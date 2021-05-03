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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageRegistrar;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.fragments.ActivityFragment;
import cat.itb.instagramclone.fragments.HomeFragment;
import cat.itb.instagramclone.fragments.ProfileFragment;
import cat.itb.instagramclone.fragments.SearchFragment;
import cat.itb.instagramclone.fragments.UploadImage;
import cat.itb.instagramclone.models.User;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    final String URL_DB = "https://instagram-clone-a09bc-default-rtdb.firebaseio.com/";
    final String REF_USER_DB = "User";
    final String REF_PUBLIC_DB = "Publicaciones";
    static BottomNavigationView view;
    public static DatabaseReference databaseReference;
    public static DatabaseReference publicacionDBReference;
    public static FirebaseDatabase database;
    public static User user;
    public static StorageReference storageReference;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conectarFirebase();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

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

    }

    private void cargarUsuario(){
        //TODO: Registo usuarios https://www.youtube.com/watch?v=xwhEHb_AZ6k&list=RDCMUCskTj1cdSSOeCjZXVm2QS9Q&start_radio=1&t=1103
        //TODO: Login usuarios https://www.youtube.com/watch?v=IEc44_CxoyY&list=RDCMUCskTj1cdSSOeCjZXVm2QS9Q&index=2
    }

}