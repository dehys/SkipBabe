package se.nikoci.ryder.bot.commands.division2;

import se.nikoci.ryder.bot.commands.division2.objects.PlatformInfo;
import se.nikoci.ryder.bot.commands.division2.objects.Segments;
import se.nikoci.ryder.bot.commands.division2.objects.UserInfo;

public class PlayerStatsData{
    PlatformInfo platformInfo;
    UserInfo userInfo;
    Segments[] segments;
    private String expiryDate;

    public PlatformInfo getPlatformInfo(){ return platformInfo; }
    public void setPlatformInfo(PlatformInfo platformInfo){ this.platformInfo = platformInfo; }

    public UserInfo getUserInfo(){ return userInfo; }
    public void setUserInfo(UserInfo userInfo){ this.userInfo = userInfo; }

    public Segments getSegments(){ return segments[0]; }
    public void setSegments(Segments[] segments){ this.segments = segments; }

    public String getExpiryDate(){ return expiryDate; }
    public void setExpiryDate(String expiryDate){ this.expiryDate = expiryDate; }
}