/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mtahq.gtfsserver.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author michaeln
 */
public class Log {
    private static final Logger log = Logger.getLogger("org.mtahq.gtfsserver");

    private Log() {
    }
    
    static
    {
        configure();
    }
    
    private static void configure()
    {
        log.setLevel(Level.ALL);
        try {
            String path = Utils.getAppProperties().get(AppProperty.LOG_DIRECTORY);
            File dir = new File(path);
            if(!dir.exists())
            {
                log.info(String.format("Creating Log directory at %s",dir.getCanonicalPath()));
                dir.mkdirs();
            }
            FileHandler fh = new FileHandler(path + "/gtfs-server.log",200000,6,true);
            fh.setFormatter(new SimpleFormatter());      
            fh.setLevel(Level.ALL);
            log.addHandler(fh);
            fh = new FileHandler(path + "/gtfs-server-error.log",200000,6,true);
            fh.setFormatter(new SimpleFormatter());      
            fh.setLevel(Level.WARNING);
            log.addHandler(fh);
        } catch (IOException e) {
            log.log(Level.SEVERE,"Failed to add logging FileHandler",e);
        }
        
     
    }

    public static Logger getLog() {
        return log;
    }

}
