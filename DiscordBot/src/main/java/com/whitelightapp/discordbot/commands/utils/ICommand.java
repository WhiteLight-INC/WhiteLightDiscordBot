package com.whitelightapp.discordbot.commands.utils;

import com.whitelightapp.discordbot.commands.utils.CommandContext;

import java.util.List;

public interface ICommand {
    void handle(CommandContext ctx);

    String getName();

    String getHelp();

    default List<String> getAliases() {
        return List.of(); // use Arrays.asList if you are on java 8
    }
}