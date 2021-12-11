package club.shrimphack.swag.features.command.commands;

import club.shrimphack.swag.features.command.Command;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.shrimphack.swag.ShrimpHack;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : ShrimpHack.commandManager.getCommands()) {
            HelpCommand.sendMessage(ChatFormatting.GRAY + ShrimpHack.commandManager.getPrefix() + command.getName());
        }
    }
}

