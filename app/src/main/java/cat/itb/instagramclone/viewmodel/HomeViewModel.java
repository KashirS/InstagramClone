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
            new User( "","@jorge", "", null, ""),
            new User("","@kashir", "", null, ""),
            new User( "","@manolo_33", "", null, "")
    };

    Publication[] publicaciones = {
            new Publication("", new User("", "@jorge", "", null, ""), "Gran dia", Arrays.asList(users), "R.drawable.gohan", Arrays.asList(comentarios)),
            new Publication("", new User("", "@kashir", "", null, ""), "El crack", Arrays.asList(users), "R.drawable.vegeta", Arrays.asList(comentarios)),
            new Publication("", new User("", "@jorge2", "", null, ""), "Postureo", Arrays.asList(users), "R.drawable.broly", Arrays.asList(comentarios)),
            new Publication("", new User("", "@kashir2" , "", null, ""), "Gran dia", Arrays.asList(users), "R.drawable.gogeta", Arrays.asList(comentarios)),
            new Publication("", new User("", "@jorge3", "", null, ""), "Gran dia", Arrays.asList(users), "R.drawable.gohan", Arrays.asList(comentarios)),
            new Publication("", new User("", "@kashir3", "", null, ""), "Gran dia", Arrays.asList(users), "R.drawable.lambo", Arrays.asList(comentarios)),
            new Publication("", new User("", "@pepe", "", null, ""), "Gran dia", Arrays.asList(users), "R.drawable.instagram_logo", Arrays.asList(comentarios)),
    };


    Integer [] histori= {
            R.drawable.gogeta,
            R.drawable.gogeta,
            R.drawable.gogeta
    };

    Story[] historis = {
            new Story(1, new User("", "@jorge22", "", null, ""), Arrays.asList(histori)),
            new Story(2, new User("", "@kashir33", "", null, ""), Arrays.asList(histori)),
            new Story(3, new User("", "@jorge1", "", null, ""), Arrays.asList(histori)),
            new Story(4, new User("", "@kashi2r", "",null, ""), Arrays.asList(histori)),
            new Story(5, new User("", "@jorge", "",null, ""), Arrays.asList(histori)),
            new Story(6, new User("", "@kashir", "", null, ""), Arrays.asList(histori)),
            new Story(7, new User("", "@manolooo22", "", null, ""), Arrays.asList(histori))
    };

    public HomeViewModel() {
        Collections.addAll(publicacionesList, publicaciones);
        Collections.addAll(storyList);

    }
}