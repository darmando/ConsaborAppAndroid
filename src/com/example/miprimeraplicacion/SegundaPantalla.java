package com.example.miprimeraplicacion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.Toast;

public class SegundaPantalla extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.segundapantalla);
		
		/*
		  //Recuperamos la información pasada en el intent
           Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
      //  lb.setText("Hola " + bundle.getString("NOMBRE"));
		 Toast.makeText(getApplicationContext(), bundle.getString("NOMBRE"), Toast.LENGTH_SHORT).show();
		 */
		SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
		String titulo = prefs.getString("TITULO", "");
		Toast.makeText(getApplicationContext(), "lo que trae es:"+titulo, Toast.LENGTH_SHORT).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.segunda_pantalla, menu);
		return true;
	}

}
