package cat.itb.instagramclone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import cat.itb.instagramclone.R;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            currentFragment = new Fragment();
            changeFragment();
        }

    }

    private void changeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,currentFragment).commit();
    }
}