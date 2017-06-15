/*
 * Copyright Metropolitan Transportation Authority NY
 * All Rights Reserved
 */
package org.mtahq.gtfsserver;

import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtime.FeedEntity.Builder;
import com.google.transit.realtime.GtfsRealtime.TimeRange;
import com.google.transit.realtime.GtfsRealtime.TranslatedString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.mtahq.gtfsserver.util.AppProperty;
import org.mtahq.gtfsserver.util.Utils;

/**
 *
 * @author mnilsen
 */
public class GTFSGenerator {
    private final long gtfsUpdatePeriod = Utils.getAppProperties().getLong(AppProperty.GTFS_UPDATE_PERIOD);
    private final AtomicReference<GtfsRealtime.FeedEntity> rtData = new AtomicReference<>();
    private final List<GtfsRealtime.Alert> currentAlerts = Collections.synchronizedList(new ArrayList<GtfsRealtime.Alert>());
    
    
    private String[] situations = {"Tai-Pan!  Li Yuen's pirates approach!",
                                   "Man Overboard!",
                                   "Crew hung over; trip delayed"};
    
    public GTFSGenerator() {
    }
    
    public void start()
    {
        
    }
    
    public void stop()
    {
        
    }
    
    public void updateGTFS()
    {
        Builder b = GtfsRealtime.FeedEntity.newBuilder();
        
    }
    
    private GtfsRealtime.Alert generateAlert(String situation)
    {
        TranslatedString.Translation t = TranslatedString.Translation.getDefaultInstance();
        t = t.toBuilder().setLanguage("EN-US").setText(situation).build();
        TranslatedString ts = TranslatedString.newBuilder().addTranslation(t).build();
        GtfsRealtime.Alert.Builder b = GtfsRealtime.Alert.newBuilder();
        b.setDescriptionText(ts);
        long time = System.currentTimeMillis() / 1000;
        TimeRange tr = TimeRange.newBuilder().setStart(time).setEnd(time + 3600).build();
        b.addActivePeriod(tr);
        return b.build();
    }
    
    private GtfsRealtime.TripUpdate generateTripUpdate()
    {
        GtfsRealtime.TripUpdate.Builder b = GtfsRealtime.TripUpdate.getDefaultInstance().toBuilder();
        
        return b.build();
    }
}
