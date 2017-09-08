package postbook.com.br.tarefaprogramada;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by douglas on 30/08/2017.
 */

public class Servico extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        boolean alarmeativo=(PendingIntent.getBroadcast(this,0,new Intent("ALARME_DISPARADO"),PendingIntent.FLAG_NO_CREATE)==null);

        if(alarmeativo) {
            intent = new Intent("ALARME_DISPARADO");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 02);
            calendar.set(Calendar.MINUTE,40);
            calendar.set(Calendar.SECOND,00);
            long inicio = calendar.getTimeInMillis();

            AlarmManager alarme = (AlarmManager) this.getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_FIFTEEN_MINUTES, p);
        }
        // return (super.onStartCommand(intent, flags, startId));
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
