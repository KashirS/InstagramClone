package cat.itb.instagramclone.models;

import java.util.List;

public class User {
    String id_usuario;
    String nombre_usuario;
    String imagen_usuario;

    List<Publication> publications_user;
    String descripcion_user;

    public User(String id_usuario, String nombre_usuario, String imagen_usuario, List<Publication> publications_user, String descripcion_user) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.imagen_usuario = imagen_usuario;
        this.publications_user = publications_user;
        this.descripcion_user = descripcion_user;
    }

    public String getDescripcion_user() {
        return descripcion_user;
    }

    public void setDescripcion_user(String descripcion_user) {
        this.descripcion_user = descripcion_user;
    }

    public List<Publication> getPublications_user() {
        return publications_user;
    }

    public void setPublications_user(List<Publication> publications_user) {
        this.publications_user = publications_user;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getImagen_usuario() {
        return imagen_usuario;
    }

    public void setImagen_usuario(String imagen_usuario) {
        this.imagen_usuario = imagen_usuario;
    }
}
