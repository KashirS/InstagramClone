package cat.itb.instagramclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    List<Chat> chatList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(this.chatList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        MaterialButton user_chat_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_chat_button = itemView.findViewById(R.id.user_chat_button);
        }

        public void bindData(Chat chat){
            user_chat_button.setText(chat.getUser_chat().getNombre_usuario());
        }
    }
}