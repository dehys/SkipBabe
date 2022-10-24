package se.nikoci.ryder.bot;

import net.dv8tion.jda.api.requests.GatewayIntent;
import se.nikoci.ryder.bot.commands.division2.DivisionCommandOld;

import javax.security.auth.login.LoginException;
import java.util.Set;

public class Main {

    public static Ryder ryder;

    public static void main(String[] args) throws LoginException, InterruptedException {
        ryder = new Ryder("MTAyMTYzMTg4Nzk4ODQ5ODUwNA.G0jW2R.MLPBI92ZRzns_1MgDJEgyfUpgQsL4PmqKWpQdA", Set.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_WEBHOOKS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS
        ), new CommandHandler(), new Settings());

        ryder.getCommandHandler().addCommand(new DivisionCommandOld());
        ryder.getCommandHandler().updateCommandData();

    }
}