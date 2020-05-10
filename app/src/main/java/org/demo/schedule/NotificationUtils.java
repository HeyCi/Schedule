package org.demo.schedule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationUtils {

    private static void initChannel(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("scheduleChannel", "scheduleChannel", NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400});
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(channel);
    }

    public static void createNotification(Context context, String nom_tache) {
        initChannel(context);
        Intent intent = new Intent(context, KidTaskActivity.class);
        intent.putExtra("nom_tache", nom_tache);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "scheduleChannel");
        builder.setContentTitle("Tâche à faire")
                .setContentText(nom_tache)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notif);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(29, builder.build());

        
        SharedPreferences prefs = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userId = prefs.getString("tel", null);
        Database bdd = new Database();
        bdd.setAlarmManager(userId, context);
    }
}
