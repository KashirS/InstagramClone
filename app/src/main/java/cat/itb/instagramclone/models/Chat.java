package cat.itb.instagramclone.models;

import java.util.List;

public class Chat {
    String chat_id;
    String user_chat;

    public List<String> getChat() {
        return chat;
    }

    public void setChat(List<String> chat) {
        this.chat = chat;
    }

    List<String> chat;

    public Chat() {

    }

    public Chat(String chat_id, String user_chat, List<String> chat) {
        this.chat_id = chat_id;
        this.user_chat = user_chat;
        this.chat = chat;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getUser_chat() {
        return user_chat;
    }

    public void setUser_chat(String user_chat) {
        this.user_chat = user_chat;
    }
}