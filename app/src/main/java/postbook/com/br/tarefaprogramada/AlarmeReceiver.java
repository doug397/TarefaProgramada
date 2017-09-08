package postbook.com.br.tarefaprogramada;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by douglas on 30/08/2017.
 */

public class AlarmeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

  /*    if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
            Intent servico=new Intent(context,Servico.class);
            context.startActivity(servico);
           // gerarNotificacao( context, intent,"Notificacao","Alarme Ligado","Testando"+new Date());
        }*/
     /*   Intent servico=new Intent(context,Servico.class);
        context.startActivity(servico);*/

   boolean alarmeativo=(PendingIntent.getBroadcast(context,0,new Intent("ALARME_DISPARADO"),PendingIntent.FLAG_NO_CREATE)==null);

        if(alarmeativo) {
          intent = new Intent("ALARME_DISPARADO");
            PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 02);
            calendar.set(Calendar.MINUTE,40);
            calendar.set(Calendar.SECOND,00);
            long inicio = calendar.getTimeInMillis();

            AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, p);

        }

        gerarNotificacao(context, intent, "Notificacao", "Alarme Ligado", "Testando" + new Date() + "Receiver");

    /*    if("ALARME_DISPARADO".equals(intent.getAction())) {
            gerarNotificacao(context, intent, "Notificacao", "Alarme Ligado", "Testando" + new Date() + "Receiver");
            Log.i("BroadCast","Dentro Intente");
        }*/

    }

    public void gerarNotificacao(Context context,Intent intent,CharSequence ticker,CharSequence titulo,CharSequence descricao){
        NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p=PendingIntent.getActivity(context,0,intent,0);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher_round));
        builder.setContentIntent(p);
        builder.setStyle(new NotificationCompat.BigTextStyle());
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(descricao));


        Notification n=builder.build();
        nm.notify(R.mipmap.ic_launcher,n);
    }
}
