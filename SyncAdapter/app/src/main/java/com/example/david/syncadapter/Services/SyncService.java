package com.example.david.syncadapter.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.david.syncadapter.SyncAdapter.SyncAdapter;

/**
 * Created by david on 31/05/16.
 */
public class SyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static SyncAdapter sSyncAdapter = null;

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    /**
     * Nuevos sync requests va a ser mandados directamente al SyncAdapter usando este canal.
     * @param intent Intent que invoca.
     * @return Binder handle for {@link SyncAdapter}
     */
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
