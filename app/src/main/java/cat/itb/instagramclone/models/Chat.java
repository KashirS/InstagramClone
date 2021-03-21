package cat.itb.instagramclone.models;

import java.util.List;

public class Chat {
    int chat_id;
    User user_chat;
    List<String> chat

    public Chat() {

    }

    public Chat(int chat_id, User user_chat, List<String> chat) {
        this.chat_id = chat_id;
        this.user_chat = user_chat;
        this.chat = chat;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public User getUser_chat() {
        return user_chat;
    }

    public void setUser_chat(User user_chat) {
        this.user_chat = user_chat;
    }
}