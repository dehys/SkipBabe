package se.nikoci.ryder.lib;

import lombok.*;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import se.nikoci.ryder.lib.command.CommandHandler;

import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BotProfile {

    @NotNull private String botToken;
    @NotNull private Set<GatewayIntent> botGatewayIntents;
    @NotNull private CommandHandler botCommandHandler;

    private OnlineStatus botOnlineStatus = OnlineStatus.ONLINE;
    private Activity botActivity = Activity.listening("Basshunter Dota");


    private String prefix = "!";
    private String permission_error = "You do not have permissions to perform this action!";

}
