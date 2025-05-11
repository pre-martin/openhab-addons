package org.openhab.binding.modbus.kostal.internal;

import java.util.Optional;

import javax.measure.Quantity;
import javax.measure.Unit;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.modbus.handler.EndpointNotInitializedException;
import org.openhab.binding.modbus.handler.ModbusEndpointThingHandler;
import org.openhab.core.io.transport.modbus.ModbusCommunicationInterface;
import org.openhab.core.io.transport.modbus.ModbusReadFunctionCode;
import org.openhab.core.io.transport.modbus.ModbusReadRequestBlueprint;
import org.openhab.core.library.types.QuantityType;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NonNullByDefault
public abstract class AbstractModbusHandler extends BaseThingHandler {

    private final Logger logger = LoggerFactory.getLogger(AbstractModbusHandler.class);

    private volatile @Nullable ModbusCommunicationInterface comms = null;
    private volatile int slaveId;

    public AbstractModbusHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // control is not yet supported by this binding
    }

    @Override
    public void initialize() {
        logger.debug("Initializing thing");

        startUp();
    }

    private void startUp() {
        connectEndpoint();

        registerPollTask(comms);
    }

    @Override
    public void dispose() {
        tearDown();
    }

    /**
     * Unregister the poll task and release the endpoint reference
     */
    private void tearDown() {
        unregisterPollTask(comms);
        unregisterEndpoint();
    }

    /**
     * Returns the {@link ModbusEndpointThingHandler} from the bridge. This method checks, that the bridge is online and
     * of the right type.
     */
    private @Nullable ModbusEndpointThingHandler getEndpointThingHandler() {
        final var bridge = getBridge();

        if (bridge == null) {
            logger.debug("Bridge is null");
            return null;
        }

        if (bridge.getStatus() != ThingStatus.ONLINE) {
            logger.debug("Bridge is not online");
            return null;
        }

        final var bridgeHandler = bridge.getHandler();
        if (bridgeHandler == null) {
            logger.debug("Bridge handler is null");
            return null;
        }

        if (bridgeHandler instanceof ModbusEndpointThingHandler modbusEndpointThingHandler) {
            return modbusEndpointThingHandler;
        } else {
            logger.debug("Unexpected bridge handler: {}", bridgeHandler);
            return null;
        }
    }

    /**
     * Get a reference to the modbus endpoint
     */
    private void connectEndpoint() {
        if (comms != null) {
            return;
        }

        ModbusEndpointThingHandler slaveEndpointThingHandler = getEndpointThingHandler();
        final var label = Optional.ofNullable(getBridge()).map(Thing::getLabel).orElse("<null>");

        if (slaveEndpointThingHandler == null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_OFFLINE,
                    "Bridge '%s' is offline".formatted(label));
            logger.debug("No bridge handler available -- aborting init for '{}'", label);
            return;
        }

        try {
            slaveId = slaveEndpointThingHandler.getSlaveId();
            comms = slaveEndpointThingHandler.getCommunicationInterface();
        } catch (EndpointNotInitializedException e) {
            // handled below, because "comms" remains null.
        }

        if (comms == null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_OFFLINE,
                    "Bridge '%s' not completely initialized".formatted(label));
            logger.debug("Bridge not initialized fully (no endpoint) -- aborting init for '{}'", this);
            return;
        }
    }

    private void unregisterEndpoint() {
        // Comms will be close()'d by endpoint thing handler
        comms = null;
    }

    protected abstract void registerPollTask(@Nullable ModbusCommunicationInterface comms);

    protected abstract void unregisterPollTask(@Nullable ModbusCommunicationInterface comms);

    @Override
    public void bridgeStatusChanged(ThingStatusInfo bridgeStatusInfo) {
        super.bridgeStatusChanged(bridgeStatusInfo);

        logger.debug("Thing status changed to {}", this.getThing().getStatus().name());
        if (getThing().getStatus() == ThingStatus.ONLINE) {
            startUp();
        } else if (getThing().getStatus() == ThingStatus.OFFLINE) {
            tearDown();
        }
    }

    /**
     * Reset communication status to ONLINE if we're in an OFFLINE state
     */
    protected void resetCommunicationError() {
        ThingStatusInfo statusInfo = thing.getStatusInfo();
        if (ThingStatus.OFFLINE.equals(statusInfo.getStatus())
                && ThingStatusDetail.COMMUNICATION_ERROR.equals(statusInfo.getStatusDetail())) {
            updateStatus(ThingStatus.ONLINE);
        }
    }

    /**
     * Creates a {@link ModbusReadRequestBlueprint} with the given parameters and a retry-count of 1.
     */
    protected ModbusReadRequestBlueprint createReadRequest(int start, int length) {
        return new ModbusReadRequestBlueprint(slaveId, ModbusReadFunctionCode.READ_MULTIPLE_REGISTERS, start, length,
                1);
    }

    /**
     * Shortcut to {@code QuantityType.valueOf()} to avoid line breaks.
     */
    protected <T extends Quantity<T>> QuantityType<T> qt(double value, Unit<T> unit) {
        return QuantityType.valueOf(value, unit);
    }
}
