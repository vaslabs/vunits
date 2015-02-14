package com.vaslabs.sdc.ui.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaslabs.sdc.sensors.HPASensorValue;
import com.vaslabs.sdc.sensors.LatitudeSensorValue;
import com.vaslabs.sdc.sensors.LongitudeSensorValue;
import com.vaslabs.sdc.sensors.MetersSensorValue;
import com.vaslabs.sdc.utils.SDConnectivity;
import com.vaslabs.sdc.utils.SkyDiver;
import com.vaslabs.sdc.utils.SkyDiverEnvironmentUpdate;
import com.vaslabs.sdc.utils.SkyDiverPersonalUpdates;
import com.vaslabs.sdc.utils.SkyDiverPositionalComparator;

import android.hardware.Sensor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SkyDiverListAdapter extends BaseAdapter implements SkyDiverEnvironmentUpdate, SkyDiverPersonalUpdates {

    private Map<String, SkyDiver> knownSkyDivers;
    private List<SkyDiver> knownSkyDiversList;
    private SkyDiver me;
    public SkyDiverListAdapter(SkyDiver me) {
        knownSkyDivers = new HashMap<String, SkyDiver>();
        this.me = me;
    }
    
    
    @Override
    public int getCount() {
        return knownSkyDivers.size();
    }

    @Override
    public Object getItem( int position ) {
        return null;
    }

    @Override
    public long getItemId( int position ) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public synchronized void onNewSkydiverInfo( SkyDiver skydiver ) {
        if (knownSkyDivers.containsKey( skydiver.getName() )) {
            onSkydiverInfoUpdate( skydiver );
        } else {
            knownSkyDivers.put( skydiver.getName(), skydiver );
        }
        
    }

    @Override
    public synchronized void onSkydiverInfoUpdate( SkyDiver skydiver ) {
        if (!this.knownSkyDivers.containsKey( skydiver )) {
            onNewSkydiverInfo( skydiver );
        } else {
            SkyDiver previouslyKnownSkyDiver = this.knownSkyDivers.get( skydiver.getName());
            if (skydiver.getConnectivityStrengthAsInt() != previouslyKnownSkyDiver.getConnectivityStrengthAsInt()) {
                onConnectivityChange( skydiver );
            } else {
                previouslyKnownSkyDiver.updatePositionInformation( skydiver.getPosition() );
                Collections.sort( this.knownSkyDiversList, new SkyDiverPositionalComparator( me )  );
                //also speed && direction which are not yet available TODO
            }
        }
    }



    @Override
    public synchronized void onConnectivityChange( SkyDiver skydiver ) {
        
        if (skydiver.getConnectivityStrengthAsInt() == SDConnectivity.CONNECTION_LOST.ordinal()) {
            onLooseConnection(skydiver);
        } else {
            SkyDiver sd = knownSkyDivers.get( skydiver.getName() );
            if (sd != null) {
                sd.setConnectivityStrength( 
                        SDConnectivity.values()[skydiver.getConnectivityStrengthAsInt()] );
            }
        }
        
    }



    @Override
    public synchronized void onLooseConnection( SkyDiver skydiver ) {
        SkyDiver sd = knownSkyDivers.get( skydiver.getName() );
        if (sd != null) {
            sd.setConnectivityStrength( SDConnectivity.CONNECTION_LOST );
            //possibly do a warning implementation here.
        }
    }


    @Override
    public synchronized void onAltitudeUpdate( MetersSensorValue hpa ) {
        me.getPosition().setAlt( hpa );
    }


    @Override
    public synchronized void onGPSUpdate( LatitudeSensorValue lat, LongitudeSensorValue lng ) {
        me.getPosition().setLat( lat );
        me.getPosition().setLng( lng );
        
    }

}