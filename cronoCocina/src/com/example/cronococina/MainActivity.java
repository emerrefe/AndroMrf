package com.example.cronococina;

import com.example.cronococina.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	TimePicker timePicker1;
	horaElegida a;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
		timePicker1.setIs24HourView(true);
		timePicker1.setCurrentHour(00);
		timePicker1.setCurrentMinute(00);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	public void btnComenzar (View view){
			
		Intent i = new Intent(this, horaElegida.class);
	
		i.putExtra("horaSeleccionada", timePicker1.getCurrentHour().toString());
		i.putExtra("minutoSeleccionado", timePicker1.getCurrentMinute().toString());		
		
		//Lanzar la segunda Activity
		startActivity(i);
		
	}
	
}
