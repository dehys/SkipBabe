package com.dehys.skipbabe;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class SkipBabe {

    public static JDA jda;
    public static CommandHandler commandHandler;

    public static void main(String[] args) throws LoginException, InterruptedException {
        jda = JDABuilder.create(System.getenv("SKIPBABE_TOKEN") , getIntents()).build();
        jda.awaitReady();

        commandHandler = ((CommandHandler) new CommandHandler().subscribe()).addCommands();
    }

    private static EnumSet<GatewayIntent> getIntents(){
        return EnumSet.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_WEBHOOKS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS
        );
    }

}
