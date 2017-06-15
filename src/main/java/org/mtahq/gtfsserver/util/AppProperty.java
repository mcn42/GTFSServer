/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mtahq.gtfsserver.util;

/**
 *
 * @author michaeln
 */
public enum AppProperty {
    DEVICE_ID("12345"),
    GTFS_UPDATE_PERIOD("60000"),
    CLEANUP_POLLING_PERIOD("120000"),
    EVENT_EXPIRATION_PERIOD("300000"),
    NETWORK_TIMEOUT("15000"),
    DATA_DIRECTORY("/Users/mnilsen/gtfs/data"),
    LOG_DIRECTORY("/Users/mnilsen/gtfs/logs"), 
    STATION_FILE_NAME("stations.csv"),
    READING_HISTORY_SIZE("100"),
    SUPERVISOR_IP_ADDRESS("172.24.0.1"),
    APPLICATION_PORT("8080"),
    APPLICATION_HOME_PATH("/Users/mnilsen/gtfs"),
    GTFS_STATIC_URL("");
    
    private final String defaultValue;
    
    private AppProperty(String defaultVal)
    {
        this.defaultValue = defaultVal;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
