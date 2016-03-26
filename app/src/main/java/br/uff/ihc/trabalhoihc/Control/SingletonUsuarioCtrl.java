package br.uff.ihc.trabalhoihc.Control;

public class SingletonUsuarioCtrl {

    private static int idUsuario = 0;

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

}
