package br.com.danieljunior.localerede.localizacao;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Atualiza implements LocationListener {
	private LocationManager lm;
	private double deltaMetros=-1;
	private Context context;
	private Location gps, network;
	private boolean disponiveis;

	public Atualiza(Context c) {
		context = c;
	}
	public void atualiza() {
		lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
			gps = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			lm.removeUpdates(this);
		}
		lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
			network = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			lm.removeUpdates(this);
		}
		if (gps==null || network==null) {
			disponiveis = false;
		}else{
			disponiveis = true;
			deltaMetros = gps.distanceTo(network);
		}
	}

	public double getDeltaMetros() {
		return deltaMetros;
	}

	public boolean isDisponiveis() {
		return disponiveis;
	}

	public double getGPSLatitude() {
		return gps.getLatitude();
	}

	public double getGPSLongitude() {
		return gps.getLongitude();
	}

	public double getGPSPrecisao() {
		return gps.getAccuracy();
	}

	public double getNetworkLatitude() {
		return network.getLatitude();
	}

	public double getNetworkLongitude() {
		return network.getLongitude();
	}

	public double getNetworkPrecisao() {
		return network.getAccuracy();
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
