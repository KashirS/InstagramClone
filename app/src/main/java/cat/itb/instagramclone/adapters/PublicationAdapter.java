package cat.itb.instagramclone.adapters;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Publication;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder> {
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
        Drawable d = holder.itemView.getContext().getResources().getDrawable(p.getImagen_publicacion());
        Drawable imageUser = holder.itemView.getContext().getResources().getDrawable(p.getUser_propietario().getImagen_usuario());
        holder.bindData(p, d, imageUser);
    }

    @Override
    public int getItemCount() {
        return this.publicationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        MaterialButton nombre_usuario;
        ImageButton image_usuario_button;
        ImageView imagen_publicacion;
        MaterialButton num_likes_publicacion;
        MaterialButton nombre_usuario_2;
        MaterialTextView texto_usuario;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre_usuario = itemView.findViewById(R.id.nombre_usuario_button);
            this.imagen_publicacion = itemView.findViewById(R.id.imagen_publicacion);
            this.num_likes_publicacion = itemView.findViewById(R.id.num_likes_button);
            this.nombre_usuario_2 = itemView.findViewById(R.id.nombre_usuario_button_2);
            this.texto_usuario = itemView.findViewById(R.id.texto_usuario_textView);
            this.image_usuario_button = itemView.findViewById(R.id.imagen_usuario_button);
        }

        public void bindData(Publication p, Drawable imagePublicacion, Drawable imageUser){
            image_usuario_button.setImageDrawable(imageUser);
            nombre_usuario.setText(p.getUser_propietario().getNombre_usuario());
            imagen_publicacion.setImageDrawable(imagePublicacion);
            num_likes_publicacion.setText("Le ha gustado a " + p.getLikes_publicacion().get(0).getNombre_usuario() +" y " + p.getLikes_publicacion().size() + " usuarios más.");
            nombre_usuario_2.setText(p.getUser_propietario().getNombre_usuario());
            texto_usuario.setText(p.getTexto_publicacion());
        }
    }
}
