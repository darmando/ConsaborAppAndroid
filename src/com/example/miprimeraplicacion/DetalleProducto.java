package com.example.miprimeraplicacion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.Toast;

public class DetalleProducto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_producto);
		
		SharedPreferences prefs = getSharedPreferences("PRODUCTOS", Context.MODE_PRIVATE);
		String nombre = prefs.getString("NOMBRE", "");
		Toast.makeText(getApplicationContext(), "lo que trae es:"+nombre, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_producto, menu);
		return true;
	}

}
