package se.nikoci.ryder.lib.command;

import lombok.Getter;
import net.dv8tion.jda.api.Permission;

import java.lang.reflect.Method;

class FinalCommand {
    @Getter private final String name;
    @Getter private final String description;
    @Getter private final Permission[] permissions;
    @Getter private final CommandType type;
    @Getter private final Method[] executableMethods;

    public FinalCommand(String name, String description, Permission[] permissions, CommandType type, Method... executableMethods){
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.type = type;
        this.executableMethods = executableMethods;
    }
}