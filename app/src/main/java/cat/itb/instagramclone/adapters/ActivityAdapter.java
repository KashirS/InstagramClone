package cat.itb.instagramclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Notification;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    List<Notification> notificationList;

    public ActivityAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notificacion_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(this.notificationList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen_user_notification;
        MaterialTextView texto_notificacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_user_notification = itemView.findViewById(R.id.user_image_notification);
            texto_notificacion = itemView.findViewById(R.id.text_notification);
        }

        public void bindData(Notification not){
            //imagen_user_notification.setImageResource(not.getUser_notification().getImagen_usuario());
            texto_notificacion.setText(not.getNotification());
        }
    }
}
