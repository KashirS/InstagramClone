package cat.itb.instagramclone.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    List<Chat> chatList;

    public ChatAdapter(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat c = this.chatList.get(position);
        //Drawable image = holder.itemView.getContext().getResources().getDrawable(c.getUser_chat().getImagen_usuario());
        //holder.bindData(c, image);
    }

    @Override
    public int getItemCount() {
        return this.chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView user_chat_button;
        MaterialTextView chat_user;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_chat_button = itemView.findViewById(R.id.user_chat_button);
            //imageView = itemView.findViewById(R.id.chat_user_image);
            //chat_user = itemView.findViewById(R.id.chat_chat);
        }

        public void bindData(Chat chat,  Drawable drawable){
            user_chat_button.setText(chat.getUser_chat().getNombre_usuario());
            //imageView.setImageDrawable(drawable);
            //chat_user.setText(chat.getChat().get(0));
        }
    }
}