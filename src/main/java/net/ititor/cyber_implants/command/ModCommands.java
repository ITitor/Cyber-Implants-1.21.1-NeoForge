package net.ititor.cyber_implants.command;

import net.ititor.cyber_implants.command.custom.ResetCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class ModCommands {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {

        var commandDispatcher = event.getDispatcher();
        var commandBuildContext = event.getBuildContext();

        ResetCommand.register(commandDispatcher);

    }
}
