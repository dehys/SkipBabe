package se.nikoci.ryder.lib;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import se.nikoci.ryder.lib.command.CommandHandler;

import javax.security.auth.login.LoginException;
import java.util.Set;

public class Ryder {

    private final JDA jda;
    private CommandHandler commandHandler;
    private final Set<GatewayIntent> intents;

    public Ryder(String token, Set<GatewayIntent> intents) throws LoginException, InterruptedException {
        this.intents = intents;
        jda = JDABuilder.create(token, intents).build();
        jda.awaitReady();
    }

    public Ryder(String token, Set<GatewayIntent> intents, CommandHandler commandHandler) throws LoginException, InterruptedException {
        this.intents = intents;
        jda = JDABuilder.create(token, intents).build();
        jda.awaitReady();
        this.commandHandler = commandHandler;
    }

    public JDA getJda(){ return this.jda; }
    public Set<GatewayIntent> getIntents(){ return this.intents; }

    public CommandHandler getCommandHandler() { return this.commandHandler; }
}