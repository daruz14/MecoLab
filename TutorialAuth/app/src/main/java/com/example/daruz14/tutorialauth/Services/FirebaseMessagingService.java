package com.example.daruz14.tutorialauth.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.daruz14.tutorialauth.MainActivity;
import com.example.daruz14.tutorialauth.R;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by daruz14 on 23-08-16.
 */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pedingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("chatUc")
                .setContentText(message)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentIntent(pedingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());


    }
}
