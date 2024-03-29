package cat.itb.instagramclone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import androidx.navigation.fragment.NavHostFragment;

import android.content.ContentResolver;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageRegistrar;

import java.util.ArrayList;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.fragments.ActivityFragment;
import cat.itb.instagramclone.fragments.HomeFragment;
import cat.itb.instagramclone.fragments.ProfileFragment;
import cat.itb.instagramclone.fragments.SearchFragment;
import cat.itb.instagramclone.fragments.UploadImage;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener, ValueEventListener {

    final String URL_DB = "https://instagram-clone-a09bc-default-rtdb.firebaseio.com/";
    final String REF_USER_DB = "User";
    final String REF_PUBLIC_DB = "Publicaciones";
    static BottomNavigationView view;
    public static DatabaseReference databaseReference;
    public static DatabaseReference publicacionDBReference;
    public static FirebaseDatabase database;
    public static User user;
    public static DatabaseReference DBReference;
    public static StorageReference storageReference;
    public static List<User> userList;
    public static List<Publication> publicacionesList;
    public static List<User> user_log_list;
    public static List<User> users_DB_List;
    FirebaseAuth auth;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conectarFirebase();
        cargarDatos();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        view = findViewById(R.id.bottom_navigation);
        view.setOnNavigationItemSelectedListener(this);
        //TODO: https://es.stackoverflow.com/questions/254882/android-c%C3%B3mo-reducir-tama%C3%B1o-de-un-bitmap Usos bitmap sino URL

    }

    public static void mostrarNavDrawer(){
        view.setVisibility(View.VISIBLE);
    }

    public static void quitarNavDrawer(){
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_icon:
                mostrarFragmentSeleccionado(new HomeFragment());
                break;
            case R.id.search_icon:
                mostrarFragmentSeleccionado(new SearchFragment());
                break;
            case R.id.activity_icon:
                mostrarFragmentSeleccionado(new ActivityFragment());
                break;
            case R.id.profile_icon:
                mostrarFragmentSeleccionado(new ProfileFragment());
                break;
            case R.id.upload_image:
                mostrarFragmentSeleccionado(new UploadImage());
                break;
        }
        return true;
    }

    private void mostrarFragmentSeleccionado(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void conectarFirebase(){
        database = FirebaseDatabase.getInstance(URL_DB);
        storageReference = FirebaseStorage.getInstance("gs://instagram-clone-a09bc.appspot.com").getReference("/image");
        databaseReference = database.getReference(REF_USER_DB);
        publicacionDBReference = database.getReference(REF_PUBLIC_DB);
        DBReference = database.getReference();

    }

    public void cargarDatos(){
        publicacionesList = new ArrayList<>();
        userList = new ArrayList<>();
        user_log_list = new ArrayList<>();
        users_DB_List = new ArrayList<>();
        DBReference.addValueEventListener(this);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()){
            for (DataSnapshot data : snapshot.getChildren()){
                if (data.getKey().equals("Publicaciones")){
                    for (DataSnapshot ds_pub : data.getChildren()){
                        List<String> likes = new ArrayList<>();
                        List<String> comentarios = new ArrayList<>();
                        String id_publi = ds_pub.child("id_publicacion").getValue().toString();
                        String imagen = ds_pub.child("imagen_publicacion").getValue().toString();
                        String texto = ds_pub.child("texto_publicacion").getValue().toString();
                        String user_prop_id = ds_pub.child("user_propietario").getValue().toString();
                        for (DataSnapshot ds : ds_pub.child("Likes").getChildren()){
                            String like = ds.getValue().toString();
                            likes.add(like);
                        }
                        for (DataSnapshot ds2 : ds_pub.child("Comentarios").getChildren()){
                            String comentario = ds2.getValue().toString();
                            comentarios.add(comentario);
                        }
                        Publication p = new Publication(id_publi, user_prop_id, texto, likes, imagen, comentarios);
                        publicacionesList.add(p);
                    }
                }
                if (data.getKey().equals("User")){
                    for (DataSnapshot ds : data.getChildren()){
                        List<Publication> user_publicationList = new ArrayList<>();
                        List<Publication> amigos_userlist = new ArrayList<>();
                        List<String> ids_amigos_list = new ArrayList<>();
                        String id_user = ds.child("id_usuario").getValue().toString();
                        String username = ds.child("username").getValue().toString();
                        String password = ds.child("password").getValue().toString();
                        String name = ds.child("nombre_usuario").getValue().toString();
                        String apellido = ds.child("apellidos_usuario").getValue().toString();
                        String email = ds.child("email_usuario").getValue().toString();
                        String descripcion = ds.child("descripcion_usuario").getValue().toString();
                        String image = ds.child("imagen_usuario").getValue().toString();
                        for (DataSnapshot dataPublic : ds.child("Publicaciones").getChildren()){
                            List<String> comentarios = new ArrayList<>();
                            List<String> likes = new ArrayList<>();
                            String id_publi = dataPublic.getValue().toString();
                            String imagen_p = snapshot.child("Publicaciones").child(id_publi).child("imagen_publicacion").getValue().toString();
                            String texto_p = snapshot.child("Publicaciones").child(id_publi).child("texto_publicacion").getValue().toString();
                            String user_prop_p = snapshot.child("Publicaciones").child(id_publi).child("user_propietario").getValue().toString();
                            for (DataSnapshot ds_c : snapshot.child("Publicaciones").child(id_publi).child("Comentarios").getChildren()){
                                String com = ds_c.getValue().toString();
                                comentarios.add(com);
                            }
                            for (DataSnapshot ds_1 : snapshot.child("Publicaciones").child(id_publi).child("Likes").getChildren()){
                                String like = ds_1.getValue().toString();
                                likes.add(like);
                            }
                            Publication pub = new Publication(id_publi, user_prop_p, texto_p, likes, imagen_p, comentarios);
                            user_publicationList.add(pub);
                        }
                        for (DataSnapshot dataAmigo : ds.child("Amigos").getChildren()){
                            String id_amigo = dataAmigo.getValue().toString();
                            ids_amigos_list.add(id_amigo);
                            /*for (DataSnapshot data_publi : snapshot.child("Publicaciones").getChildren()){
                                String user_prop = data_publi.child("user_propietario").getValue().toString();
                                if (id_amigo.equals(user_prop)){
                                    List<String> comentarios = new ArrayList<>();
                                    List<String> likes = new ArrayList<>();
                                    String id_publi = data_publi.child("id_publicacion").getValue().toString();
                                    String texto_pub = data_publi.child("texto_publicacion").getValue().toString();
                                    String imagen_pub = data_publi.child("imagen_publicacion").getValue().toString();
                                    for (DataSnapshot ds_com : data_publi.child("Comentarios").getChildren()){
                                        String com = ds_com.getValue().toString();
                                        comentarios.add(com);
                                    }
                                    for (DataSnapshot ds_1 : data_publi.child("Likes").getChildren()){
                                        String like = ds_1.getValue().toString();
                                        likes.add(like);
                                    }
                                    Publication publi = new Publication(id_publi, id_amigo, texto_pub, likes, texto_pub, comentarios);
                                    amigos_userlist.add(publi);
                                }
                            }*/

                        }

                        User u = new User(id_user, username, password, name, apellido, image, email, descripcion, user_publicationList, ids_amigos_list);
                        users_DB_List.add(u);
                    }
                }
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}