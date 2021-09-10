package com.whitelightapp.discordbot;

import com.whitelightapp.discordbot.commands.utils.Config;
import com.whitelightapp.discordbot.commands.utils.Listener;
import com.whitelightapp.discordbot.database.DatabaseManager;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Bot {

    private Bot() throws LoginException {
        // Sneaky init of the class for faster boot
        DatabaseManager.INSTANCE.getPrefix(-1);
        WebUtils.setUserAgent("I am a bot");
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                        .setColor(0x3883d9)
                        .setFooter("White Light Inc.")
        );

        JDABuilder.createDefault(
                        Config.get("token"),
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_VOICE_STATES
                )
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE
                ))
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(new Listener())
                .setActivity(Activity.watching("White Light Inc"))
                .build();
    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }

}
