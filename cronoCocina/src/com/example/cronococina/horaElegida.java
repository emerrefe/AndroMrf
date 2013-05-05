package com.example.cronococina;

//import java.util.Calendar;

import android.app.Activity;
//import android.app.TimePickerDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
//import android.widget.TimePicker;

public class horaElegida extends Activity {
	
	private TextView tiempoRestante;
	private MediaPlayer alarma;
	
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
		
		alarma = MediaPlayer.create(this,R.raw.CLOCK1);
		
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
			alarma.start();
			
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
	
	
		
}
