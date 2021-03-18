package cat.itb.instagramclone.models;

import android.widget.ImageView;

public class User {
    int id_usuario;
    String nombre_usuario;
    int imagen_usuario;

    public User(int id_usuario, String nombre_usuario, int imagen_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.imagen_usuario = imagen_usuario;
    }

    public User() {

    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getImagen_usuario() {
        return imagen_usuario;
    }

    public void setImagen_usuario(int imagen_usuario) {
        this.imagen_usuario = imagen_usuario;
    }
}
