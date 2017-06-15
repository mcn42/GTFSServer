/*
 * Copyright Metropolitan Transportation Authority NY
 * All Rights Reserved
 */
package org.mtahq.gtfsserver.util;

import java.util.Timer;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author mnilsen
 */
public class Utils {

    private static AppPropertyManager appProperties = null;
    private static long arrivalLimitMillis;
    private static final Timer appTimer = new Timer("AppTimer");
    private static String appDirectory = "";
    
    public static void config(String configPath)
    {
        appProperties = new AppPropertyManager(configPath);
        arrivalLimitMillis = Long.parseLong(appProperties.get(AppProperty.GTFS_UPDATE_PERIOD));
        appDirectory = configPath;
    }

    public static String getAppDirectory() {
        return appDirectory;
    }

    public static AppPropertyManager getAppProperties() {
        return appProperties;
    }
    
    public static int millisToMinutes(long millis)
    {
        return (int)TimeUnit.MILLISECONDS.toMinutes(millis);
    }

    public static Timer getAppTimer() {
        return appTimer;
    }  
    
    
    
}
