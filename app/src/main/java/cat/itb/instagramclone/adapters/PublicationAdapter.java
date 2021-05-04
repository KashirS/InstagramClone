package cat.itb.instagramclone.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.Publication;
import de.hdodenhof.circleimageview.CircleImageView;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder>{
    List<String> publicationList;


    public PublicationAdapter(List<String> publicationList) {
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
        String p = this.publicationList.get(position);
        holder.bindData(p, holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return this.publicationList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements ValueEventListener{
        MaterialButton nombre_usuario;
        CircleImageView image_usuario_button;
        ImageView imagen_publicacion;
        MaterialButton num_likes_publicacion;
        MaterialButton nombre_usuario_2;
        MaterialTextView texto_usuario;
        String imagen_user;
        String nombre_user;
        String imagen_publi;
        String texto_publi;
        String id_propiet;
        List<String> likes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre_usuario = itemView.findViewById(R.id.nombre_usuario_button);
            this.imagen_publicacion = itemView.findViewById(R.id.imagen_publicacion);
            this.num_likes_publicacion = itemView.findViewById(R.id.num_likes_button);
            this.nombre_usuario_2 = itemView.findViewById(R.id.nombre_usuario_button_2);
            this.texto_usuario = itemView.findViewById(R.id.texto_usuario_textView);
            this.image_usuario_button = itemView.findViewById(R.id.imagen_usuario_button);
            this.likes = new ArrayList<>();
        }

        public void bindData(String p, Context context){
            MainActivity.publicacionDBReference.child(p).addValueEventListener(this);
            MainActivity.databaseReference.child(p).addValueEventListener(this);
            nombre_usuario.setText(nombre_user);
            num_likes_publicacion.setText("Le ha gustado a " + likes.size() + " usuarios más.");
            nombre_usuario_2.setText(nombre_user);
            texto_usuario.setText(texto_publi);
            Glide.with(context).load(imagen_user).fitCenter().centerCrop().into(image_usuario_button);
            Glide.with(context).load(imagen_publi).fitCenter().centerCrop().into(imagen_publicacion);
        }

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                if (snapshot.getRef().getParent().getKey().equals("Publicaciones")){
                    texto_publi = snapshot.child("texto_publicacion").getValue().toString();
                    imagen_publi = snapshot.child("imagen_publicacion").getValue().toString();
                    id_propiet = snapshot.child("user_propietario").getValue().toString();
                    for (DataSnapshot s : snapshot.child("Likes").getChildren()){
                        String like = s.getValue().toString();
                        likes.add(like);
                    }

                }else{
                    imagen_user = snapshot.child("imagen_usuario").getValue().toString();
                    nombre_user = snapshot.child("username").getValue().toString();

                }

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
}
