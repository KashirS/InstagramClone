package cat.itb.instagramclone.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

public class SearchViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static List<Publication> searchesList = new ArrayList<Publication>();

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

    Publication[] searches = {
            new Publication(1, new User(1, "@jorge", 1), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(2, new User(2, "@kashir", 2), "El crack", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(3, new User(3, "@jorge2", 3), "Postureo", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(4, new User(4, "@kashir2" ,4), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(5, new User(5, "@jorge3", 5), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(6, new User(6, "@kashir3", 6), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
            new Publication(7, new User(7, "@pepe", 7), "Gran dia", Arrays.asList(users), 10, Arrays.asList(comentarios)),
    };

    public SearchViewModel() {
        Collections.addAll(searchesList, searches);
    }
}