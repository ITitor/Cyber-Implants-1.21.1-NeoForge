package net.ititor.cyber_implants.command.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.ititor.cyber_implants.data.ModData;
import net.ititor.cyber_implants.network.SendCyberPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public class ResetCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("resetability").requires((sourceStack) -> {
            return sourceStack.hasPermission(2);}).executes(ResetCommand::runSetSpawn));
    }

    public static int runSetSpawn(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = context.getSource().getPlayer();
        ServerLevel level = context.getSource().getLevel();

        player.setData(ModData.IMPLANT0, 0);
        player.setData(ModData.IMPLANT1, 0);
        player.setData(ModData.IMPLANT2, 0);
        player.setData(ModData.IMPLANT3, 0);


//        context.getSource().sendSuccess(() -> {
//            return Component.literal("Set spawn at " + pos);
//        }, true);

        return 1;
    }



}

