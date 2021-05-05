package cat.itb.instagramclone.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.internal.Sleeper;

import java.util.ArrayList;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

import static java.lang.Thread.sleep;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private TextInputEditText username;
    private TextInputEditText password;
    private MaterialButton login;
    private MaterialButton register;
    private MaterialButton forgotPassword;
    private TextInputLayout userInput;
    private TextInputLayout passInput;
    static List<User> log_list = new ArrayList<>();
    String log_name;
    String log_password;
    boolean logeado = false;
    List<String> userlist_likes;
    List<String> comentlist_publi;
    List<Publication> publiList = new ArrayList<>();
    static Publication prueba;
    List<Publication> publications_amigos = new ArrayList<>();
    List<String> id_publications_amigos = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        username = v.findViewById(R.id.user_name_login_editext);
        password = v.findViewById(R.id.user_password_login_editext);
        login = v.findViewById(R.id.login_button);
        register = v.findViewById(R.id.register_button_login);
        userInput = v.findViewById(R.id.user_name_login);
        passInput = v.findViewById(R.id.user_password_login);
        login.setOnClickListener(this::onClick);
        register.setOnClickListener(this::onClick);
        cargarPreferencias();
        MainActivity.quitarNavDrawer();
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                verifier();
                break;
            case R.id.register_button_login:
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_registerFragment);
                break;
        }
    }

    private void verifier() {
        String usernameVerify;
        String passwordVerify;
        usernameVerify = username.getText().toString();
        passwordVerify = password.getText().toString();

        if (usernameVerify.isEmpty()) {
            userInput.setError("required field");
            userInput.isEnabled();
        }else if (passwordVerify.isEmpty()){
            userInput.setErrorEnabled(false);
            passInput.setError("required field");
            passInput.isEnabled();
        }else if (passwordVerify.length() < 8){
            passInput.setErrorEnabled(false);
            passInput.setError("min 8 character");
            passInput.isEnabled();
        }else {

            log_name = usernameVerify;
            log_password = passwordVerify;
            guardarPreferencias(usernameVerify, passwordVerify);
            boolean log = logear(log_name, log_password);
            crearRepo();
            if (log){
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeFragment);
            }else {
                Toast.makeText(getContext(), "Error al logear", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void crearRepo(){
        List<Publication> p_l = new ArrayList<>();
        for (String id_user : MainActivity.user.getIds_amigos_list()){
            for(Publication p : MainActivity.publicacionesList){
                if (p.getUser_propietario().equals(id_user)){
                    p_l.add(p);
                }
            }
        }
        MainActivity.user.setPublications_amigos(p_l);
    }

    private boolean logear(String name, String password){
        boolean b = false;
        for (User userDB : MainActivity.users_DB_List){
            if (userDB.getUsername().equals("@"+name) && userDB.getPassword().equals(password)){
                MainActivity.user = userDB;
                b = true;
            }
        }
        return b;
    }

    private void cargarPreferencias(){
        SharedPreferences preferencias = getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario = preferencias.getString("user", "");
        String pass = preferencias.getString("password", "");

        username.setText(usuario);
        password.setText(pass);
    }

    private void guardarPreferencias(String user, String pass){
        SharedPreferences preferencias = getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("user", user);
        editor.putString("password", pass);
        editor.commit();
    }

}