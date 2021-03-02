package cat.itb.instagramclone.models;

import android.widget.ImageView;

public class User {
    int id_usuario;
    String nombre_usuario;
    ImageView imagen_usuario;

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

    public ImageView getImagen_usuario() {
        return imagen_usuario;
    }

    public void setImagen_usuario(ImageView imagen_usuario) {
        this.imagen_usuario = imagen_usuario;
    }
}
