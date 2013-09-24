package br.com.danieljunior.localerede.receivers;

import br.com.danieljunior.localerede.MainActivity;
import br.com.danielljunior.localerede.save.Save;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

public class ReceiverREDE extends BroadcastReceiver {
	private String info;
	private int cid,lac;
	private Save save;
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		TelephonyManager tm = (TelephonyManager)arg0.getSystemService(Context.TELEPHONY_SERVICE) ;
		CellLocation.requestLocationUpdate();
		GsmCellLocation gsm =(GsmCellLocation) tm.getCellLocation();		
		if(gsm!=null){
		cid = gsm.getCid();
		lac  = gsm.getLac();
		info = "CID: "+cid+"LAC: "+lac;
		}else{
			info = "Indisponivel";
		}
		save = new Save(MainActivity.DIRETORIO);
		save.salvar(info, "receiver_rede.txt");
	}

}
