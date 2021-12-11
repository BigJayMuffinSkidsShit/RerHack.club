package club.shrimphack.swag.features.command.commands;

import club.shrimphack.swag.features.command.Command;
import club.shrimphack.swag.ShrimpHack;

public class ReloadCommand
        extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        ShrimpHack.reload();
    }
}

