package cat.itb.instagramclone.models;

import android.widget.ImageView;

import java.util.List;

public class Publication {
    int id_publicacion;
    User user_propietario;
    String texto_publicacion;
    List<User> likes_publicacion;
    ImageView imagen_publicacion;
    List<String> comentarios;

    public List<User> getLikes_publicacion() {
        return likes_publicacion;
    }

    public void setLikes_publicacion(List<User> likes_publicacion) {
        this.likes_publicacion = likes_publicacion;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public User getUser_propietario() {
        return user_propietario;
    }

    public void setUser_propietario(User user_propietario) {
        this.user_propietario = user_propietario;
    }

    public String getTexto_publicacion() {
        return texto_publicacion;
    }

    public void setTexto_publicacion(String texto_publicacion) {
        this.texto_publicacion = texto_publicacion;
    }

    public ImageView getImagen_publicacion() {
        return imagen_publicacion;
    }

    public void setImagen_publicacion(ImageView imagen_publicacion) {
        this.imagen_publicacion = imagen_publicacion;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }
}
