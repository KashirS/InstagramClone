package cat.itb.instagramclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
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
        holder.bindData(this.publicationList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.publicationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        MaterialButton nombre_usuario;
        ImageView imagen_publicacion;
        MaterialButton num_likes_publicacion;
        MaterialButton nombre_usuario_2;
        MaterialTextView texto_usuario;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre_usuario = itemView.findViewById(R.id.nombre_usuario_button);
            imagen_publicacion = itemView.findViewById(R.id.imagen_publicacion);
            num_likes_publicacion = itemView.findViewById(R.id.num_likes_button);
            nombre_usuario_2 = itemView.findViewById(R.id.nombre_usuario_button_2);
            texto_usuario = itemView.findViewById(R.id.texto_usuario_textView);
        }

        public void bindData(Publication p){
            nombre_usuario.setText(p.getUser_propietario().getNombre_usuario());
            //imagen_publicacion.setImageResource(p.getImagen_publicacion());
            num_likes_publicacion.setText("Le ha gustado a " + p.getLikes_publicacion().size() + " usuarios");
            nombre_usuario_2.setText(p.getUser_propietario().getNombre_usuario());
            texto_usuario.setText(p.getTexto_publicacion());
        }
    }
}
