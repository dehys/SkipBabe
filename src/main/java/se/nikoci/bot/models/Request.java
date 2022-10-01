package se.nikoci.bot.models;

import net.dv8tion.jda.api.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public interface Request {

    @NotNull String getName();
    @NotNull default String getDescription(){ return "No description provided for this command"; }
    default Collection<Permission> getPermissions() {
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.MESSAGE_SEND);
        return permissions;
    }
}
