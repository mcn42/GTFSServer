/*
 * Copyright Metropolitan Transportation Authority NY
 * All Rights Reserved
 */
package org.mtahq.gtfsserver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import org.mtahq.gtfsserver.util.AppProperty;
import org.mtahq.gtfsserver.util.Log;
import org.mtahq.gtfsserver.util.Utils;
import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.serialization.GtfsReader;

/**
 *
 * @author mnilsen
 */
public class GtfsStaticSource {
    private GtfsDaoImpl gtfsData = null;
    private String gtfsUrl = Utils.getAppProperties().get(AppProperty.GTFS_STATIC_URL);
    private String dataPath = Utils.getAppProperties().get(AppProperty.DATA_DIRECTORY);
    
    public GtfsStaticSource() {
        this.loadGtfsData();
    }
    
    private File retrieveFileFromUrl()
    {
        File tmpFile = null;
        try {
            URL url = new URL(this.gtfsUrl);
            InputStream in = url.openStream();
            
            tmpFile = File.createTempFile("TempGtfsStatic", ".zip",new File(this.dataPath));
            tmpFile.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tmpFile);
            
            byte[] buffer = new byte[1024];
            while(true)
            {
                int rc = in.read(buffer);
                if(rc <= 0) break;
                fos.write(buffer, 0, rc);
            }
            in.close();
            fos.close();
            
        } catch (IOException ex) {
            Log.getLog().log(Level.SEVERE, "GTFS Static retrieval error", ex);
        }
        return tmpFile;
    }
    
    private void loadGtfsData()
    {
        GtfsReader reader = new GtfsReader();
        File gtfs = this.retrieveFileFromUrl();
        if(gtfs == null) return;
        try {
            reader.setInputLocation(gtfs);
            this.gtfsData = new GtfsDaoImpl();
            reader.setEntityStore(this.gtfsData);
            reader.run();
        } catch (IOException ex) {
            Log.getLog().log(Level.SEVERE, "GTFS Static read error", ex);
        }
    }
    
}
