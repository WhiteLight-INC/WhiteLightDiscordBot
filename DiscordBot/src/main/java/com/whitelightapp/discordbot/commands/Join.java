package com.whitelightapp.discordbot.commands;

import com.whitelightapp.discordbot.commands.utils.CommandContext;
import com.whitelightapp.discordbot.commands.utils.CommandManager;
import com.whitelightapp.discordbot.commands.utils.ICommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ConstantConditions")
public class Join implements ICommand {
    private final CommandManager commandManager;

    public Join(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        Member selfMember = ctx.getSelfMember();
        GuildVoiceState selfVoiceState = selfMember.getVoiceState();

        if (selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("I am already in a voice channel!").complete().delete().queueAfter(10, TimeUnit.SECONDS);
            return;
        }

        Member member = ctx.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You must be in a voice channel to use this command!").complete().delete().queueAfter(10, TimeUnit.SECONDS);
            return;
        }

        AudioManager audioManager = ctx.getGuild().getAudioManager();
        VoiceChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);

        channel.sendMessage("Connecting to `\uD83D\udd0A " + memberChannel.getName()).complete().delete().queueAfter(10, TimeUnit.SECONDS);
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getHelp() {
        return "Tells the bot to join your voice channel";
    }

    @Override
    public List<String> getAliases() {
        return List.of("summon", "come");
    }
}
