package com.dehys.ryder;

import com.dehys.ryder.commands.Commands;
import com.dehys.ryder.models.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.reflections.Reflections;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;
import java.util.Set;

public class Ryder {

    public static JDA jda;
    public static CommandHandler commandHandler;
    public static Set<Class<? extends Command>> allCommands;

    public static void main(String[] args) throws LoginException, InterruptedException {

        // Getting all classes implementing the Command interface
        Reflections reflections = new Reflections("com.dehys.ryder");
        allCommands = reflections.getSubTypesOf(Command.class);

        jda = JDABuilder.create("MzM3NzA1ODAyMTM3NjAwMDEw.WXEfCQ.JiTc2rZfMaYrIoAXAn_TYAKrioo" , getIntents()).build();
        jda.awaitReady();

        commandHandler = ((CommandHandler) new CommandHandler().subscribe()).addCommands(
                new Commands()
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
