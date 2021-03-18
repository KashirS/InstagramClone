package cat.itb.instagramclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Story;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    List<Story> storyList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_home_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(this.storyList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.storyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen_usuario;
        MaterialTextView nombre_usuario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_usuario = itemView.findViewById(R.id.imagen_user_story_item);
            nombre_usuario = itemView.findViewById(R.id.nombre_user_story_item);
        }

        public void bindData(Story s){
            imagen_usuario.setImageResource(s.getUser_story().getImagen_usuario());
            nombre_usuario.setText(s.getUser_story().getNombre_usuario());
        }
    }
}
