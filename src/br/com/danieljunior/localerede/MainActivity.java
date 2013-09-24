package br.com.danieljunior.localerede;

import br.com.danieljunior.localerede.localizacao.Atualiza;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Atualiza dados;
	private TextView aux;
	public static final String DIRETORIO = "Android/data/br.com.danieljunior.localerede/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dados = new Atualiza(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void atualizar(View view) {
		dados.atualiza();
		if (dados.isDisponiveis()) {
			aux = (TextView) findViewById(R.id.gps_lat);
			aux.setText("Latitude: " + dados.getGPSLatitude());
			aux = (TextView) findViewById(R.id.gps_longi);
			aux.setText("Longitude: " + dados.getGPSLongitude());
			aux = (TextView) findViewById(R.id.gps_pre);
			aux.setText("Precisão: " + dados.getGPSPrecisao());
			aux = (TextView) findViewById(R.id.net_lat);
			aux.setText("Latitude: " + dados.getNetworkLatitude());
			aux = (TextView) findViewById(R.id.net_longi);
			aux.setText("Longitude: " + dados.getNetworkLongitude());
			aux = (TextView) findViewById(R.id.net_pre);
			aux.setText("Precisão: " + dados.getNetworkPrecisao());
			aux = (TextView) findViewById(R.id.dist);
			aux.setText("Distância entre GPS e NETWORK: "
					+ dados.getDeltaMetros());
		} else {
			aux = (TextView) findViewById(R.id.dist);
			aux.setText("Distância entre GPS e NETWORK: DADOS OBSOLETOS, ATIVE O GPS E ATUALIZE NOVAMENTE...");
		}
	}
}
