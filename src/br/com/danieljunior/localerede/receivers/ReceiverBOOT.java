package br.com.danieljunior.localerede.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class ReceiverBOOT extends BroadcastReceiver {
	private PendingIntent pi;
	private AlarmManager am;
	private int minuto = 1000*60;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		pi = PendingIntent.getService(context, 0, new Intent("br.com.danieljunior.localerede.servicos.TorreIDRun"), 0);
		am = (AlarmManager) (context.getSystemService(Context.ALARM_SERVICE));
		am.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 
				minuto*20, pi );
		Intent service = new Intent(context,
				br.com.daniejunior.localerede.servicos.TorreIDRun.class);
		context.startService(service);
	}
}
