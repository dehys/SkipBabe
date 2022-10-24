package se.nikoci.ryder.lib;

public class Settings {

    private String prefix = "!";
    private String permission_error = "You do not have permissions to perform this action!";
    private boolean addingAliases = true;

    public Settings(){}

    public Settings(String prefix, String permission_error, boolean addingAliases){
        this.prefix = prefix;
        this.permission_error = permission_error;
        this.addingAliases = addingAliases;
    }

    public String getPrefix() {
        return prefix;
    }

    public Settings setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getPermission_error() {
        return permission_error;
    }

    public Settings setPermission_error(String permission_error) {
        this.permission_error = permission_error;
        return this;
    }

    public boolean isAddingAliases() {
        return addingAliases;
    }

    public Settings setAddingAliases(boolean addingAliases) {
        this.addingAliases = addingAliases;
        return this;
    }
}
