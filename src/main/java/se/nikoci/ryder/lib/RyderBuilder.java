package se.nikoci.ryder.lib;

import lombok.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Set;

@Builder
@RequiredArgsConstructor
public class RyderBuilder {

    @NonNull protected String botToken;
    @NonNull protected Set<GatewayIntent> gatewayIntents;

    protected OnlineStatus onlineStatus = OnlineStatus.ONLINE;
    protected Activity activity = Activity.watching("you");
    protected String prefix = "";

    public Ryder build() throws LoginException, InterruptedException {
        JDA jda = JDABuilder.create(botToken, gatewayIntents).build();
        jda.awaitReady();
        return new Ryder(jda, this);
    }
}