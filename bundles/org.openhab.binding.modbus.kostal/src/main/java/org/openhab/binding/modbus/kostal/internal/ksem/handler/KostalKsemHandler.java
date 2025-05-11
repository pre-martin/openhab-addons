package org.openhab.binding.modbus.kostal.internal.ksem.handler;

import static org.openhab.binding.modbus.kostal.internal.ksem.ModbusConstants.*;
import static org.openhab.binding.modbus.kostal.internal.ksem.ThingConstants.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Duration;

import javax.measure.Quantity;
import javax.measure.Unit;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.modbus.kostal.internal.AbstractModbusHandler;
import org.openhab.binding.modbus.kostal.internal.ksem.KostalKsemConfiguration;
import org.openhab.binding.modbus.kostal.internal.ksem.parser.EnergyBlockParser;
import org.openhab.binding.modbus.kostal.internal.ksem.parser.InfoBlockParser;
import org.openhab.binding.modbus.kostal.internal.ksem.parser.PowerBlockParser;
import org.openhab.core.io.transport.modbus.*;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.QuantityType;
import org.openhab.core.library.unit.Units;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingStatus;
import org.openhab.core.thing.ThingStatusDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NonNullByDefault
public class KostalKsemHandler extends AbstractModbusHandler {

    private final Logger logger = LoggerFactory.getLogger(KostalKsemHandler.class);
    private final PowerBlockParser powerBlockParser = new PowerBlockParser();
    private final EnergyBlockParser energyBlockParser = new EnergyBlockParser();
    private final InfoBlockParser infoBlockParser = new InfoBlockParser();

    private final ChannelHolder ch;

    private @Nullable KostalKsemConfiguration config;
    private volatile @Nullable PollTask power0PollTask;
    private volatile @Nullable PollTask power1PollTask;
    private volatile @Nullable PollTask energy0PollTask;
    private volatile @Nullable PollTask energy1PollTask;
    private volatile @Nullable PollTask infoPollTask;

    public KostalKsemHandler(Thing thing) {
        super(thing);
        ch = new ChannelHolder(thing.getUID());
    }

    @Override
    public void initialize() {
        config = getConfigAs(KostalKsemConfiguration.class);
        super.initialize();
    }

    protected synchronized void registerPollTask(@Nullable ModbusCommunicationInterface comms) {
        final var myconfig = config;
        if (comms == null || myconfig == null) {
            logger.debug("Invalid endpoint/config for Kostal Smart Energy handler");
            return;
        }

        if (power0PollTask != null || power1PollTask != null || energy0PollTask != null || energy1PollTask != null
                || infoPollTask != null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR);
            throw new IllegalStateException("pollTasks should be unregistered before registering a new one!");
        }

        logger.debug("Setting up regular polling");

        final var refreshMillis = myconfig.getRefreshMillis();
        final var power0Request = createReadRequest(POWER0_REG_START, POWER0_REG_SIZE);
        final var power1Request = createReadRequest(POWER1_REG_START, POWER1_REG_SIZE);
        final var energy0Request = createReadRequest(ENERGY0_REG_START, ENERGY0_REG_SIZE);
        final var energy1Request = createReadRequest(ENERGY1_REG_START, ENERGY1_REG_SIZE);
        final var infoRequest = createReadRequest(INFO_REG_START, INFO_REG_SIZE);

        power0PollTask = comms.registerRegularPoll(power0Request, refreshMillis, 1000, result -> {
            result.getRegisters().ifPresent(this::handlePower0Registers);
            if (getThing().getStatus() != ThingStatus.ONLINE) {
                updateStatus(ThingStatus.ONLINE);
            }
        }, this::handleError);
        power1PollTask = comms.registerRegularPoll(power1Request, refreshMillis, 1100, result -> {
            result.getRegisters().ifPresent(this::handlePower1Registers);
        }, this::handleError);
        energy0PollTask = comms.registerRegularPoll(energy0Request, refreshMillis, 1200, result -> {
            result.getRegisters().ifPresent(this::handleEnergy0Registers);
        }, this::handleError);
        energy1PollTask = comms.registerRegularPoll(energy1Request, refreshMillis, 1300, result -> {
            result.getRegisters().ifPresent(this::handleEnergy1Registers);
        }, this::handleError);
        infoPollTask = comms.registerRegularPoll(infoRequest, Duration.ofMinutes(1).toMillis(), 500, result -> {
            result.getRegisters().ifPresent(this::handleInfoRegisters);
        }, this::handleError);
    }

    void handlePower0Registers(ModbusRegisterArray registers) {
        logger.trace("Power0 block received, size: {}", registers.size());
        final var block = powerBlockParser.parseBlock0(registers);

        updateState(ch.activePowerPlus, qt((double) block.activePowerPlus / 10, Units.WATT));
        updateState(ch.activePowerMinus, qt((double) block.activePowerMinus / 10, Units.WATT));
        updateState(ch.reactivePowerPlus, qt((double) block.reactivePowerPlus / 10, Units.WATT)); // TODO var
        updateState(ch.reactivePowerMinus, qt((double) block.reactivePowerMinus / 10, Units.WATT)); // TODO var
        updateState(ch.apparentPowerPlus, qt((double) block.apparentPowerPlus / 10, Units.WATT)); // TODO VA
        updateState(ch.apparentPowerMinus, qt((double) block.apparentPowerMinus / 10, Units.WATT)); // TODO VA
        updateState(ch.powerFactor, new DecimalType((double) block.powerFactor / 1000));
        updateState(ch.supplyFrequency, new DecimalType((double) block.supplyFrequency / 1000));

        resetCommunicationError();
    }

    void handlePower1Registers(ModbusRegisterArray registers) {
        logger.trace("Power1 block received, size: {}", registers.size());
        final var block = powerBlockParser.parseBlock1(registers);

        updateState(ch.activePowerPlusL1, qt((double) block.activePowerPlusL1 / 10, Units.WATT));
        updateState(ch.activePowerMinusL1, qt((double) block.activePowerMinusL1 / 10, Units.WATT));
        updateState(ch.reactivePowerPlusL1, qt((double) block.reactivePowerPlusL1 / 10, Units.WATT)); // TODO var
        updateState(ch.reactivePowerMinusL1, qt((double) block.reactivePowerMinusL1 / 10, Units.WATT)); // TODO var
        updateState(ch.apparentPowerPlusL1, qt((double) block.apparentPowerPlusL1 / 10, Units.WATT)); // TODO VA
        updateState(ch.apparentPowerMinusL1, qt((double) block.apparentPowerMinusL1 / 10, Units.WATT)); // TODO VA
        updateState(ch.currentL1, qt((double) block.currentL1 / 1000, Units.AMPERE));
        updateState(ch.voltageL1, qt((double) block.voltageL1 / 1000, Units.VOLT));
        updateState(ch.powerFactorL1, new DecimalType((double) block.powerFactorL1 / 1000));

        updateState(ch.activePowerPlusL2, qt((double) block.activePowerPlusL2 / 10, Units.WATT));
        updateState(ch.activePowerMinusL2, qt((double) block.activePowerMinusL2 / 10, Units.WATT));
        updateState(ch.reactivePowerPlusL2, qt((double) block.reactivePowerPlusL2 / 10, Units.WATT)); // TODO var
        updateState(ch.reactivePowerMinusL2, qt((double) block.reactivePowerMinusL2 / 10, Units.WATT)); // TODO var
        updateState(ch.apparentPowerPlusL2, qt((double) block.apparentPowerPlusL2 / 10, Units.WATT)); // TODO VA
        updateState(ch.apparentPowerMinusL2, qt((double) block.apparentPowerMinusL2 / 10, Units.WATT)); // TODO VA
        updateState(ch.currentL2, qt((double) block.currentL2 / 1000, Units.AMPERE));
        updateState(ch.voltageL2, qt((double) block.voltageL2 / 1000, Units.VOLT));
        updateState(ch.powerFactorL2, new DecimalType((double) block.powerFactorL2 / 1000));

        updateState(ch.activePowerPlusL3, qt((double) block.activePowerPlusL3 / 10, Units.WATT));
        updateState(ch.activePowerMinusL3, qt((double) block.activePowerMinusL3 / 10, Units.WATT));
        updateState(ch.reactivePowerPlusL3, qt((double) block.reactivePowerPlusL3 / 10, Units.WATT)); // TODO var
        updateState(ch.reactivePowerMinusL3, qt((double) block.reactivePowerMinusL3 / 10, Units.WATT)); // TODO var
        updateState(ch.apparentPowerPlusL3, qt((double) block.apparentPowerPlusL3 / 10, Units.WATT)); // TODO VA
        updateState(ch.apparentPowerMinusL3, qt((double) block.apparentPowerMinusL3 / 10, Units.WATT)); // TODO VA
        updateState(ch.currentL3, qt((double) block.currentL3 / 1000, Units.AMPERE));
        updateState(ch.voltageL3, qt((double) block.voltageL3 / 1000, Units.VOLT));
        updateState(ch.powerFactorL3, new DecimalType((double) block.powerFactorL3 / 1000));

        resetCommunicationError();
    }

    void handleEnergy0Registers(ModbusRegisterArray registers) {
        logger.trace("Energy0 block received, size: {}", registers.size());
        final var block = energyBlockParser.parseBlock0(registers);

        updateState(ch.activeEnergyPlus, qtBy10(block.activeEnergyPlus, Units.WATT_HOUR));
        updateState(ch.activeEnergyMinus, qtBy10(block.activeEnergyMinus, Units.WATT_HOUR));
        updateState(ch.reactiveEnergyPlus, qtBy10(block.reactiveEnergyPlus, Units.WATT_HOUR)); // varh
        updateState(ch.reactiveEnergyMinus, qtBy10(block.reactiveEnergyMinus, Units.WATT_HOUR)); // varh
        updateState(ch.apparentEnergyPlus, qtBy10(block.apparentEnergyPlus, Units.WATT_HOUR)); // VAh
        updateState(ch.apparentEnergyMinus, qtBy10(block.apparentEnergyMinus, Units.WATT_HOUR)); // VAh

        updateState(ch.activeEnergyPlusL1, qtBy10(block.activeEnergyPlusL1, Units.WATT_HOUR));
        updateState(ch.activeEnergyMinusL1, qtBy10(block.activeEnergyMinusL1, Units.WATT_HOUR));
        updateState(ch.reactiveEnergyPlusL1, qtBy10(block.reactiveEnergyPlusL1, Units.WATT_HOUR)); // varh
        updateState(ch.reactiveEnergyMinusL1, qtBy10(block.reactiveEnergyMinusL1, Units.WATT_HOUR)); // varh
        updateState(ch.apparentEnergyPlusL1, qtBy10(block.apparentEnergyPlusL1, Units.WATT_HOUR)); // VAh
        updateState(ch.apparentEnergyMinusL1, qtBy10(block.apparentEnergyMinusL1, Units.WATT_HOUR)); // VAh

        resetCommunicationError();
    }

    void handleEnergy1Registers(ModbusRegisterArray registers) {
        logger.trace("Energy1 block received, size: {}", registers.size());
        final var block = energyBlockParser.parseBlock1(registers);

        updateState(ch.activeEnergyPlusL2, qtBy10(block.activeEnergyPlusL2, Units.WATT_HOUR));
        updateState(ch.activeEnergyMinusL2, qtBy10(block.activeEnergyMinusL2, Units.WATT_HOUR));
        updateState(ch.reactiveEnergyPlusL2, qtBy10(block.reactiveEnergyPlusL2, Units.WATT_HOUR)); // varh
        updateState(ch.reactiveEnergyMinusL2, qtBy10(block.reactiveEnergyMinusL2, Units.WATT_HOUR)); // varh
        updateState(ch.apparentEnergyPlusL2, qtBy10(block.apparentEnergyPlusL2, Units.WATT_HOUR)); // VAh
        updateState(ch.apparentEnergyMinusL2, qtBy10(block.apparentEnergyMinusL2, Units.WATT_HOUR)); // VAh

        updateState(ch.activeEnergyPlusL3, qtBy10(block.activeEnergyPlusL3, Units.WATT_HOUR));
        updateState(ch.activeEnergyMinusL3, qtBy10(block.activeEnergyMinusL3, Units.WATT_HOUR));
        updateState(ch.reactiveEnergyPlusL3, qtBy10(block.reactiveEnergyPlusL3, Units.WATT_HOUR)); // varh
        updateState(ch.reactiveEnergyMinusL3, qtBy10(block.reactiveEnergyMinusL3, Units.WATT_HOUR)); // varh
        updateState(ch.apparentEnergyPlusL3, qtBy10(block.apparentEnergyPlusL3, Units.WATT_HOUR)); // VAh
        updateState(ch.apparentEnergyMinusL3, qtBy10(block.apparentEnergyMinusL3, Units.WATT_HOUR)); // VAh

        resetCommunicationError();
    }

    void handleInfoRegisters(ModbusRegisterArray registers) {
        logger.trace("Info block received, size: {}", registers.size());
        final var block = infoBlockParser.parse(registers);

        final var properties = editProperties();
        properties.put(PROP_PRODUCT_VERSION, "0x" + Integer.toHexString(block.productVersion));
        properties.put(PROP_FIRMWARE_VERSION, "0x" + Integer.toHexString(block.firmwareVersion));
        properties.put(PROP_VENDOR_NAME, block.vendorName);
        properties.put(PROP_PRODUCT_NAME, block.productName);
        properties.put(PROP_SERIAL_NUMBER, block.serialNumber);
        properties.put(PROP_MEASURING_INTERVAL, String.format("%d ms", block.measuringInterval));
        updateProperties(properties);
    }

    void handleError(AsyncModbusFailure<ModbusReadRequestBlueprint> failure) {
        final var msg = failure.getCause().getMessage();
        final var cls = failure.getCause().getClass().getName();
        updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                "Error with read: %s: %s".formatted(cls, msg));
    }

    protected synchronized void unregisterPollTask(@Nullable ModbusCommunicationInterface comms) {
        @Nullable
        final PollTask myPower0PollTask = power0PollTask;
        @Nullable
        final PollTask myPower1PollTask = power1PollTask;
        @Nullable
        final PollTask myEnergy0PollTask = energy0PollTask;
        @Nullable
        final PollTask myEnergy1PollTask = energy1PollTask;
        @Nullable
        final PollTask myInfoPollTask = infoPollTask;

        logger.debug("Unregistering polling from ModbusManager");
        @Nullable
        final ModbusCommunicationInterface mycomms = comms;
        if (mycomms != null) {
            if (myPower0PollTask != null) {
                mycomms.unregisterRegularPoll(myPower0PollTask);
            }
            if (myPower1PollTask != null) {
                mycomms.unregisterRegularPoll(myPower1PollTask);
            }
            if (myEnergy0PollTask != null) {
                mycomms.unregisterRegularPoll(myEnergy0PollTask);
            }
            if (myEnergy1PollTask != null) {
                mycomms.unregisterRegularPoll(myEnergy1PollTask);
            }
            if (myInfoPollTask != null) {
                mycomms.unregisterRegularPoll(myInfoPollTask);
            }
        }
        power0PollTask = null;
        power1PollTask = null;
        energy0PollTask = null;
        energy1PollTask = null;
        infoPollTask = null;
    }

    /**
     * Shortcut to {@code QuantityType.valueOf()} to avoid line breaks.
     */
    private <T extends Quantity<T>> QuantityType<T> qtBy10(BigInteger value, Unit<T> unit) {
        return new QuantityType<>(new BigDecimal(value).divide(BigDecimal.TEN, 1, RoundingMode.HALF_UP), unit);
    }
}
