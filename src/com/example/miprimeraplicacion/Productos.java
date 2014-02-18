package com.example.miprimeraplicacion;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Productos extends Activity {
	 private ListView lista;
	 private SQLiteDatabase db;
//	 private String[] listaProductos;
	 private ArrayList<String> listaProductos = new ArrayList<String>();
	
	 
	 
	// private HipotecaCursorAdapter hipotecaAdapter ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productos);
		
		 ConsomeSQLiteHelper usdbh = new ConsomeSQLiteHelper(this, "productosDB", null, 1);	 
		 db = usdbh.getWritableDatabase();
		 Cursor c = db.rawQuery("SELECT idProducto,nombre FROM productos", null);
	
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		    	 
		     //     String id = c.getString(0);
		    	  String id = c.getString(0);
		          String nombre = c.getString(1);
		          listaProductos.add(nombre);
		       //   listaProductos[i] = nombre;
		         // Log.i(this.getClass().toString(),listaProductos[i] );
		         
		      
		          Log.i(this.getClass().toString(), "ID:"+id+"NOMBRE:"+nombre);
		     } while(c.moveToNext());
		}
		

	    
	    lista = (ListView) findViewById(R.id.liProductos);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaProductos);
		lista.setAdapter(adaptador);
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicion,long arg3) {
				String str = lista.getItemAtPosition(posicion).toString();
				// TODO Auto-generated method stub     
			
				/***************/
			
				 Cursor c = db.rawQuery("SELECT idProducto,nombre FROM productos", null);
				 SharedPreferences prefs = getSharedPreferences("PRODUCTOS",Context.MODE_PRIVATE); 
				 SharedPreferences.Editor editor = prefs.edit();


				if (c.moveToFirst()) {
				     //Recorremos el cursor hasta que no haya más registros
				     do {
				    	  String nombre = c.getString(1);
				    		// Toast.makeText(getApplicationContext(), "NOMBRE:"+nombre, Toast.LENGTH_SHORT).show();
				          if(str.equals(nombre)){
				        	  Intent intentProductos = new Intent(Productos.this, DetalleProducto.class);
							    editor.putString("NOMBRE", nombre);
							    editor.commit();
							    startActivity(intentProductos);
				        		Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_SHORT).show();
				          }
				    	 
				     } while(c.moveToNext());
				}
			    db.close();
				
				/***************/
			}
		 
		}); 
	
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.productos, menu);
		return true;
	}

}
