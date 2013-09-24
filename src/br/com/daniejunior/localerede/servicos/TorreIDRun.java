package br.com.daniejunior.localerede.servicos;

import br.com.danieljunior.localerede.MainActivity;
import br.com.danielljunior.localerede.save.Save;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

public class TorreIDRun extends IntentService {
	private PhoneStateListener ph;
	private TelephonyManager tm;
	private GsmCellLocation gsm;
	private int cid, lac;
	private Save save;

	public TorreIDRun() {
		super("TorreIDRun");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
	
		return super.onStartCommand(intent, flags, Service.START_STICKY);
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		save = new Save(MainActivity.DIRETORIO);
		ph = new PhoneStateListener() {
			@Override
			public void onCellLocationChanged(CellLocation location) {
				// TODO Auto-generated method stub
				gsm = (GsmCellLocation) tm.getCellLocation();
				if (gsm != null) {
					cid = gsm.getCid();
					lac = gsm.getLac();
					save.salvar("CID: " + cid + " LAC: " + lac,"torre_service.txt");
				} else {
					cid = -1;
					lac = -1;
					save.salvar("Indispoivel","torre_service.txt");
				}

				super.onCellLocationChanged(location);
			}
		};
		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(ph, PhoneStateListener.LISTEN_CELL_LOCATION);
	}

}
