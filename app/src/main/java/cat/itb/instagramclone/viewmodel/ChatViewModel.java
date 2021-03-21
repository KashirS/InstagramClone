package cat.itb.instagramclone.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
            new Chat(1, new User(1, "@kachir1", 1), Arrays.asList(chat)),
    };

    public ChatViewModel() {
        Collections.addAll(chatsList, chat);
    }
}