package cat.itb.instagramclone.models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.List;

public class Publication {
    String id_publicacion;
    String user_propietario;
    String texto_publicacion;
    List<String> likes_publicacion;
    String imagen_publicacion;
    List<String> comentarios;

    public Publication() {
    }

    public Publication(String id_publicacion, String user_propietario, String texto_publicacion, List<String> likes_publicacion, String imagen_publicacion_id, List<String> comentarios) {
        this.id_publicacion = id_publicacion;
        this.user_propietario = user_propietario;
        this.texto_publicacion = texto_publicacion;
        this.likes_publicacion = likes_publicacion;
        this.imagen_publicacion = imagen_publicacion_id;
        this.comentarios = comentarios;
    }

    public Publication(String imagen_publicacion) {
        this.imagen_publicacion = imagen_publicacion;
    }

    public List<String> getLikes_publicacion() {
        return likes_publicacion;
    }

    public void setLikes_publicacion(List<String> likes_publicacion) {
        this.likes_publicacion = likes_publicacion;
    }

    public String getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(String id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getUser_propietario() {
        return user_propietario;
    }

    public String getTexto_publicacion() {
        return texto_publicacion;
    }

    public void setTexto_publicacion(String texto_publicacion) {
        this.texto_publicacion = texto_publicacion;
    }

    public String getImagen_publicacion() {
        return imagen_publicacion;
    }

    public void setImagen_publicacion(String imagen_publicacion) {
        this.imagen_publicacion = imagen_publicacion;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public void setUser_propietario(String user_propietario) {
        this.user_propietario = user_propietario;
    }
}
