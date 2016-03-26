package br.uff.ihc.trabalhoihc.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.uff.ihc.trabalhoihc.Database.UsuarioDB;


public class DataHelper {

    public static Context context;
    private SQLiteDatabase db;
    private UsuarioDB usuario;

    public DataHelper(Context context) {
        this.context = context;
        db = DBSingleton.getDb();
        usuario = new UsuarioDB(db);
    }

    public int cadastrar(String login, String novaSenha, String nome){
        usuario.cadastrar(login, novaSenha, nome);
        return usuario.login(login, novaSenha);
    }

    /**
     * @param login
     * @param senha
     * @return idUsuario
     */
    public int login (String login, String senha){
        return usuario.login(login, senha);
    }

    public String name(int id){
        return usuario.name(id);
    }

}
