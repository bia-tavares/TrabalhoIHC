package br.uff.ihc.trabalhoihc.Model;

import android.database.sqlite.SQLiteDatabase;

import br.uff.ihc.trabalhoihc.Database.OpenHelper;


public class DBSingleton {

    public static final String TABLE_INGRESSO = "INGRESSO";
    public static final String TABLE_USUARIO = "USUARIO";

    private static SQLiteDatabase db = new OpenHelper(DataHelper.context).getReadableDatabase();


    /*public static void setDb(SQLiteDatabase database) {
        db = database;
    }*/

    public static SQLiteDatabase getDb() {
        return db;
    }
}
