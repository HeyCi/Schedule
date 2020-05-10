package org.demo.schedule;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver
{

    NotificationUtils notificationUtils;
    String taskName;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        SharedPreferences prefs = context.getSharedPreferences("task", Context.MODE_PRIVATE);
        taskName = prefs.getString("name", null);
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "tag:a");
        wl.acquire();

        // Put here YOUR code.
        NotificationUtils.createNotification(context, taskName);

        wl.release();
    }

    public void setAlarm(Context context, long trigger)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        //am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2500, pi);
        am.set(AlarmManager.RTC_WAKEUP, trigger, pi);
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public Alarm() {
        super();
        notificationUtils = new NotificationUtils();
    }
}
