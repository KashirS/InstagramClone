package cat.itb.instagramclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.Publication;
import de.hdodenhof.circleimageview.CircleImageView;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder>{
    List<Publication> publicationList;

    public PublicationAdapter(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.publicacion_home_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Publication p = this.publicationList.get(position);
        holder.bindData(p, holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return this.publicationList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        MaterialButton nombre_usuario;
        CircleImageView image_usuario_button;
        ImageView imagen_publicacion;
        MaterialButton num_likes_publicacion;
        MaterialTextView nombre_usuario_textView;
        MaterialTextView texto_usuario;
        MaterialButton comentarios_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre_usuario = itemView.findViewById(R.id.nombre_usuario_button);
            this.imagen_publicacion = itemView.findViewById(R.id.imagen_publicacion);
            this.num_likes_publicacion = itemView.findViewById(R.id.num_likes_button);
            this.nombre_usuario_textView = itemView.findViewById(R.id.nombre_usuario_textView);
            this.texto_usuario = itemView.findViewById(R.id.texto_usuario_textView);
            this.image_usuario_button = itemView.findViewById(R.id.imagen_usuario_button);
            this.comentarios_button = itemView.findViewById(R.id.comentarios_publicacion_button_2);
        }

        public void bindData(Publication p, Context context){
            MainActivity.databaseReference.child(p.getUser_propietario()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String imagen_user = snapshot.child("imagen_usuario").getValue().toString();
                        String nombre_user = snapshot.child("username").getValue().toString();
                        nombre_usuario.setText(nombre_user);
                        num_likes_publicacion.setText("Le ha gustado a " + p.getLikes_publicacion().size() + " usuarios más.");
                        nombre_usuario_textView.setText(nombre_user);
                        texto_usuario.setText(p.getTexto_publicacion());
                        Glide.with(context).load(imagen_user).fitCenter().centerCrop().into(image_usuario_button);
                        Glide.with(context).load(p.getImagen_publicacion()).fitCenter().centerCrop().into(imagen_publicacion);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
}
