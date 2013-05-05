package com.example.cronococina;

//import java.util.Calendar;

import android.app.Activity;
//import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
//import android.widget.TimePicker;

public class horaElegida extends Activity {
	
	private TextView tiempoRestante;
	private ProgressBar progress;
	
	private String horaU;
	private String minutoU;
	private Long LongHoraU;
	private Long LongMinutoU;
	private Long sumaMinutos;
	
	private String horasQuedan;
	private String minutosQuedan;
	private Long segundosTotales;
	
	private CountDownTimer countDownTimer;
	private long startTime;
	private long interval = 1000; 
	private String mostrar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.esperando);
		
		//recupero de mi primera actividad los datos que necesito
		Bundle bundle=getIntent().getExtras();
		horaU = bundle.getString("horaSeleccionada");
		minutoU = bundle.getString("minutoSeleccionado");
		
		LongHoraU = Long.valueOf(horaU)*60;
		LongMinutoU = Long.valueOf(minutoU) + 1;
		sumaMinutos = (LongHoraU + LongMinutoU)*60*1000;
		
		//cambio el tiempo restante mostrado
		tiempoRestante = (TextView)findViewById(R.id.textView5);
		tiempoRestante.setText(horaU);
				
		startTime = sumaMinutos;
	
		countDownTimer = new MyCountDownTimer(startTime, interval);
			
		countDownTimer.start();
		
	}
	
	
	public class MyCountDownTimer extends CountDownTimer {

		TextView texto;
		
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
			texto = (TextView)findViewById(R.id.textView5);
		}
		
		@Override
		public void onFinish() {
			tiempoRestante.setText("00");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			segundosTotales = millisUntilFinished/1000;
			
			horasQuedan = String.valueOf(segundosTotales/3600);
			minutosQuedan = String.valueOf((segundosTotales-(3600*Integer.valueOf(horasQuedan)))/60);
			
			if(Integer.valueOf(horasQuedan)<10)
				horasQuedan = "0"+horasQuedan;

			if(Integer.valueOf(minutosQuedan)<10)
				minutosQuedan = "0"+minutosQuedan;
						
			mostrar = horasQuedan + ":"+ minutosQuedan;
						
			tiempoRestante.setText("" + mostrar);
			
		}

	}
	
	
	/*
	public class ProgressTask extends AsyncTask {

		@Override
		protected void onPreExecute() {
			// initialize the progress bar
			// Toast, popup text when the progress is begin
			//Toast.makeText(MainActivity.this, "Progress begin",
				//	Toast.LENGTH_LONG).show();
			// set maximum progress to 100.
			progress.setMax(100);

		}

		@Override
		protected void onCancelled() {
			// stop the progress back to 0
			progress.setMax(0);

		}

		@Override
		protected Void doInBackground(Integer... params) {
			// get the initial starting value
			int start = params[0];
			// increment the progress
			for (int i = start; i <= 100; i += 5) {
				try {
					boolean cancelled = isCancelled();
					// if async task is not cancelled, update the progress
					if (!cancelled) {
						publishProgress(i);
						// delay progress
						SystemClock.sleep(1000);

					}

				} catch (Exception e) {
					Log.e("Error", e.toString());
				}

			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// increment progress bar by progress value
			progress.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			// async task finished
			Log.v("Progress", "Finished");
			// Toast, popup text when the progress is finshed
			Toast.makeText(MainActivity.this, "Progress finished",
					Toast.LENGTH_LONG).show();
			// when the progress finished. the button can click again
			buttonStartProgress.setClickable(true);
		}

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// call id function from main.xml
		buttonStartProgress = (Button) findViewById(R.id.btn);
		buttonCancel = (Button) findViewById(R.id.btnCancel);
		progress = (ProgressBar) findViewById(R.id.progress);

		// create click method function for start progress
		buttonStartProgress.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProgressTask task = new ProgressTask();
				switch (v.getId()) {
				case R.id.btn:
					// execute progress starting from 10 to 100
					task.execute(10);
					// when the progressbarr still in progress. the button cant
					// click
					buttonStartProgress.setClickable(false);
					break;
				}
			}
		});

		// create click method function for cancel progress
		buttonCancel.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProgressTask task = new ProgressTask();
				switch (v.getId()) {
				case R.id.btnCancel:
					task.cancel(true);
					break;
				}

			}
		});

	}*/
		
}
