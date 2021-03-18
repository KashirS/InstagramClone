package cat.itb.instagramclone.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static List<Publication> publicacionesList = new ArrayList<Publication>();

    String[] comentarios = {
            "Gran foto",
            "Me encanta",
            "Que bien te ves",
            "Nice"
    };

    User[] users = {
            new User(1, "Jorge", 1),
            new User(2, "Kashir", 2),
            new User(3, "Manolo", 3)
    };

    Publication[] publicaciones = {
            new Publication(1, new User(1, "Jorge", 1), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(2, new User(2, "Kashir", 2), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(3, new User(3, "Jorge2", 3), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(4, new User(4, "Kashir2" ,4), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(5, new User(5, "Jorge3", 5), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "Kashir3", 6), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(7, new User(7, "Pepe", 7), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
    };

    public HomeViewModel() {
        Collections.addAll(publicacionesList, publicaciones);
    }
}