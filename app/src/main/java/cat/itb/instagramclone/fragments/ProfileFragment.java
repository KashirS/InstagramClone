package cat.itb.instagramclone.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.adapters.SearchAdapter;
import cat.itb.instagramclone.models.User;
import cat.itb.instagramclone.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    RecyclerView profile_recyclerView;
    ImageView profile_image;
    MaterialTextView profile_name;
    MaterialTextView profile_description;
    TextView num_pub;
    TextView num_user_follower;
    TextView num_user_follow;


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment, container, false);
        num_pub = v.findViewById(R.id.num_pub);
        num_user_follower = v.findViewById(R.id.num_user_follower);
        num_user_follow = v.findViewById(R.id.num_user_follow);
        profile_image = v.findViewById(R.id.imagen_user_profile);
        profile_description = v.findViewById(R.id.profile_description);
        profile_description.setText(MainActivity.user.getDescripcion_user());
        profile_name = v.findViewById(R.id.nombre_user_profile);
        profile_name.setText(MainActivity.user.getUsername());
        Glide.with(getContext()).load(MainActivity.user.getImagen_usuario()).fitCenter().centerCrop().into(profile_image);
        num_pub.setText(MainActivity.user.getPublications_user().size()+"");
        num_user_follower.setText(MainActivity.user.getPublications_amigos().size()+"");
        num_user_follow.setText(MainActivity.user.getPublications_amigos().size()+"");
        profile_recyclerView = v.findViewById(R.id.profile_recyclerView);
        profile_recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        profile_recyclerView.setAdapter(new SearchAdapter(MainActivity.user.getPublications_user()));
        return v;
    }

}