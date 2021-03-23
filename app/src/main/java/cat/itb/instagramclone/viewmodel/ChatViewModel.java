package cat.itb.instagramclone.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Chat;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

public class ChatViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static List<Chat> chatsList = new ArrayList<Chat>();

    String[] chat = {
            "Hola",
            "Quetal",
            "XD",
            "Nice"
    };


    Chat[] chats = {
            new Chat(1, new User(1, "@kachir1", R.drawable.gogeta, null, ""), Arrays.asList(chat)),
            new Chat(1, new User(1, "@manolo22", R.drawable.broly, null, ""), Arrays.asList(chat)),
            new Chat(1, new User(1, "@pepe33", R.drawable.instagram_logo, null, ""), Arrays.asList(chat)),
            new Chat(1, new User(1, "@manolo22", R.drawable.vegeta, null, ""), Arrays.asList(chat)),
            new Chat(1, new User(1, "@manolo22", R.drawable.gohan, null, ""), Arrays.asList(chat)),
            new Chat(1, new User(1, "@pepe56", R.drawable.lambo, null, ""), Arrays.asList(chat)),
            new Chat(1, new User(1, "@manolo22", R.drawable.gohan, null, ""), Arrays.asList(chat)),
            new Chat(1, new User(1, "@juan2", R.drawable.instagram_logo, null, ""), Arrays.asList(chat))
    };

    public ChatViewModel() {
        Collections.addAll(chatsList, chats);
    }
}