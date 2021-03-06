package br.ufop.wagner.mybabywagner;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchNotification extends AppCompatActivity {

    public static NotificationCompat.Builder mBuilder;
    public static NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        //Setup a notification channel
        String id = "channel_id";
        CharSequence name = "channel_name";
        String description = "channel_description";

        int importance = NotificationManager.IMPORTANCE_LOW;

        if(Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new
                    NotificationChannel(id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
            mNotificationManager.
                    createNotificationChannel(mChannel);
        }

        //Intent that is gonna be executed when
        // the user clicks on the notification
        Intent it = new Intent(this,
                MyBaby.class);
        PendingIntent p = PendingIntent.
                getActivity(this, 0, it, 0);

        //Creates the notification
        mBuilder = new
                NotificationCompat.Builder(
                getApplicationContext())
                .setSmallIcon(R.drawable.mednotificacao)
                .setContentTitle("Notificação de Medicamento")
                .setContentText("Hora de Tomar Remédio!")
                .setOngoing(false) //Allow the user to cancel the notification
                .setContentIntent(p) //Intent that is going to be launched when the user clicks on the notification
                .setChannelId(id);
        mNotificationManager.notify(0,
                mBuilder.build());
        finish();



    }
}
