package co.quindio.sena.tutorialwebservice.entidades;

/**
 * Created by CHENAO on 6/08/2017.
 */

public class Usuario {


    private String name;
    private Integer contrasena;


    public String getName(){
        return  name;
    }

    public Integer getDocumento() {
        return contrasena;
    }





    public void setContrasena(Integer contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }


}
