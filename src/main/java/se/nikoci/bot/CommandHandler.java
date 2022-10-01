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

public class CommandHandler extends Handler {

    //No command aliases stored here, check Request#aliases
    //Map<name, Request object (Command)>
    private final Map<String, Request> requestMap = new HashMap<>();

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
        List<CommandData> slashCommandData = (request instanceof SlashCommand slashCommand) ?
                List.of(slashCommand.getCommandData()) : null;

        if (slashCommandData != null) this.instance.getJda()
                .updateCommands()
                .addCommands(slashCommandData)
                .complete();

        requestMap.put(request.getName(), request);
    }

    public void removeCommand(String name){
        requestMap.keySet().forEach(cmd -> {
            if (cmd.equalsIgnoreCase(name)) requestMap.remove(cmd);
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
