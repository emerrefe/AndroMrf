package com.example.cronococina;

//import java.util.Calendar;

import android.app.Activity;
//import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
//import android.util.Log;
import android.widget.TextView;
//import android.widget.TimePicker;

public class horaElegida extends Activity {
	
	private TextView tiempoRestante;
	
	private String horaU;
	private String minutoU;
	private Long LongHoraU;
	private Long LongMinutoU;
	private Long sumaMinutos;
	
	private Long horasQuedan;
	private Long minutosQuedan;
	private Long segundosTotales;
	
	private CountDownTimer countDownTimer;
	private long startTime;
	private long interval = 1000; 
	
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
			tiempoRestante.setText("0");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			/*
			segundosTotales = millisUntilFinished/1000;
			minutosQuedan = segundosTotales/60;
			if(minutosQuedan > 59)
			horasQuedan = 
		*/
			
			
			tiempoRestante.setText("" + (millisUntilFinished/1000)/60);
		}

	}
}
