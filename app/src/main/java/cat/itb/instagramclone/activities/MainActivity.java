package cat.itb.instagramclone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    static BottomNavigationView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        view = findViewById(R.id.bottom_navigation);

    }

    public static void mostrarNavDrawer(){
        view.setVisibility(View.VISIBLE);
    }

    public static void quitarNavDrawer(){
        view.setVisibility(View.INVISIBLE);
    }
}