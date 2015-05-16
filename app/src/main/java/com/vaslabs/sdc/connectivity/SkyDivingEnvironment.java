package com.vaslabs.sdc.connectivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vaslabs.sdc.UserInformation;
import com.vaslabs.sdc.sensors.BarometerListener;
import com.vaslabs.sdc.sensors.BarometerSensor;
import com.vaslabs.sdc.sensors.GPSSensor;
import com.vaslabs.sdc.sensors.GPSSensorListener;
import com.vaslabs.sdc.sensors.HPASensorValue;
import com.vaslabs.sdc.sensors.LatitudeSensorValue;
import com.vaslabs.sdc.sensors.LongitudeSensorValue;
import com.vaslabs.sdc.sensors.MetersSensorValue;
import com.vaslabs.sdc.sensors.NoBarometerException;
import com.vaslabs.sdc.ui.OnSpeechSuccessListener;
import com.vaslabs.sdc.ui.SpeechCommunicationManager;
import com.vaslabs.sdc.ui.util.SkyDiverListAdapterHelper;
import com.vaslabs.sdc.utils.SDConnectivity;
import com.vaslabs.sdc.utils.SkyDiver;
import com.vaslabs.sdc.utils.SkyDiverEnvironmentUpdate;
import com.vaslabs.sdc.utils.SkyDiverPersonalUpdates;
import com.vaslabs.sdc.utils.SkyDiverPositionalComparator;
import com.vaslabs.sdc.logs.PositionGraph;

public class SkyDivingEnvironment extends BaseAdapter implements
        OnSpeechSuccessListener, SkyDiverEnvironmentUpdate,
        SkyDiverPersonalUpdates, BarometerListener, GPSSensorListener {
    private static final String LOG_TAG = "SKYDIVING_ENVIRONMENT";
    private Map<String, SkyDiver> skydivers;
    private List<SkyDiver> skydiversList;
    private SkyDiver myself;
    private Context context;
    private static SkyDivingEnvironment environmentInstance = null;
    private SpeechCommunicationManager scm;
    private int[] colors = SkyDiverListAdapterHelper.getColors();
    private BarometerSensor barometerSensor;
    private GPSSensor gpsSensor;
    private PositionGraph positionGraph;
    protected Lock wifiResetLock;
    private final int defaultColor = SkyDiverListAdapterHelper
            .getDefaultColor();
    private WirelessBroadcastReceiver mReceiver;

    private SkyDivingEnvironment( Context context ) {
        wifiResetLock = new ReentrantLock();
        skydivers = new HashMap<String, SkyDiver>();
        this.context = context;
        UserInformation ui = UserInformation.getUserInfo( context );
        myself = new SkyDiver( ui );
        scm = SpeechCommunicationManager.getInstance();
        scm.initialiseTextToSpeech( context, this );
        skydiversList = new ArrayList<SkyDiver>();

        SkyDivingEnvironmentLogger.initLogger( context );
        positionGraph = new PositionGraph();
        registerSensors();


    }

    public synchronized static SkyDivingEnvironment getInstance( Context c ) {
        if ( environmentInstance == null ) {
            environmentInstance = new SkyDivingEnvironment( c );
        }
        return environmentInstance;
    }

    @Override
    public void onSuccess() {
        scm.getTalkingAvailable( context );

    }

    @Override
    public void onFailure() {
        // TODO warning

    }

    private void registerSensors() {
        try
        {
            barometerSensor = new BarometerSensor(this.context);
            barometerSensor.registerListener(this);
        } catch (NoBarometerException nbe) {
            //scm.getNoBarometerWarning();
        } catch (Exception e) {
            //scm.getBarometerErrorWarning();
        }
        try {
            gpsSensor = new GPSSensor(this.context);
            gpsSensor.registerListener(this);
        } catch (Exception e) {
            //scm.getGPSErrorWarning();
        }
    }

    @Override
    public synchronized void onNewSkydiverInfo( SkyDiver skydiver ) {
        if ( skydivers.containsKey( skydiver.getName() ) ) {
            onSkydiverInfoUpdate(skydiver);

        } else {
            skydivers.put( skydiver.getName(), skydiver );
            skydiversList.add( skydiver );
            Collections.sort( this.skydiversList,
                    new SkyDiverPositionalComparator( myself ) );
            SpeechCommunicationManager scm =
                    SpeechCommunicationManager.getInstance();
            scm.getProximityWarning( context );
            Log.v( LOG_TAG, "New connection: " + skydiver.toString() );
            SkyDivingEnvironmentLogger.Log("New connection: " + skydiver.toString());
        }
        this.notifyDataSetChanged();
    }

    @Override
    public synchronized void onSkydiverInfoUpdate( SkyDiver skydiver ) {
        if ( !this.skydivers.containsKey( skydiver.getName() ) ) {
            onNewSkydiverInfo(skydiver);
        } else {
            SkyDiver previouslyKnownSkyDiver =
                    this.skydivers.get( skydiver.getName() );
            if ( skydiver.getConnectivityStrengthAsInt() != previouslyKnownSkyDiver
                    .getConnectivityStrengthAsInt() ) {
                onConnectivityChange( skydiver );
            } else {
                previouslyKnownSkyDiver.updatePositionInformation( skydiver
                        .getPosition() );
                Collections.sort(this.skydiversList,
                        new SkyDiverPositionalComparator(myself));
                // also speed && direction which are not yet available TODO
            }
        }
        this.notifyDataSetChanged();
    }

    @Override
    public synchronized void onConnectivityChange( SkyDiver skydiver ) {

        if ( skydiver.getConnectivityStrengthAsInt() == SDConnectivity.CONNECTION_LOST
                .ordinal() ) {
            onLooseConnection( skydiver );
            Log.v( LOG_TAG, "Lost connection: " + skydiver.toString() );
            SkyDivingEnvironmentLogger.Log("Lost connection: " + skydiver.toString());
        } else {
            SkyDiver sd = skydivers.get( skydiver.getName() );
            if (sd == null)
                return;
            if (sd.getConnectivityStrengthAsInt() == SDConnectivity.CONNECTION_LOST.ordinal())
            {
                SpeechCommunicationManager scm =
                        SpeechCommunicationManager.getInstance();
                scm.getProximityWarning(context);
            }

            sd.setConnectivityStrength( SDConnectivity.values()[skydiver
                    .getConnectivityStrengthAsInt()] );

        }

        Collections.sort( this.skydiversList, new SkyDiverPositionalComparator(
                myself ) );

        this.notifyDataSetChanged();
    }

    @Override
    public synchronized void onLooseConnection( SkyDiver skydiver ) {
        SkyDiver sd = skydivers.get( skydiver.getName() );
        if ( sd != null ) {
            SpeechCommunicationManager.getInstance().informAboutdisconnection(
                    SDConnectivity.values()[sd.getConnectivityStrengthAsInt()],
                    context );
            sd.setConnectivityStrength( SDConnectivity.CONNECTION_LOST );
        }

        this.notifyDataSetChanged();
    }

    @Override
    public synchronized void onMyAltitudeUpdate( MetersSensorValue altitude ) {

    }

    @Override
    public synchronized void onMyGPSUpdate( LatitudeSensorValue lat,
            LongitudeSensorValue lng ) {
        myself.getPosition().setLat( lat );
        myself.getPosition().setLng( lng );

    }

    public int getOtherSkyDiversSize() {
        return this.skydivers.size();
    }

    public SkyDiver getSkyDiver( int position ) {
        return skydiversList.get(position);
    }

    @Override
    public int getCount() {
        return skydiversList.size();
    }

    @Override
    public SkyDiver getItem( int position ) {
        return skydiversList.get(position);
    }

    @Override
    public long getItemId( int position ) {
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        TextView tv = new TextView( parent.getContext() );
        int connectivity = getItem( position ).getConnectivityStrengthAsInt();
        int color =
                connectivity < colors.length ? colors[connectivity]
                        : defaultColor;
        tv.setBackgroundColor( color );
        tv.setText(getItem(position).getName());

        return tv;
    }

    public static SkyDivingEnvironment getInstance() {
        return environmentInstance;
    }

    @Override
    public void onLooseConnection( String skydiverKey ) {
        SkyDiver sd = skydivers.get( skydiverKey );
        onLooseConnection(sd);
    }

    public static String getLogFile() {
        return SkyDivingEnvironmentLogger.LOG_FILE;
    }

    @Override
    public void onHPASensorValueChange(HPASensorValue pressure, MetersSensorValue altitude) {
        myself.updatePositionInformation(altitude);
        positionGraph.registerBarometerValue(pressure, altitude);
    }

    public void writeSensorLogs() {
        FileOutputStream logStream = null;
        try {
            logStream = context.openFileOutput(PositionGraph.BAROMETER_LOG_FILE, Context.MODE_APPEND);
        } catch (FileNotFoundException fnfe) {
            return;
        }

        try {
            logStream.write(positionGraph.getBarometerData());
        } catch (IOException ioE) {

        } finally {
            if (logStream != null)
                try {
                    logStream.close();
                } catch (IOException e) {

                }
        }

        try {
            logStream = context.openFileOutput(PositionGraph.GPS_LOG_FILE, Context.MODE_APPEND);
        } catch (FileNotFoundException fnfe) {
            return;
        }

        try {
            logStream.write(positionGraph.getGPSData());
        } catch (IOException ioE) {

        } finally {
            if (logStream != null)
                try {
                    logStream.close();
                } catch (IOException e) {

                }
        }

    }

    public List<String> getBarometerSensorLogsLinesUncompressed() {
        FileInputStream logStream = null;
        try {
            logStream = context.openFileInput(PositionGraph.BAROMETER_LOG_FILE);
        } catch (FileNotFoundException e) {
            return null;
        }

        try {
            List<String> lines = new ArrayList<String>();
            int result = 0;
            byte[] data = new byte[12];
            while (result >= 0) {
                result = logStream.read(data, 0, 12);
                ByteBuffer bf = ByteBuffer.wrap(data);
                long timestamp = bf.getLong();
                float meterValue = bf.getFloat();
                lines.add(String.valueOf(timestamp) + ":" + String.valueOf(meterValue));

            }

            return lines;
        } catch (IOException ioe) {
            return null;
        }
    }


    public List<String> getGPSSensorLogsLinesUncompressed() {
        FileInputStream logStream = null;
        try {
            logStream = context.openFileInput(PositionGraph.GPS_LOG_FILE);
        } catch (FileNotFoundException e) {
            return null;
        }

        try {
            List<String> lines = new ArrayList<String>();
            int result = 0;
            byte[] data = new byte[24];

            while (result >= 0) {
                //first 8 bytes represent timestamp

                result = logStream.read(data, 0, 24);
                if (result < 0)
                    break;
                ByteBuffer bf = ByteBuffer.wrap(data);

                long timestamp = bf.getLong();
                double latValue = bf.getDouble();
                double lngValue = bf.getDouble();
                lines.add(String.valueOf(timestamp) + ":" + String.valueOf(latValue) + ',' +
                    String.valueOf(lngValue));

            }

            return lines;
        } catch (IOException ioe) {
            return null;
        }
    }

    @Override
    public void onLatLngChange(LatitudeSensorValue lat, LongitudeSensorValue lng) {
        positionGraph.registerGPSValue(lat, lng);
    }

    public void registerWirelessManager(WirelessBroadcastReceiver receiver) {
        this.mReceiver = receiver;
    }

}
