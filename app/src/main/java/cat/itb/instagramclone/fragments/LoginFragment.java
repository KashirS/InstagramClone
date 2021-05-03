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


public class LoginFragment extends Fragment implements View.OnClickListener, ValueEventListener{

    private TextInputEditText username;
    private TextInputEditText password;
    private MaterialButton login;
    private MaterialButton register;
    private MaterialButton forgotPassword;
    private TextInputLayout userInput;
    private TextInputLayout passInput;
    List<User> log_list = new ArrayList<>();
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
                //Toast.makeText(getContext(), MainActivity.user.getPublications_amigos().get(0).getId_publicacion(), Toast.LENGTH_LONG).show();
                MainActivity.user = log_list.get(0);
                //Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeFragment);
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

    private void crearPublicaciones(String id_user){
        List<String> list_string = new ArrayList<>();
        MainActivity.databaseReference.child(id_user).child("Amigos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    List<Publication> list = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()){
                        String user_amigo_id = ds.getValue().toString();
                        crearPublicacionesAmigos(user_amigo_id);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "ERROR CREANDO PUBLICACIONES PARA VER USUARIO ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void crearPublicacionesAmigos(String id){

        MainActivity.databaseReference.child(id).child("Publicaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    String id_publicacion_amigo = ds.getValue().toString();
                    crearPublicacion(id_publicacion_amigo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "ERROR CREANDO PUBLICACIONES AMIGOS ", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void crearUser(String id_user){
        List<String> list_string = new ArrayList<>();
        MainActivity.databaseReference.child(id_user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    List<Publication> list = new ArrayList<>();
                    String id_user = snapshot.child("id_usuario").getValue().toString();
                    String username = snapshot.child("username").getValue().toString();
                    String password = snapshot.child("password").getValue().toString();
                    String name = snapshot.child("nombre_usuario").getValue().toString();
                    String apellido = snapshot.child("apellido_usuario").getValue().toString();
                    String email = snapshot.child("email_usuario").getValue().toString();
                    String descripcion = snapshot.child("descripcion_usuario").getValue().toString();
                    String image = snapshot.child("imagen_usuario").getValue().toString();
                    for (DataSnapshot ds : snapshot.child("Publicaciones").getChildren()){
//TODO: ACacbar
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "ERROR CREANDO PUBLICACIONES PARA VER USUARIO ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void crearPublicacion(String id_publicacion){
        prueba = new Publication();
        MainActivity.publicacionDBReference.child(id_publicacion).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    List<String> likes = new ArrayList<>();
                    List<String> comentarios = new ArrayList<>();
                    String id_publi_amigo = snapshot.child("id_publicacion").getValue().toString();
                    Toast.makeText(getContext(), "ID_PUBLI_USER2: "+id_publi_amigo, Toast.LENGTH_SHORT).show();
                    String imagen = snapshot.child("imagen_publicacion").getValue().toString();
                    String texto = snapshot.child("id_publicacion").getValue().toString();
                    String user_ammigo_id = snapshot.child("user_propietario").getValue().toString();
                    for (DataSnapshot ds : snapshot.child("Likes").getChildren()){
                        String like = ds.getValue().toString();
                        likes.add(like);
                    }
                    for (DataSnapshot ds2 : snapshot.child("Comentarios").getChildren()){
                        String comentario = ds2.getValue().toString();
                        comentarios.add(comentario);
                    }
                    Publication publi = new Publication(id_publi_amigo, user_ammigo_id, texto, likes, imagen, comentarios);
                    publications_amigos.add(publi);
                    id_publications_amigos.add(id_publi_amigo);
                    Toast.makeText(getContext(), "ID_USER: "+publi.getUser_propietario(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "ERROR CREANDO PUBLICACION AMIGO ", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void findPublication(String id){
        MainActivity.publicacionDBReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String publicacion_id = snapshot.child("id_publicacion").getValue().toString();
                    String imagen = snapshot.child("imagen_publicacion").getValue().toString();
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
                    Publication p = new Publication(publicacion_id, user, texto, userlist_likes, imagen, comentlist_publi);
                    publiList.add(p);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()){
                for (DataSnapshot ds : snapshot.getChildren()){
                    String username = ds.child("username").getValue().toString();
                    String password = ds.child("password").getValue().toString();
                    if (username.equals("@"+log_name) && password.equals(log_password)){
                        logeado = true;
                        List<Publication> publicationList = new ArrayList<>();
                        List<String> idPublicationList = new ArrayList<>();
                        String id = ds.child("id_usuario").getValue().toString();
                        String email = ds.child("email_usuario").getValue().toString();
                        String name = ds.child("nombre_usuario").getValue().toString();
                        String apellido = ds.child("apellidos_usuario").getValue().toString();
                        String descripcion = ds.child("descripcion_usuario").getValue().toString();
                        String imagen = ds.child("imagen_usuario").getValue().toString();
                        for(DataSnapshot dataPublicaciones : ds.child("Publicaciones").getChildren()){
                            String id_publi = dataPublicaciones.getValue().toString();
                            idPublicationList.add(id_publi);
                            //Toast.makeText(getContext(), id_publi, Toast.LENGTH_SHORT).show();
                            findPublication(id_publi);

                        }
                        crearPublicaciones(id);
                        User u = new User(id, username, password, name, apellido, imagen, email, idPublicationList, publiList, descripcion, id_publications_amigos, publications_amigos);
                        //addUser(u); TODO: Falla aqui
                        //Toast.makeText(getContext(), MainActivity.user.getNombre_usuario(), Toast.LENGTH_LONG).show();
                    }
                }
        }
    }

    private void addUser(User u){
        log_list.add(u);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(getContext(), "ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
    }
}