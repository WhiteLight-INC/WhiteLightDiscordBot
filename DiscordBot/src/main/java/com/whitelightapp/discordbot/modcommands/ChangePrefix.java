package com.whitelightapp.discordbot.modcommands;

import com.whitelightapp.discordbot.commands.utils.CommandContext;
import com.whitelightapp.discordbot.commands.utils.ICommand;

import java.util.List;

public class ChangePrefix implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return ICommand.super.getAliases();
    }
}
