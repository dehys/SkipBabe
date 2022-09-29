package se.nikoci.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import se.nikoci.bot.models.Command;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Set;

public class Ryder {

    private final JDA jda;
    private CommandHandler commandHandler;
    private final Set<GatewayIntent> intents;
    private final Settings settings;

    public Ryder(String token, Set<GatewayIntent> intents, Settings settings) throws LoginException, InterruptedException {
        this.intents = intents;
        this.settings = settings;
        jda = JDABuilder.create(token, intents).build();
        jda.awaitReady();
    }

    public Ryder(String token, Set<GatewayIntent> intents, CommandHandler commandHandler, Settings settings) throws LoginException, InterruptedException {
        this.intents = intents;
        this.settings = settings;
        jda = JDABuilder.create(token, intents).build();
        jda.awaitReady();
        setCommandHandler(commandHandler);
    }

    public JDA getJda(){ return this.jda; }
    public List<Command> getCommands(){ return getCommandHandler().getCommands(); }
    public Set<GatewayIntent> getIntents(){ return this.intents; }

    public CommandHandler getCommandHandler() { return this.commandHandler; }
    public void setCommandHandler(CommandHandler commandHandler) {
        commandHandler.setInstance(this).subscribe();
        this.commandHandler = commandHandler;
    }

    public Settings getSettings(){
        return this.settings;
    }

}
