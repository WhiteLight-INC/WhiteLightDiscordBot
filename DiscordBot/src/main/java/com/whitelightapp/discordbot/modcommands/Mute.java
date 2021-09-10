package com.whitelightapp.discordbot.modcommands;

import com.whitelightapp.discordbot.commands.utils.CommandContext;
import com.whitelightapp.discordbot.commands.utils.CommandManager;
import com.whitelightapp.discordbot.commands.utils.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Mute implements ICommand {

    private CommandManager commandManager;

    public Mute(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getEvent().getChannel();



        String search = args.get(0);
        ICommand command = commandManager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search).queue();
            return;
        }

        channel.sendMessage(command.getHelp()).queue();
    }

    @Override
    public String getName() {
        return "mute";
    }

    @Override
    public String getHelp() {
        return "Mute a specified user in chat";
    }

    @Override
    public List<String> getAliases() {
        return List.of("stfu", "quiet", "slience");
    }
}
