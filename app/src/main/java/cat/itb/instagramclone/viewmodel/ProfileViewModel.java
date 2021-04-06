package cat.itb.instagramclone.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

public class ProfileViewModel extends ViewModel {

    public static List<Publication> publicacionesList = new ArrayList<>();
    public static User user;
    // TODO: Implement the ViewModel
    String[] comentarios = {
            "Gran foto",
            "Me encanta",
            "Que bien te ves",
            "Nice"
    };

    User[] users = {
            new User(1, "@jorge", R.drawable.gogeta, null, ""),
            new User(2, "@kashir", R.drawable.gogeta, null, ""),
            new User(3, "@manolo_33", R.drawable.gogeta, null, "")
    };

    Publication[] publicaciones = {
            new Publication(1, new User(1, "@jorge", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(2, new User(2, "@kashir", R.drawable.lambo, null, ""), "El crack", Arrays.asList(users), R.drawable.instagram_logo, Arrays.asList(comentarios)),
            new Publication(3, new User(3, "@jorge2", R.drawable.lambo, null,""), "Postureo", Arrays.asList(users), R.drawable.vegeta, Arrays.asList(comentarios)),
            new Publication(4, new User(4, "@kashir2" , R.drawable.lambo, null,""), "Gran dia", Arrays.asList(users), R.drawable.lambo, Arrays.asList(comentarios)),
            new Publication(5, new User(5, "@jorge3", R.drawable.lambo, null,""), "Gran dia", Arrays.asList(users), R.drawable.broly, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo, null,""), "Gran dia", Arrays.asList(users), R.drawable.gohan, Arrays.asList(comentarios)),
            new Publication(7, new User(7, "@pepe", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.broly, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.vegeta, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.instagram_logo, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.vegeta, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo, null,""), "Gran dia", Arrays.asList(users), R.drawable.gohan, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", R.drawable.lambo, null,""), "Gran dia", Arrays.asList(users), R.drawable.lambo, Arrays.asList(comentarios)),
            new Publication(7, new User(7, "@pepe", R.drawable.lambo, null, ""), "Gran dia", Arrays.asList(users), R.drawable.gogeta, Arrays.asList(comentarios))
    };


    public ProfileViewModel() {
        //Collections.addAll(publicacionesList, publicaciones);
        user = new User(1, "@gogeta55", R.drawable.gogeta, Arrays.asList(publicaciones), "Super Sayan Dios XD");
    }
}