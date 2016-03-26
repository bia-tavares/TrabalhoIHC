package br.uff.ihc.trabalhoihc.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.uff.ihc.trabalhoihc.Model.DBSingleton;

public class OpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tiket.db";
    private static int DATABASE_VERSION = 1;

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("TIKET_LOG", "Criação de banco de dados");

        db.execSQL("CREATE TABLE IF NOT EXISTS USUARIO (" +
                "  `idUsuario` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  `login` VARCHAR(45) NULL," +
                "  `senha` VARCHAR(45) NULL," +
                "  `tokenFace` VARCHAR(100) NULL," +
                "  `nome` VARCHAR(100) NOT NULL," +
                "  `foto` LONGBLOB NULL" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS INGRESSO (" +
                "`idIngresso` INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`vendido` TINYINT(1) NOT NULL," +
                "`valor` DOUBLE NOT NULL," +
                "`vendedor` INTEGER NOT NULL," +
                "`comprador` INTEGER NULL," +
                "  CONSTRAINT `fkVendedor`" +
                "        FOREIGN KEY (`vendedor`)" +
                "        REFERENCES `USUARIO` (`idUsuario`)" +
                "        ON DELETE CASCADE" +
                "        ON UPDATE CASCADE," +
                "                CONSTRAINT `fkComprador`" +
                "        FOREIGN KEY (`comprador`)" +
                "        REFERENCES `USUARIO` (`idUsuario`)" +
                "        ON DELETE CASCADE" +
                "        ON UPDATE CASCADE);");

    }

    public void deleteDB(SQLiteDatabase db) {

        Log.i("ECHAMADALOG", "Exclusão de banco de dados");

        db.execSQL("DROP TABLE IF EXISTS " + DBSingleton.TABLE_INGRESSO);
        db.execSQL("DROP TABLE IF EXISTS " + DBSingleton.TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (DATABASE_VERSION == oldVersion) {

            Log.i("ECHAMADALOG", "Upgrade de banco de dados");

            deleteDB(db);
            DATABASE_VERSION = newVersion;
            onCreate(db);

        }
    }

}