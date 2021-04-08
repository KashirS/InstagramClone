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
            new User( "","@jorge", R.drawable.gogeta, null, ""),
            new User("","@kashir", R.drawable.gogeta, null, ""),
            new User( "","@manolo_33", R.drawable.gogeta, null, "")
    };

    Publication[] publicaciones = {
            new Publication(1, new User("", "@jorge", R.drawable.gohan, null, ""), "Gran dia", Arrays.asList(users), R.drawable.gohan, Arrays.asList(comentarios)),
            new Publication(2, new User("", "@kashir", R.drawable.vegeta, null, ""), "El crack", Arrays.asList(users), R.drawable.vegeta, Arrays.asList(comentarios)),
            new Publication(3, new User("", "@jorge2", R.drawable.broly, null, ""), "Postureo", Arrays.asList(users), R.drawable.broly, Arrays.asList(comentarios)),
            new Publication(4, new User("", "@kashir2" , R.drawable.gogeta, null, ""), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(5, new User("", "@jorge3", R.drawable.gohan, null, ""), "Gran dia", Arrays.asList(users), R.drawable.gohan, Arrays.asList(comentarios)),
            new Publication(6, new User("", "@kashir3", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.lambo, Arrays.asList(comentarios)),
            new Publication(7, new User("", "@pepe", R.drawable.instagram_logo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.instagram_logo, Arrays.asList(comentarios)),
    };


    Integer [] histori= {
            R.drawable.gogeta,
            R.drawable.gogeta,
            R.drawable.gogeta
    };
/*
    Story[] historis = {
            new Story(1, new User(R.drawable.gogeta, "@jorge22", R.drawable.vegeta, null, ""), Arrays.asList(histori)),
            new Story(2, new User(R.drawable.gogeta, "@kashir33", R.drawable.broly, null, ""), Arrays.asList(histori)),
            new Story(3, new User(R.drawable.gogeta, "@jorge1", R.drawable.gohan, null, ""), Arrays.asList(histori)),
            new Story(4, new User(R.drawable.gogeta, "@kashi2r", R.drawable.instagram_logo,null, ""), Arrays.asList(histori)),
            new Story(5, new User(R.drawable.gogeta, "@jorge", R.drawable.gogeta,null, ""), Arrays.asList(histori)),
            new Story(6, new User(R.drawable.gogeta, "@kashir", R.drawable.vegeta, null, ""), Arrays.asList(histori)),
            new Story(7, new User(R.drawable.gogeta, "@manolooo22", R.drawable.broly, null, ""), Arrays.asList(histori))
    };
*/
    public HomeViewModel() {
        Collections.addAll(publicacionesList, publicaciones);
        Collections.addAll(storyList);

    }
}