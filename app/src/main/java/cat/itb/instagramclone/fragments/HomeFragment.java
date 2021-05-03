package cat.itb.instagramclone.fragments;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.adapters.PublicationAdapter;
import cat.itb.instagramclone.adapters.StoryAdapter;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;
import cat.itb.instagramclone.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment{

    private HomeViewModel mViewModel;
    RecyclerView publicaciones_recyclerView;
    RecyclerView story;
    MenuItem chat_item;
    MaterialToolbar materialToolbar;
    List<Publication> publicationList;
    Publication p_prueba;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    // TODO: Adapters Firebase https://www.youtube.com/watch?v=0pF9r0CsT_4


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        MainActivity.mostrarNavDrawer();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        publicaciones_recyclerView.setAdapter(new PublicationAdapter(MainActivity.user.getPublications_amigos()));
        publicaciones_recyclerView = v.findViewById(R.id.home_publication_recyclerView);
        publicaciones_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Toast.makeText(getContext(), "Main User: "+ MainActivity.user.getNombre_usuario(), Toast.LENGTH_LONG).show();
        materialToolbar = v.findViewById(R.id.top_app_bar);
        materialToolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        story = v.findViewById(R.id.story_recy);
        story.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //story.setAdapter(new StoryAdapter());

        return v;
    }


    public List<User> getPublicacionesUsers(){
        MainActivity.databaseReference.child("Publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    publicationList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()){
                        List<String> comentList = new ArrayList<>();
                        List<String> likeList = new ArrayList<>();
                        String id_publi = ds.child("id_publicacion").getValue().toString();
                        String imagen = ds.child("imagen_publicacion").getValue().toString();
                        String texto = ds.child("texto_publicacion").getValue().toString();
                        String user = ds.child("user_propietario").getValue().toString();
                        for (DataSnapshot dataComent : snapshot.child("Comentarios").getChildren()){
                            String com = dataComent.getValue().toString();
                            comentList.add(com);
                        }
                        for (DataSnapshot dataLike : snapshot.child("Likes").getChildren()){
                            String username_like = dataLike.getValue().toString();
                            likeList.add(username_like);
                        }
                        publicationList.add(new Publication(id_publi, user, texto, likeList, imagen, comentList));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return new ArrayList<User>();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_top_appbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_to_chat:
                Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_chatFragment);
                return true;

            default: return false;
        }

    }

    private void buscarPublicaciones(){
        this.publicationList = MainActivity.user.getPublications_user();
    }

}