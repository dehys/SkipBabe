package se.nikoci.ryder.bot.commands.division2.objects;

public class PlatformInfo {
    String platformSlug;
    String platformUserId;
    String platformUserHandle;
    String platformUserIdentifier;
    String avatarUrl;


    public String getPlatformSlug() {
        return platformSlug;
    }

    public void setPlatformSlug(String platformSlug) {
        this.platformSlug = platformSlug;
    }

    public String getPlatformUserId() {
        return platformUserId;
    }

    public void setPlatformUserId(String platformUserId) {
        this.platformUserId = platformUserId;
    }

    public String getPlatformUserHandle() {
        return platformUserHandle;
    }

    public void setPlatformUserHandle(String platformUserHandle) {
        this.platformUserHandle = platformUserHandle;
    }

    public String getPlatformUserIdentifier() {
        return platformUserIdentifier;
    }

    public void setPlatformUserIdentifier(String platformUserIdentifier) {
        this.platformUserIdentifier = platformUserIdentifier;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
