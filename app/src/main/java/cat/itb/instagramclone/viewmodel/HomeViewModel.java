package cat.itb.instagramclone.viewmodel;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.Story;
import cat.itb.instagramclone.models.User;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static List<Publication> publicacionesList = new ArrayList<Publication>();
    public static List<Story> storyList = new ArrayList<Story>();


    String[] comentarios = {
            "Gran foto",
            "Me encanta",
            "Que bien te ves",
            "Nice"
    };

    User[] users = {
            new User(1, "@jorge", 1),
            new User(2, "@kashir", 2),
            new User(3, "@manolo_33", 3)
    };

    Publication[] publicaciones = {
            new Publication(1, new User(1, "@jorge", R.drawable.lambo), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(2, new User(2, "@kashir", R.drawable.lambo), "El crack", Arrays.asList(users), R.drawable.instagram_logo, Arrays.asList(comentarios)),
            new Publication(3, new User(3, "@jorge2", R.drawable.lambo), "Postureo", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(4, new User(4, "@kashir2" , R.drawable.lambo), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(5, new User(5, "@jorge3", R.drawable.lambo), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(7, new User(7, "@pepe", R.drawable.lambo), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
    };

    Integer [] histori= {
            R.drawable.gogeta,
            R.drawable.gogeta,
            R.drawable.gogeta
    };

    Story[] historis = {
            new Story(1, new User(R.drawable.gogeta, "@jorge22", R.drawable.lambo), Arrays.asList(histori)),
            new Story(2, new User(R.drawable.gogeta, "@kashir33", R.drawable.lambo), Arrays.asList(histori)),
            new Story(3, new User(R.drawable.gogeta, "@jorge1", R.drawable.lambo), Arrays.asList(histori)),
            new Story(4, new User(R.drawable.gogeta, "@kashi2r", R.drawable.lambo), Arrays.asList(histori)),
            new Story(5, new User(R.drawable.gogeta, "@jorge", R.drawable.lambo), Arrays.asList(histori)),
            new Story(6, new User(R.drawable.gogeta, "@kashir", R.drawable.lambo), Arrays.asList(histori)),
            new Story(7, new User(R.drawable.gogeta, "@manolooo22", R.drawable.lambo), Arrays.asList(histori))
    };

    public HomeViewModel() {
        Collections.addAll(publicacionesList, publicaciones);
        Collections.addAll(storyList, historis);

    }
}