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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.Publication;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    List<String> publicationList;

    public SearchAdapter(List<String> publicationList) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements ValueEventListener {
        ImageView imagen_publicacion;
        Context c;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_publicacion = itemView.findViewById(R.id.image_pub);
        }

        public void bindData(String p, Context context){
            //TODO: Poner imagenes
            c = context;
            MainActivity.publicacionDBReference.child(p).addValueEventListener(this);

        }

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                String i = snapshot.child("imagen_publicacion").getValue().toString();
                Glide.with(c)
                        .load(i)
                        .fitCenter()
                        .centerCrop()
                        .into(imagen_publicacion);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
}
