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
    List<Publication> publicationList = new ArrayList<>();
    List<Publication> userPublications = new ArrayList<>();
    List<User> userList = new ArrayList<>();
    Publication p_prueba;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    // TODO: Adapters Firebase https://www.youtube.com/watch?v=0pF9r0CsT_4


    @Override
    public void onStart() {
        super.onStart();

    }

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
        materialToolbar = v.findViewById(R.id.top_app_bar);
        materialToolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        story = v.findViewById(R.id.story_recy);
        story.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //story.setAdapter(new StoryAdapter());
        publicaciones_recyclerView = v.findViewById(R.id.home_publication_recyclerView);
        crearRepositorio();
        return v;
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

    private void crearRepositorio(){
        for (String id : MainActivity.userList.get(0).getIds_amigos_list()){
            MainActivity.databaseReference.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot ds : snapshot.child("Publicaciones").getChildren()){
                            String pub_id = ds.getValue().toString();
                            crearPublicacion(pub_id);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        MainActivity.userList.get(0).setPublications_amigos(publicationList);
        for (String p_id : MainActivity.userList.get(0).getUrl_publications_user()){
            MainActivity.publicacionDBReference.child(p_id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String publicacion_id = snapshot.child("id_publicacion").getValue().toString();
                        String imagen = snapshot.child("imagen_publicacion").getValue().toString();
                        String texto = snapshot.child("texto_publicacion").getValue().toString();
                        String user = snapshot.child("user_propietario").getValue().toString();
                        List<String> comentlist_publi = new ArrayList<>();
                        List<String> userlist_likes = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.child("Comentarios").getChildren()){
                            String com = ds.getValue().toString();
                            comentlist_publi.add(com);
                        }
                        for (DataSnapshot dataS : snapshot.child("Likes").getChildren()){
                            String username = dataS.getValue().toString();
                            userlist_likes.add(username);
                        }
                        Publication p = new Publication(publicacion_id, user, texto, userlist_likes, imagen, comentlist_publi);
                        userPublications.add(p);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            MainActivity.userList.get(0).setPublications_user(userPublications);
        }
        publicaciones_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        publicaciones_recyclerView.setAdapter(new PublicationAdapter(publicationList));
    }

    private void crearPublicacion(String id){
        MainActivity.publicacionDBReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String publicacion_id = snapshot.child("id_publicacion").getValue().toString();
                    String imagen = snapshot.child("imagen_publicacion").getValue().toString();
                    String texto = snapshot.child("texto_publicacion").getValue().toString();
                    String user = snapshot.child("user_propietario").getValue().toString();
                    List<String> comentlist_publi = new ArrayList<>();
                    List<String> userlist_likes = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.child("Comentarios").getChildren()){
                        String com = ds.getValue().toString();
                        comentlist_publi.add(com);
                    }
                    for (DataSnapshot dataS : snapshot.child("Likes").getChildren()){
                        String username = dataS.getValue().toString();
                        userlist_likes.add(username);
                    }
                    Publication p = new Publication(publicacion_id, user, texto, userlist_likes, imagen, comentlist_publi);
                    publicationList.add(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}