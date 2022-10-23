package se.nikoci.bot.commands.division2.objects;

import java.util.ArrayList;

public class UserInfo {
    private String userId = null;
    private boolean isPremium;
    private boolean isVerified;
    private boolean isInfluencer;
    private boolean isPartner;
    private String countryCode = null;
    private String customAvatarUrl = null;
    private String customHeroUrl = null;
    ArrayList<Object> socialAccounts = new ArrayList<>();
    private String pageviews = null;
    private boolean isSuspicious;


    // Getter Methods

    public String getUserId() {
        return userId;
    }

    public boolean getIsPremium() {
        return isPremium;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public boolean getIsInfluencer() {
        return isInfluencer;
    }

    public boolean getIsPartner() {
        return isPartner;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCustomAvatarUrl() {
        return customAvatarUrl;
    }

    public String getCustomHeroUrl() {
        return customHeroUrl;
    }

    public String getPageviews() {
        return pageviews;
    }

    public boolean getIsSuspicious() {
        return isSuspicious;
    }

    // Setter Methods

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIsPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void setIsInfluencer(boolean isInfluencer) {
        this.isInfluencer = isInfluencer;
    }

    public void setIsPartner(boolean isPartner) {
        this.isPartner = isPartner;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCustomAvatarUrl(String customAvatarUrl) {
        this.customAvatarUrl = customAvatarUrl;
    }

    public void setCustomHeroUrl(String customHeroUrl) {
        this.customHeroUrl = customHeroUrl;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public void setIsSuspicious(boolean isSuspicious) {
        this.isSuspicious = isSuspicious;
    }
}
