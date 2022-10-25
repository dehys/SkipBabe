package se.nikoci.ryder.lib.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

public class CommandHandler extends ListenerAdapter {

    public static Logger logger = LoggerFactory.getLogger(CommandHandler.class);
    private final Map<Command, Method[]> commands = new HashMap<>();

    public CommandHandler(){

        //Find all commands and store them via reflection


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
