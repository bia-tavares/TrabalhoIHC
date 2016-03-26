package br.uff.ihc.trabalhoihc.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.uff.ihc.trabalhoihc.Model.DBSingleton;


public class IngressoDB {

    private SQLiteDatabase db;

    private String[] colunas = new String[]{"idIngresso", "vendido", "valor", "vendedor", "comprador"};


    public IngressoDB(SQLiteDatabase database){
        db = database;
    }

    public void insert(boolean vendido, Double valor, int vendedor){

        String sql = "INSERT OR REPLACE INTO " + DBSingleton.TABLE_INGRESSO + " ("+  colunas[1] +
                ", "+ colunas[2] + ", "+ colunas[3] + ") VALUES (\""+
                vendido + "\", \"" + valor + "\", " + vendedor + ");";

        this.db.execSQL(sql);

    }

    public List<String> selectAll ( String selection, String orderBy ){

        List<String> list = new ArrayList<String>();

        Cursor cursor = this.db.query(DBSingleton.TABLE_INGRESSO, colunas ,
                selection, null, null, orderBy, null);

        if (cursor.moveToFirst()) {
            do {
                String result = String.valueOf(cursor.getInt(0)) +";"+ String.valueOf(cursor.getInt(1)) + ";" +
                        String.valueOf(cursor.getDouble(2)) + ";" + String.valueOf(cursor.getInt(3)) + ";" +
                        String.valueOf(cursor.getInt(4));

                list.add(result);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;

    }

    public void deleteTable() {
        db.delete(DBSingleton.TABLE_INGRESSO,null,null);
    }

}
