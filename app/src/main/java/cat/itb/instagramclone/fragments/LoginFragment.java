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

import java.util.ArrayList;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;


public class LoginFragment extends Fragment implements View.OnClickListener, ValueEventListener{

    private TextInputEditText username;
    private TextInputEditText password;
    private MaterialButton login;
    private MaterialButton register;
    private MaterialButton forgotPassword;
    private TextInputLayout userInput;
    private TextInputLayout passInput;
    User logUser;
    String log_name;
    String log_password;
    boolean logeado = false;
    List<String> userlist_likes;
    List<String> comentlist_publi;
    Publication publication_creada;

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

    public void getUsuarioLogIn(){
        MainActivity.databaseReference.addValueEventListener(this);
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
            getUsuarioLogIn();
            MainActivity.user = logUser;
            if (logeado){
                Toast.makeText(getContext(),"Loged", Toast.LENGTH_LONG).show();
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeFragment);
            }
        }
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

    public Publication findPublication(String id){
        MainActivity.publicacionDBReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String user_id = snapshot.child("id_publicacion").getValue().toString();
                    String imagen = snapshot.child("imagen_usuario").getValue().toString();
                    String texto = snapshot.child("texto_publicacion").getValue().toString();
                    String user = snapshot.child("user_propietario").getValue().toString();
                    comentlist_publi = new ArrayList<>();
                    userlist_likes = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.child("Comentarios").getChildren()){
                        String com = ds.getValue().toString();
                        comentlist_publi.add(com);
                    }
                    for (DataSnapshot dataS : snapshot.child("Likes").getChildren()){
                        String username = dataS.getValue().toString();
                        userlist_likes.add(username);
                    }
                    publication_creada = new Publication(user_id, user, texto, userlist_likes, imagen, comentlist_publi);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return publication_creada;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()){
                List<Publication> publicationList = new ArrayList<>();
                List<String> idPublicationList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()){
                    String id = ds.getValue().toString();
                    String username = ds.child("username").getValue().toString();
                    String password = ds.child("password").getValue().toString();
                    String email = ds.child("email_usuario").getValue().toString();
                    String name = ds.child("nombre_usuario").getValue().toString();
                    String apellido = ds.child("apellidos_usuario").getValue().toString();
                    String descripcion = ds.child("descripcion_usuario").getValue().toString();
                    String imagen = ds.child("imagen_usuario").getValue().toString();
                    for(DataSnapshot dataPublicaciones : ds.child("Publicaciones").getChildren()){
                        String id_publi = dataPublicaciones.getValue().toString();
                        publicationList.add(findPublication(id_publi));
                    }
                    if (username.equals("@"+log_name) && password.equals(log_password)){
                        logUser = new User(id, username, password, name, apellido, imagen, email, idPublicationList, publicationList, descripcion);
                        Toast.makeText(getContext(), "Log In", Toast.LENGTH_LONG).show();
                        logeado = true;
                    }
                }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(getContext(), "ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
    }
}