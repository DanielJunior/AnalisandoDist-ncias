package br.com.danieljunior.localerede.receivers;

import br.com.danieljunior.localerede.MainActivity;
import br.com.danieljunior.localerede.localizacao.Atualiza;
import br.com.danielljunior.localerede.save.Save;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class ReceiverGPS extends BroadcastReceiver {
	private Atualiza atual;
	private Save save;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		LocationManager lm  = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		atual = new Atualiza(context);
		atual.atualiza();
		save = new Save(MainActivity.DIRETORIO);
		save.salvar("Delta em metros: " + atual.getDeltaMetros(),
				  "deltametros.txt");
		}
		Toast.makeText(context, "GPS!!!", Toast.LENGTH_SHORT).show();
	}

}
