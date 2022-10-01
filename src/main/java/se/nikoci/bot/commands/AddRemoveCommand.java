package se.nikoci.bot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import se.nikoci.bot.Main;
import se.nikoci.bot.models.Command;

import java.util.Collection;
import java.util.Set;

public class AddRemoveCommand implements Command {
    @Override
    public void execute(MessageReceivedEvent event) {
        StringBuilder sb = new StringBuilder();

        for (var entrySet : Main.ryder.getRequestMap().entrySet()){
            sb
                    .append("**")
                    .append(entrySet.getKey())
                    .append("** points to -> ``")
                    .append(entrySet.getValue())
                    .append("`` \n");
            if (entrySet.getValue() instanceof Command command){
                sb.append("```\nALIASES:\n");
                for (String alias : command.getAliases()) sb.append("\t- ").append(alias).append("\n");
                sb.append("```");
            }
        }

        event.getChannel().sendMessage(sb.toString()).queue();
    }

    @Override
    public @NotNull String getName() {
        return "command";
    }

    @Override
    public @NotNull String getDescription() {
        return "Manages commands";
    }

    @Override
    public String[] getAliases() {
        return new String[]{
                "cmd"
        };
    }

    @Override
    public Collection<Permission> getPermissions() {
        return Set.of(
                Permission.ADMINISTRATOR
        );
    }
}
