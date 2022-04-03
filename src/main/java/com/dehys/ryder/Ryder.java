package com.dehys.ryder;

import com.dehys.ryder.commands.Help;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Ryder {

    public static JDA jda;
    public static CommandHandler commandHandler;

    public static void main(String[] args) throws LoginException, InterruptedException {
        jda = JDABuilder.create("MzM3NzA1ODAyMTM3NjAwMDEw.WXEfCQ.JxJzuWqWQo1zW1XLnyGAXGNP5p0" , getIntents()).build();
        jda.awaitReady();

        commandHandler = ((CommandHandler) new CommandHandler().subscribe()).addCommands(
                new Help()
        );
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
