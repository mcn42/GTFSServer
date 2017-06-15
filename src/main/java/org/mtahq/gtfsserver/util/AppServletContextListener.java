/*
 * Copyright Metropolitan Transportation Authority NY
 * All Rights Reserved
 */
package org.mtahq.gtfsserver.util;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 *
 * @author mnilsen
 */

@WebListener
public class AppServletContextListener implements ServletContextListener {
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if(initialized.get()) return;
        try {
            InitialContext ic = new InitialContext();
            String appPath = (String) ic.lookup("java:comp/env/APPLICATION_DIRECTORY");
            String fileName = (String) ic.lookup("java:comp/env/CONFIG_FILENAME");
            sce.getServletContext().log(String.format("App directory is '%s', file name is %s", appPath,fileName));
            Utils.config(appPath + "/" + fileName);
            Log.getLog().info("GTFS Server application starting up...");
            initialized.set(true);
        } catch (NamingException ex) {
            Logger.getLogger(AppServletContextListener.class.getName()).log(Level.SEVERE, "App initialization error", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("GTFS Server application shutting down...");
        if(Utils.getAppTimer() != null) Utils.getAppTimer().cancel();
        initialized.set(false);
    }
    
}
