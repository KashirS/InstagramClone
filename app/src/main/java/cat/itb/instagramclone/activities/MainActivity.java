package cat.itb.instagramclone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.fragments.ActivityFragment;
import cat.itb.instagramclone.fragments.HomeFragment;
import cat.itb.instagramclone.fragments.ProfileFragment;
import cat.itb.instagramclone.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    static BottomNavigationView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        view = findViewById(R.id.bottom_navigation);
        view.setOnNavigationItemSelectedListener(this);

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
        }
        return true;
    }

    private void mostrarFragmentSeleccionado(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}