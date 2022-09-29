package se.nikoci.bot;

import net.dv8tion.jda.api.requests.GatewayIntent;
import se.nikoci.bot.commands.InfoCommand;

import javax.security.auth.login.LoginException;
import java.util.Set;

public class Main {

    public static Ryder ryder;

    public static void main(String[] args) throws LoginException, InterruptedException {
        ryder = new Ryder("MTAyNDc1NjQxOTM5Mzc1NzIwNA.GYj7_q.AwddZ7Ja8zWPZ09zZ_jEjpNRVlQzDGrUabfYXc", Set.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_WEBHOOKS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS
        ), new CommandHandler(), new Settings());

        ryder.getCommandHandler().addCommands(
                new InfoCommand()
        );
    }
}