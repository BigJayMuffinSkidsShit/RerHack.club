package club.shrimphack.swag.features.command.commands;

import club.shrimphack.swag.features.command.Command;
import club.shrimphack.swag.ShrimpHack;

public class UnloadCommand
        extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        ShrimpHack.unload(true);
    }
}

