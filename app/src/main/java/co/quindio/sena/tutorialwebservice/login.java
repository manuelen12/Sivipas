package co.quindio.sena.tutorialwebservice;

import android.app.Application;

/**
 * Created by Sebastian on 23/10/2017.
 */

public class login extends Application {
    private String usuario;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;

    }
}
