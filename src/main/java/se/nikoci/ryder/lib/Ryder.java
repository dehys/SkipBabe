package se.nikoci.ryder.lib;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import se.nikoci.ryder.lib.command.CommandHandler;

@Data
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Ryder {

    @NonNull private JDA jda;
    @NonNull private RyderBuilder ryderBuilder;

    private CommandHandler commandHandler;

}
