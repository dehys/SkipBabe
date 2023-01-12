package se.nikoci.ryder.lib.command;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nikoci.ryder.lib.RyderBuilder;

import java.lang.reflect.Method;
import java.util.*;

public class CommandHandler extends ListenerAdapter {

    public static Logger logger = LoggerFactory.getLogger(CommandHandler.class);
    private final Map<Command, Method[]> commands = new HashMap<>();

    @Getter @Setter private RyderBuilder instance;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().startsWith(this.instance.getBotProfile().getPrefix())) return;
        if (event.getAuthor().equals(this.instance.getJda().getSelfUser())) return;

        Member member = event.getMember();
        String label = event.getMessage().getContentRaw().split(" ")[0].replaceFirst(this.instance.getBotProfile().getPrefix(), "");
        Command command = (Command) getCommand(label);

        if (!event.isFromType(ChannelType.TEXT) || member == null || command == null) return;

        if (member.hasPermission(command.getPermissions())){
            command.execute(event); //Execute the command
        } else {
            event.getChannel().sendMessage(this.instance.getBotProfile().getPermission_error()).queue();
        }
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        super.onSlashCommand(event);
    }


    public void registerCommand(Class<?> clazz){
        if (clazz.isAnnotationPresent(Command.class)){
            Command cmd = clazz.getAnnotation(Command.class);
            List<Method> methods = new ArrayList<>();

            // Making sure we only add methods from the clazz which has either MessageReceivedEvent or SlashCommandEvent as parameters
            for (Method m : clazz.getDeclaredMethods()){
                for (Class<?> parameterType : m.getParameterTypes()) {
                    if (parameterType.getSimpleName().equalsIgnoreCase("MessageReceivedEvent")
                        || parameterType.getSimpleName().equalsIgnoreCase("SlashCommandEvent")){
                        methods.add(m);
                    }
                }
            }

            commands.put(cmd, methods.toArray(new Method[0]));
            logger.info("Command " + clazz.getSimpleName() + " registered successfully");
        } else {
            logger.error("Could not register " + clazz.getSimpleName() + " as a command. Class must be annotated with @Command");
        }
    }
}
