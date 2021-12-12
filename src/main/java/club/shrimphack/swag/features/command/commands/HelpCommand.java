package club.shrimphack.swag.features.command.commands;

import club.shrimphack.swag.features.command.Command;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.shrimphack.swag.RerHack;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : RerHack.commandManager.getCommands()) {
            HelpCommand.sendMessage(ChatFormatting.GRAY + RerHack.commandManager.getPrefix() + command.getName());
        }
    }
}

