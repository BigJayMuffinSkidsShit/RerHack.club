package club.shrimphack.swag.features.command.commands;

import club.shrimphack.swag.features.command.Command;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.shrimphack.swag.ShrimpHack;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(ChatFormatting.GREEN + "Current prefix is " + ShrimpHack.commandManager.getPrefix());
            return;
        }
        ShrimpHack.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + ChatFormatting.GRAY + commands[0]);
    }
}

