package se.nikoci.ryder.lib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import se.nikoci.ryder.lib.command.CommandHandler;

import java.util.Set;

@Data
@RequiredArgsConstructor(staticName = "create")
@AllArgsConstructor(staticName = "create")
public class Ryder {
    private JDA jda = null;

    @NotNull private String token;
    @NotNull private Set<GatewayIntent> gatewayIntents;

    private CommandHandler commandHandler = CommandHandler.create(this);

    private OnlineStatus onlineStatus = OnlineStatus.ONLINE;
    private Activity activity = Activity.listening("Basshunter Dota");

    private String prefix = "!";
    private String permission_error = "You do not have permissions to perform this action!";

    public Ryder online() throws InterruptedException {
        if (jda != null) {
            //there is already a bot instance
            System.out.println("there is already a bot instance");
            return this;
        }

        jda = JDABuilder
                .create(getToken(), getGatewayIntents())
                .setActivity(activity)
                .build();
        jda.awaitReady();

        if (commandHandler != null) jda.addEventListener(commandHandler);
        return this;
    }

    public void offline() {
        if (jda != null) {
            this.jda.shutdownNow();
        } else {
            System.out.println("bot is already offline");
        }
    }

}
