package br.com.danielljunior.localerede.save;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class Save {

	private RandomAccessFile raf;
	private File root,file;
	private Date data;
	private String info,diretorio;

	public Save(String diretorio){
		this.diretorio = diretorio;
	}
	public void salvar(String ifs, String nArq) {
		if (isExternalStorageWritable()) {
			try {
				// crio o arquivo ou abro se ele jah existe
				root = Environment.getExternalStorageDirectory();
				if(root.canWrite()){
					file = new File(root, diretorio);
				}
				file.mkdirs();
				root = file;
				file = new File(root, nArq);
				raf = new RandomAccessFile(file, "rw");
				data = new Date();
				// formato da data
				SimpleDateFormat formatadorBrasil = new SimpleDateFormat(
						"dd-MM-yyyy HH:mm:ss");
				// string que irei escrever no arquivo
				info = "Data: " + formatadorBrasil.format(data) + "\n" + ifs;
				// vou para o final do arquivo
				raf.seek(raf.length());
				// escrevo no arquivo
				raf.writeBytes(info + "\n");
				// raf.writeUTF(info + "\n");
				// fecho o arquivo
				raf.close();
			} catch (IOException e) {
				Log.d("ERRO_DE_ESCRITA", e.toString());
			}
		}
	}

	/* Checks if external storage is available for read and write */
	private boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	@SuppressWarnings("unused")
	private boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

}
