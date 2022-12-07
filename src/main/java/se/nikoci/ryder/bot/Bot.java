package se.nikoci.ryder.bot;

import net.dv8tion.jda.api.JDA;
import se.nikoci.ryder.lib.BotProfile;
import se.nikoci.ryder.lib.Ryder;
import se.nikoci.ryder.lib.RyderBuilder;
import se.nikoci.ryder.lib.command.CommandHandler;

import javax.security.auth.login.LoginException;
import java.util.Set;

public class Bot {

    public static void main(String[] args) throws LoginException, InterruptedException {

        Ryder ryder = new RyderBuilder("", Set.of())
                .se
                .build();

    }

}