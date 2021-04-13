package cat.itb.instagramclone.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Publication;

public class SearchAdapter extends FirebaseRecyclerAdapter<Publication, SearchAdapter.ViewHolder> {
    List<Publication> publicationList;

    public SearchAdapter(@NonNull FirebaseRecyclerOptions<Publication> options, List<Publication> publicationList) {
        super(options);
        this.publicationList = publicationList;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Publication model) {
        Publication p = this.publicationList.get(position);
        Drawable image = holder.itemView.getContext().getResources().getDrawable(p.getUser_propietario().getImagen_usuario());
        holder.bindData(p, image);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_publicacion_item, parent, false);
        return new ViewHolder(v);
    }

    /*public SearchAdapter(List<Publication> publicationList) {
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
        Publication p = this.publicationList.get(position);
        Drawable image = holder.itemView.getContext().getResources().getDrawable(p.getUser_propietario().getImagen_usuario());
        holder.bindData(p, image);
    }

    @Override
    public int getItemCount() {
        return this.publicationList.size();
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen_publicacion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_publicacion = itemView.findViewById(R.id.imagen_publicacion_search);
        }

        public void bindData(Publication p, Drawable image){
            //TODO: Poner imagenes
            imagen_publicacion.setImageResource(p.getImagen_publicacion());
        }
    }
}
