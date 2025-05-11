/**
 * Copyright (c) 2010-2024 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.modbus.kostal.internal.plenticore.handler;

import static org.openhab.binding.modbus.kostal.internal.plenticore.ModbusConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.modbus.kostal.internal.AbstractModbusHandler;
import org.openhab.binding.modbus.kostal.internal.plenticore.KostalPlenticoreConfiguration;
import org.openhab.binding.modbus.kostal.internal.plenticore.parser.*;
import org.openhab.core.io.transport.modbus.*;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.library.unit.SIUnits;
import org.openhab.core.library.unit.Units;
import org.openhab.core.thing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link KostalPlenticoreHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Martin Renner - Initial contribution
 */
@NonNullByDefault
public class KostalPlenticoreHandler extends AbstractModbusHandler {

    private final Logger logger = LoggerFactory.getLogger(KostalPlenticoreHandler.class);
    private final Data0BlockParser data0BlockParser = new Data0BlockParser();
    private final Data1BlockParser data1BlockParser = new Data1BlockParser();
    private final Data2BlockParser data2BlockParser = new Data2BlockParser();
    private final Data3BlockParser data3BlockParser = new Data3BlockParser();
    private final Data4BlockParser data4BlockParser = new Data4BlockParser();
    private final Data5BlockParser data5BlockParser = new Data5BlockParser();

    private final ChannelHolder ch;

    private @Nullable KostalPlenticoreConfiguration config;
    private volatile @Nullable PollTask data0PollTask;
    private volatile @Nullable PollTask data1PollTask;
    private volatile @Nullable PollTask data2PollTask;
    private volatile @Nullable PollTask data3PollTask;
    private volatile @Nullable PollTask data4PollTask;
    private volatile @Nullable PollTask data5PollTask;

    public KostalPlenticoreHandler(Thing thing) {
        super(thing);
        ch = new ChannelHolder(thing.getUID());
    }

    @Override
    public void initialize() {
        config = getConfigAs(KostalPlenticoreConfiguration.class);
        super.initialize();
    }

    protected synchronized void registerPollTask(@Nullable ModbusCommunicationInterface comms) {
        final var myconfig = config;
        if (comms == null || myconfig == null) {
            logger.debug("Invalid endpoint/config for Kostal Plenticore handler");
            return;
        }

        if (data0PollTask != null || data1PollTask != null || data2PollTask != null || data3PollTask != null
                || data4PollTask != null || data5PollTask != null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR);
            throw new IllegalStateException("pollTasks should be unregistered before registering a new one!");
        }

        logger.debug("Setting up regular polling");

        final var refreshMillis = myconfig.getRefreshMillis();
        final var data0Request = createReadRequest(DATA0_REG_START, DATA0_REG_SIZE);
        final var data1Request = createReadRequest(DATA1_REG_START, DATA1_REG_SIZE);
        final var data2Request = createReadRequest(DATA2_REG_START, DATA2_REG_SIZE);
        final var data3Request = createReadRequest(DATA3_REG_START, DATA3_REG_SIZE);
        final var data4Request = createReadRequest(DATA4_REG_START, DATA4_REG_SIZE);
        final var data5Request = createReadRequest(DATA5_REG_START, DATA5_REG_SIZE);

        data0PollTask = comms.registerRegularPoll(data0Request, refreshMillis, 1500, result -> {
            result.getRegisters().ifPresent(this::handleData0Registers);
            if (getThing().getStatus() != ThingStatus.ONLINE) {
                updateStatus(ThingStatus.ONLINE);
            }
        }, this::handleError);
        data1PollTask = comms.registerRegularPoll(data1Request, refreshMillis, 1600, result -> {
            result.getRegisters().ifPresent(this::handleData1Registers);
        }, this::handleError);
        data2PollTask = comms.registerRegularPoll(data2Request, refreshMillis, 1700, result -> {
            result.getRegisters().ifPresent(this::handleData2Registers);
        }, this::handleError);
        data3PollTask = comms.registerRegularPoll(data3Request, refreshMillis, 1800, result -> {
            result.getRegisters().ifPresent(this::handleData3Registers);
        }, this::handleError);
        data4PollTask = comms.registerRegularPoll(data4Request, refreshMillis, 1900, result -> {
            result.getRegisters().ifPresent(this::handleData4Registers);
        }, this::handleError);
        data5PollTask = comms.registerRegularPoll(data5Request, refreshMillis, 2000, result -> {
            result.getRegisters().ifPresent(this::handleData5Registers);
        }, this::handleError);
    }

    void handleData0Registers(ModbusRegisterArray registers) {
        logger.trace("Data0 block received, size: {}", registers.size());
        final var block = data0BlockParser.parse(registers);

        // device information
        updateState(ch.numberOfAcPhases, qt(block.numberOfAcPhases, Units.PERCENT));
        updateState(ch.numberOfPvStrings, qt(block.numberOfPvStrings, Units.PERCENT));
        updateState(ch.inverterState, new DecimalType(block.inverterState));

        resetCommunicationError();
    }

    void handleData1Registers(ModbusRegisterArray registers) {
        logger.trace("Data1 block received, size: {}", registers.size());
        final var block = data1BlockParser.parse(registers);

        // device information
        updateState(ch.temperatureOfControllerPcb, qt(block.temperatureOfControllerPcb, SIUnits.CELSIUS));

        // dc generic
        updateState(ch.totalDcPower, qt(block.totalDcPower, Units.WATT));

        // consumption
        updateState(ch.homeOwnConsumptionFromBattery, qt(block.homeOwnConsumptionFromBattery, Units.WATT));
        updateState(ch.homeOwnConsumptionFromGrid, qt(block.homeOwnConsumptionFromGrid, Units.WATT));
        updateState(ch.homeOwnConsumptionFromPv, qt(block.homeOwnConsumptionFromPv, Units.WATT));
        updateState(ch.totalHomeConsumptionBattery, qt(block.totalHomeConsumptionBattery, Units.WATT_HOUR));
        updateState(ch.totalHomeConsumptionGrid, qt(block.totalHomeConsumptionGrid, Units.WATT_HOUR));
        updateState(ch.totalHomeConsumptionPv, qt(block.totalHomeConsumptionPv, Units.WATT_HOUR));
        updateState(ch.totalHomeConsumption, qt(block.totalHomeConsumption, Units.WATT_HOUR));
        updateState(ch.totalHomeConsumptionRate, qt(block.totalHomeConsumptionRate, Units.PERCENT));

        // ac generic
        updateState(ch.cosPhi, new DecimalType(block.actualCosPhi));
        updateState(ch.frequency, qt(block.gridFrequency, Units.HERTZ));
        updateState(ch.totalAcActivePower, qt(block.totalAcActivePower, Units.WATT));
        updateState(ch.totalAcReactivePower, qt(block.totalAcReactivePower, Units.WATT)); // TODO var
        updateState(ch.totalAcApparentPower, qt(block.totalAcApparentPower, Units.WATT)); // TODO VA

        // ac phases
        updateState(ch.currentPhase1, qt(block.currentPhase1, Units.AMPERE));
        updateState(ch.activePowerPhase1, qt(block.activePowerPhase1, Units.WATT));
        updateState(ch.voltagePhase1, qt(block.voltagePhase1, Units.VOLT));
        updateState(ch.currentPhase2, qt(block.currentPhase2, Units.AMPERE));
        updateState(ch.activePowerPhase2, qt(block.activePowerPhase2, Units.WATT));
        updateState(ch.voltagePhase2, qt(block.voltagePhase2, Units.VOLT));
        updateState(ch.currentPhase3, qt(block.currentPhase3, Units.AMPERE));
        updateState(ch.activePowerPhase3, qt(block.activePowerPhase3, Units.WATT));
        updateState(ch.voltagePhase3, qt(block.voltagePhase3, Units.VOLT));

        // battery part 1
        updateState(ch.batteryChargeCurrent, qt(block.batteryChargeCurrent, Units.AMPERE));
        updateState(ch.numberOfBatteryCycles, new DecimalType(block.numberOfBatteryCycles));
        updateState(ch.actualBatteryCharge, qt(block.actualBatteryCharge, Units.AMPERE));
        updateState(ch.pssbFuseState, new DecimalType(block.pssbFuseState));
        updateState(ch.batteryReadyFlag, new DecimalType(block.batteryReadyFlag));
        updateState(ch.actualStateOfCharge, new DecimalType(block.actualStateOfCharge));
        updateState(ch.batteryTemperature, qt(block.batteryTemperature, SIUnits.CELSIUS));
        updateState(ch.batteryVoltage, qt(block.batteryVoltage, Units.VOLT));

        resetCommunicationError();
    }

    void handleData2Registers(ModbusRegisterArray registers) {
        logger.trace("Data2 block received, size: {}", registers.size());
        final var block = data2BlockParser.parse(registers);

        // powermeter generic
        updateState(ch.cosPhiPowerMeter, new DecimalType(block.cosPhiPowerMeter));
        updateState(ch.frequencyPowerMeter, qt(block.frequencyPowerMeter, Units.HERTZ));
        updateState(ch.totalActivePowerPowerMeter, qt(block.totalActivePowerPowerMeter, Units.WATT));
        updateState(ch.totalReactivePowerPowerMeter, qt(block.totalReactivePowerPowerMeter, Units.WATT)); // var
        updateState(ch.totalApparentPowerPowerMeter, qt(block.totalApparentPowerPowerMeter, Units.WATT)); // VA

        // powermeter phases
        updateState(ch.currentPhase1PowerMeter, qt(block.currentPhase1PowerMeter, Units.AMPERE));
        updateState(ch.activePowerPhase1PowerMeter, qt(block.activePowerPhase1PowerMeter, Units.WATT));
        updateState(ch.reactivePowerPhase1PowerMeter, qt(block.reactivePowerPhase1PowerMeter, Units.WATT)); // var
        updateState(ch.apparentPowerPhase1PowerMeter, qt(block.apparentPowerPhase1PowerMeter, Units.WATT)); // VA
        updateState(ch.voltagePhase1PowerMeter, qt(block.voltagePhase1PowerMeter, Units.VOLT));
        updateState(ch.currentPhase2PowerMeter, qt(block.currentPhase2PowerMeter, Units.AMPERE));
        updateState(ch.activePowerPhase2PowerMeter, qt(block.activePowerPhase2PowerMeter, Units.WATT));
        updateState(ch.reactivePowerPhase2PowerMeter, qt(block.reactivePowerPhase2PowerMeter, Units.WATT)); // var
        updateState(ch.apparentPowerPhase2PowerMeter, qt(block.apparentPowerPhase2PowerMeter, Units.WATT)); // VA
        updateState(ch.voltagePhase2PowerMeter, qt(block.voltagePhase2PowerMeter, Units.VOLT));
        updateState(ch.currentPhase3PowerMeter, qt(block.currentPhase3PowerMeter, Units.AMPERE));
        updateState(ch.activePowerPhase3PowerMeter, qt(block.activePowerPhase3PowerMeter, Units.WATT));
        updateState(ch.reactivePowerPhase3PowerMeter, qt(block.reactivePowerPhase3PowerMeter, Units.WATT)); // var
        updateState(ch.apparentPowerPhase3PowerMeter, qt(block.apparentPowerPhase3PowerMeter, Units.WATT)); // VA
        updateState(ch.voltagePhase3PowerMeter, qt(block.voltagePhase3PowerMeter, Units.VOLT));

        // dc strings
        updateState(ch.currentDc1, qt(block.currentDc1, Units.AMPERE));
        updateState(ch.powerDc1, qt(block.powerDc1, Units.WATT));
        updateState(ch.voltageDc1, qt(block.voltageDc1, Units.VOLT));
        updateState(ch.currentDc2, qt(block.currentDc2, Units.AMPERE));
        updateState(ch.powerDc2, qt(block.powerDc2, Units.WATT));
        updateState(ch.voltageDc2, qt(block.voltageDc2, Units.VOLT));
        updateState(ch.currentDc3, qt(block.currentDc3, Units.AMPERE));
        updateState(ch.powerDc3, qt(block.powerDc3, Units.WATT));
        updateState(ch.voltageDc3, qt(block.voltageDc3, Units.VOLT));

        resetCommunicationError();
    }

    void handleData3Registers(ModbusRegisterArray registers) {
        logger.trace("Data3 block received, size: {}", registers.size());
        final var block = data3BlockParser.parse(registers);

        // yield
        updateState(ch.totalYield, qt(block.totalYield, Units.WATT_HOUR));
        updateState(ch.dailyYield, qt(block.dailyYield, Units.WATT_HOUR));
        updateState(ch.monthlyYield, qt(block.monthlyYield, Units.WATT_HOUR));
        updateState(ch.yearlyYield, qt(block.yearlyYield, Units.WATT_HOUR));

        resetCommunicationError();
    }

    void handleData4Registers(ModbusRegisterArray registers) {
        logger.trace("Data4 block received, size: {}", registers.size());
        final var block = data4BlockParser.parse(registers);

        // battery part 2
        updateState(ch.batteryGrossCapacity, qt(block.batteryGrossCapacity, Units.AMPERE_HOUR));
        updateState(ch.batteryActualSoc, new DecimalType(block.batteryActualSoc));
        updateState(ch.batteryManufacturer, new StringType(block.batteryManufacturer));
        updateState(ch.batteryModelId, new DecimalType(block.batteryModelId));
        updateState(ch.batterySerialNumber, new DecimalType(block.batterySerialNumber));
        updateState(ch.workCapacity, qt(block.workCapacity, Units.WATT_HOUR));
        updateState(ch.actualBatteryChargePower, qt(block.actualBatteryChargePower, Units.WATT));
        updateState(ch.batteryFirmware, new DecimalType(block.batteryFirmware));
        updateState(ch.batteryType, new DecimalType(block.batteryType));

        resetCommunicationError();
    }

    void handleData5Registers(ModbusRegisterArray registers) {
        logger.trace("Data5 block received, size: {}", registers.size());
        final var block = data5BlockParser.parse(registers);

        // DC charge, AC charge
        updateState(ch.totalDcChargeEnergy, qt(block.totalDcChargeEnergy, Units.WATT_HOUR));
        updateState(ch.totalDcDischargeEnergy, qt(block.totalDcDischargeEnergy, Units.WATT_HOUR));
        updateState(ch.totalAcChargeEnergy, qt(block.totalAcChargeEnergy, Units.WATT_HOUR));
        updateState(ch.totalAcDischargeEnergy, qt(block.totalAcDischargeEnergy, Units.WATT_HOUR));
        updateState(ch.totalAcChargeEnergyGrid, qt(block.totalAcChargeEnergyGrid, Units.WATT_HOUR));

        // dc ac energy
        updateState(ch.totalDcPvEnergy, qt(block.totalDcPvEnergy, Units.WATT_HOUR));
        updateState(ch.totalDcEnergyFromPv1, qt(block.totalDcEnergyFromPv1, Units.WATT_HOUR));
        updateState(ch.totalDcEnergyFromPv2, qt(block.totalDcEnergyFromPv2, Units.WATT_HOUR));
        updateState(ch.totalDcEnergyFromPv3, qt(block.totalDcEnergyFromPv3, Units.WATT_HOUR));
        updateState(ch.totalEnergyAcSideToGrid, qt(block.totalEnergyAcSideToGrid, Units.WATT_HOUR));

        // dc general
        updateState(ch.totalDcPowerSum, qt(block.totalDcPowerSum, Units.WATT));

        resetCommunicationError();
    }

    void handleError(AsyncModbusFailure<ModbusReadRequestBlueprint> failure) {
        final var msg = failure.getCause().getMessage();
        final var cls = failure.getCause().getClass().getName();
        updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                "Error with read: %s: %s".formatted(cls, msg));
    }

    protected synchronized void unregisterPollTask(@Nullable ModbusCommunicationInterface comms) {
        @Nullable
        final PollTask myData0PollTask = data0PollTask;
        @Nullable
        final PollTask myData1PollTask = data1PollTask;
        @Nullable
        final PollTask myData2PollTask = data2PollTask;
        @Nullable
        final PollTask myData3PollTask = data3PollTask;
        @Nullable
        final PollTask myData4PollTask = data4PollTask;
        @Nullable
        final PollTask myData5PollTask = data5PollTask;

        logger.debug("Unregistering polling from ModbusManager");
        @Nullable
        final ModbusCommunicationInterface mycomms = comms;
        if (mycomms != null) {
            if (myData0PollTask != null) {
                mycomms.unregisterRegularPoll(myData0PollTask);
            }
            if (myData1PollTask != null) {
                mycomms.unregisterRegularPoll(myData1PollTask);
            }
            if (myData2PollTask != null) {
                mycomms.unregisterRegularPoll(myData2PollTask);
            }
            if (myData3PollTask != null) {
                mycomms.unregisterRegularPoll(myData3PollTask);
            }
            if (myData4PollTask != null) {
                mycomms.unregisterRegularPoll(myData4PollTask);
            }
            if (myData5PollTask != null) {
                mycomms.unregisterRegularPoll(myData5PollTask);
            }
        }
        data0PollTask = null;
        data1PollTask = null;
        data2PollTask = null;
        data3PollTask = null;
        data4PollTask = null;
        data5PollTask = null;
    }
}
