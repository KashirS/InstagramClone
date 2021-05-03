package cat.itb.instagramclone.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Publication;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    List<Publication> publicationList;

    public SearchAdapter(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_publicacion_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(this.publicationList.get(position), holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return this.publicationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen_publicacion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_publicacion = itemView.findViewById(R.id.image_pub);
        }

        public void bindData(Publication p, Context context){
            //TODO: Poner imagenes
            Glide.with(context)
                    .load(p.getImagen_publicacion())
                    .fitCenter()
                    .centerCrop()
                    .into(imagen_publicacion);

        }
    }
}
