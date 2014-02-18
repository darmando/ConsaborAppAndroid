package com.example.miprimeraplicacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 

public class ConsomeSQLiteHelper extends SQLiteOpenHelper {
   
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE productos(" +
		          " idProducto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
		          " nombre VARCHAR(30)  NOT NULL, " +
		          " precio FLOAT  NOT NULL, " +
		          " detalle TEXT NOT NULL)";
 
    public ConsomeSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
 
  

	@Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.
 
        //Se elimina la versión anterior de la tabla
    	/*
    	if(versionAnterior == 1 && versionNueva >= 2){
    		db.execSQL(SQLUpdateV2);
    	}
    	if(versionAnterior >= 1 && versionNueva >= 3){
    		db.execSQL(SQLUpdateV3);
    	}
    	*/
        db.execSQL("DROP TABLE IF EXISTS productos");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}