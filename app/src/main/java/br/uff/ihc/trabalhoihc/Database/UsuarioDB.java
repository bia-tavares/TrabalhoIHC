package br.uff.ihc.trabalhoihc.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.uff.ihc.trabalhoihc.Model.DBSingleton;

public class UsuarioDB {

    private SQLiteDatabase db;

    private String[] colunas = new String[]{"idUsuario", "login", "senha", "tokenFace", "nome", "foto"};


    public UsuarioDB(SQLiteDatabase database){
        db = database;
    }

    public void cadastrar(String login, String senha, String nome){

        String sql = "INSERT OR REPLACE INTO " + DBSingleton.TABLE_USUARIO + " ("+  colunas[1] +
                ", "+ colunas[2] + ", "+ colunas[4] + ") VALUES (\""+
                login + "\", \"" + senha + "\", \"" + nome +"\");";

        this.db.execSQL(sql);

    }

    public int login(String login, String senha){

        String[] colunas = new String[]{"idUsuario"};
        int result = -1;
        Cursor cursor = this.db.query(DBSingleton.TABLE_USUARIO, colunas ,"login = \""+login+"\" AND senha = \""+senha+"\"", null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return result;
    }

    public String name(int id){
        String[] colunas = new String[]{"nome"};
        String result = "";
        Cursor cursor = this.db.query(DBSingleton.TABLE_USUARIO, colunas ,"idUsuario = \""+id+"\"", null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return result;
    }

    public List<String> selectAll ( String selection, String orderBy ){

        List<String> list = new ArrayList<String>();

        Cursor cursor = this.db.query(DBSingleton.TABLE_USUARIO, colunas ,
                selection, null, null, orderBy, null);

        if (cursor.moveToFirst()) {
            do {
                String result = String.valueOf(cursor.getInt(0)) +";"+ cursor.getString(1) +";"+ cursor.getString(2) +";"+ cursor.getString(3) +";"+ cursor.getString(4);
                list.add(result);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;

    }

    public void deleteTable() {
        db.delete(DBSingleton.TABLE_USUARIO,null,null);
    }

}
