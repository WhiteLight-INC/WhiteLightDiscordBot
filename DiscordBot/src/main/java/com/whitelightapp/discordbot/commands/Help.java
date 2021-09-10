package com.whitelightapp.discordbot.commands;

import com.whitelightapp.discordbot.commands.utils.CommandContext;
import com.whitelightapp.discordbot.commands.utils.CommandManager;
import com.whitelightapp.discordbot.commands.utils.ICommand;
import com.whitelightapp.discordbot.commands.utils.Prefix;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Help implements ICommand {

    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getEvent().getChannel();

        if (args.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            String prefix = Prefix.PREFIXES.get(ctx.getGuild().getIdLong());

            builder.append("List of commands\n");

            commandManager.getCommands().stream().map(ICommand::getName).forEach(
                    (it) -> builder.append('`')
                            .append(prefix)
                            .append(it)
                            .append("`\n")
            );

            channel.sendMessage(builder.toString()).queue();
            return;
        }

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
        return "help";
    }

    @Override
    public String getHelp() {
        return "Displays a list of commands for the bot";
    }

    @Override
    public List<String> getAliases() {
        return List.of("?");
    }
}
