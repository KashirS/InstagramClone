package cat.itb.instagramclone.models;

import android.widget.ImageView;

import java.util.List;

public class Story {
    int id_story;
    User user_story;
    List<Integer> imagenes_storys;

    public Story(int id_story, User user_story, List<Integer> imagenes_storys) {
        this.id_story = id_story;
        this.user_story = user_story;
        this.imagenes_storys = imagenes_storys;
    }


    public int getId_story() {
        return id_story;
    }

    public void setId_story(int id_story) {
        this.id_story = id_story;
    }

    public User getUser_story() {
        return user_story;
    }

    public void setUser_story(User user_story) {
        this.user_story = user_story;
    }

    public List<Integer> getImagenes_storys() {
        return imagenes_storys;
    }

    public void setImagenes_storys(List<Integer> imagenes_storys) {
        this.imagenes_storys = imagenes_storys;
    }
}