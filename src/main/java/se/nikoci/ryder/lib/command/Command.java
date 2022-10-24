package se.nikoci.ryder.lib.command;

import net.dv8tion.jda.api.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

    String name();
    String description() default "This command does not have any information";
    Permission[] permissions() default {Permission.MESSAGE_SEND};
    CommandType[] type();

}
