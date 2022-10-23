package se.nikoci.bot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import se.nikoci.bot.models.Request;
import se.nikoci.bot.models.Handler;
import se.nikoci.bot.models.Command;
import se.nikoci.bot.models.SlashCommand;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandHandler extends Handler {

    static Logger log = Logger.getLogger(CommandHandler.class.getName());

    //No command aliases stored here, check Request#aliases
    //Map<name, Request object (Command)>
    private final Map<String, Request> requestMap = new HashMap<>();

    private final List<CommandData> slashCommandData = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().startsWith(this.instance.getSettings().getPrefix())) return;
        if (event.getAuthor().equals(this.instance.getJda().getSelfUser())) return;

        Member member = event.getMember();
        String label = event.getMessage().getContentRaw().split(" ")[0].replaceFirst(this.instance.getSettings().getPrefix(), "");
        Command command = (Command) getCommand(label);

        if (!event.isFromType(ChannelType.TEXT) || member == null || command == null) return;

        if (member.hasPermission(command.getPermissions())){
            command.execute(event); //Execute the command
        } else {
            event.getChannel().sendMessage(this.instance.getSettings().getPermission_error()).queue();
        }

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        Member member = event.getMember();
        SlashCommand command = (SlashCommand) getCommand(event.getName());

        if (!event.isFromGuild() || member == null || command == null) return;

        if (member.hasPermission(command.getPermissions())) {
            command.execute(event);
        } else {
            event.getChannel().sendMessage(this.instance.getSettings().getPermission_error()).queue();
        }
    }

    public void addCommand(Request request){
        CommandData commandData = (request instanceof SlashCommand sc) ?
                ((sc.getCommandData() != null) ? sc.getCommandData() : null)
                : null;

        if (commandData != null) {
            this.slashCommandData.add(commandData);
            log.info("Added '" + request.getName() + "' to slash command data to be updated");
        } else log.warning("No command-data for request '" + request.getName() + "'");

        requestMap.put(request.getName(), request);
    }

    public void updateCommandData(){
        this.instance.getJda()
                .updateCommands()
                .addCommands(this.slashCommandData)
                .complete();

        log.info("Updated command data for " + slashCommandData.toArray().length + " entries");
    }

    public void removeCommand(String name){
        requestMap.keySet().forEach(cmd -> {
            if (cmd.equalsIgnoreCase(name)) requestMap.remove(cmd);
        });

        slashCommandData.forEach(commandData -> {
            if (commandData.getName().equalsIgnoreCase(name)) slashCommandData.remove(commandData);
        });
    }

    public Request getCommand(String name){
        for (var entrySet : requestMap.entrySet()){
            if (entrySet.getValue() instanceof Command command){
                for (String alias : command.getAliases()) if (
                        name.equalsIgnoreCase(alias) || name.equalsIgnoreCase(command.getName())
                ) return entrySet.getValue();
            } else if (name.equalsIgnoreCase(entrySet.getKey())) return entrySet.getValue();
        }
        return null;
    }

    public Map<String, Request> getRequestMap(){
        return this.requestMap;
    }

    public void addCommands(Request... requests){
        for (var req : requests) addCommand(req);
    }

    public void removeCommands(String... names){
        for (var name : names) removeCommand(name);
    }

}
