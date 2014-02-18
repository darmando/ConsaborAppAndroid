package com.example.miprimeraplicacion;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	 private ListView list;
	 private String[] sistemas = {"Nosotros", "Productos", "Contacto"};
	
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* CREAMOS la TABLA e INSERTAMOS */
	/*
		 Log.i(this.getClass().toString(), "Creando base de datos");
		 //SQLiteDatabase db = new SQLiteDatabase();
		
		db.execSQL( "CREATE TABLE productos(" +
		          " idProducto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
		          " nombre VARCHAR(30)  NOT NULL, " +
		          " precio FLOAT  NOT NULL, " +
		          " detalle TEXT NOT NULL)" ); 
		
	
		 
		   db.execSQL( "CREATE UNIQUE INDEX nombre ON productos(nombre ASC)" );
		 
		   Log.i(this.getClass().toString(), "Tabla nombre creada");
		 
		
		   db.execSQL("INSERT INTO productos(nombre,precio,detalle) VALUES('Consomate Pollo',20.50,'Este consomate es el mejor de todos...')");
		   db.execSQL("INSERT INTO productos(nombre,precio,detalle) VALUES('Consome Res',20.00,'Este consome saber rico...')");
		   db.execSQL("INSERT INTO productos(nombre,precio,detalle) VALUES('Consome de camarón',31.00,'mmm consome de camarón...')");
		   
		   Log.i(this.getClass().toString(), "Datos iniciales productos insertados");
		 
		   Log.i(this.getClass().toString(), "Base de datos creada");
		*/
		//***********************************
		
		ConsomeSQLiteHelper usdbh = new ConsomeSQLiteHelper(this, "productosDB", null, 1);	 
        SQLiteDatabase db = usdbh.getWritableDatabase();
        usdbh.onUpgrade(db, 1, 1);
        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
                //Insertamos los datos en la tabla Usuarios
        	 try{  
        	//   db.execSQL("CREATE UNIQUE INDEX nombre ON productos(nombre ASC)" );
        	   db.execSQL("INSERT INTO productos(nombre,precio,detalle) VALUES('Consomate Pollo',20.50,'Este consomate es el mejor de todos...')");
     		   db.execSQL("INSERT INTO productos(nombre,precio,detalle) VALUES('Consome Res',20.00,'Este consome saber rico...')");
     		   db.execSQL("INSERT INTO productos(nombre,precio,detalle) VALUES('Consome de camarón',31.00,'mmm consome de camarón...')");
     		   Log.i(this.getClass().toString(), "Datos iniciales productos insertados"); 
   		       Log.i(this.getClass().toString(), "Base de datos creada");
        	 }catch (Exception e){
        	    
        	      Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        	 }
 
            //Cerramos la base de datos
            db.close();
        }
        
        
        
		
		list = (ListView) findViewById(R.id.liMenu);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sistemas);
		
		list.setAdapter(adaptador);
        // Sets the data behind this ListView
        
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int elemento,long arg3) {
				// TODO Auto-generated method stub
			     
			      SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE); 
			      SharedPreferences.Editor editor = prefs.edit();
				switch(elemento){
				case 0:
				   Toast.makeText(getApplicationContext(), "Nosotros", Toast.LENGTH_SHORT).show();
				      Intent intent = new Intent(MainActivity.this, SegundaPantalla.class);
	                  //Creamos la información a pasar entre actividades
	                /*
				      Bundle b = new Bundle();
	                  b.putString("TITULO", "Nosotros");
	                  //Añadimos la información al intent
	                  intent.putExtras(b);
	                  */
	                  //Iniciamos la nueva actividad
	                 // startActivity(intent);
				   
				      editor.putString("TITULO", "Nosotros");
				      editor.commit();
				      startActivity(intent);	
				    		
				   break;
				case 1:
					  Intent intentProductos = new Intent(MainActivity.this, Productos.class);
				      editor.putString("TITULO", "Productos");
				      editor.commit();
				      startActivity(intentProductos);
				   break;
				case 2:
					Toast.makeText(getApplicationContext(), "Contacto", Toast.LENGTH_SHORT).show();
				   break;
				default:
					Toast.makeText(getApplicationContext(), "Error de Selección", Toast.LENGTH_SHORT).show();
				   break;
				}
				
				
			}
		 
		}); 
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		
	}

}
