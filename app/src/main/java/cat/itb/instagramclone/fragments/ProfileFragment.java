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

import com.google.android.material.textview.MaterialTextView;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.adapters.SearchAdapter;
import cat.itb.instagramclone.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    RecyclerView profile_recyclerView;
    ImageView profile_image;
    MaterialTextView profile_name;
    MaterialTextView profile_description;

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
        profile_image = v.findViewById(R.id.imagen_user_profile);
       // profile_image.setImageDrawable(getResources().getDrawable(mViewModel.user.getImagen_usuario()));
        profile_description = v.findViewById(R.id.profile_description);
        profile_description.setText(mViewModel.user.getDescripcion_user());
        profile_name = v.findViewById(R.id.nombre_user_profile);
        profile_name.setText(mViewModel.user.getNombre_usuario());
        profile_recyclerView = v.findViewById(R.id.profile_recyclerView);
        profile_recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        profile_recyclerView.setAdapter(new SearchAdapter(mViewModel.user.getPublications_user()));
        return v;
    }

}