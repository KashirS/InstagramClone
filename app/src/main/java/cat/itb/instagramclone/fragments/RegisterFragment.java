package cat.itb.instagramclone.fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;


public class RegisterFragment extends Fragment implements View.OnClickListener, OnCompleteListener, ValueEventListener {

    private TextInputEditText username;
    private TextInputLayout usernameLayout;
    private TextInputEditText password;
    private TextInputLayout passwordLayout;
    private TextInputEditText repeatPassword;
    private TextInputLayout repeatPasswordLayout;
    private TextInputEditText email;
    private TextInputLayout emailLayout;
    private TextInputEditText name;
    private TextInputLayout nameLayout;
    private TextInputEditText surname;
    private TextInputLayout surnameLayout;
    private MaterialCheckBox terms;
    private MaterialButton register;
    private MaterialButton login;
    boolean creado = false;
    List<String> userlist_creada;
    List<String> comentlist_creada;
    List<String> url_publicationlist;
    List<Publication> publicationList;
    final String POR_DEFECTO_URL_IMAGE = "https://firebasestorage.googleapis.com/v0/b/instagram-clone-a09bc.appspot.com/o/image%2Fimagen_predeterminada.png?alt=media&token=b87d6c5d-34d7-4507-b0fc-616b83ec8bf8";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_register, container, false);

        username = v.findViewById(R.id.register_username);
        usernameLayout = v.findViewById(R.id.outlined_username);

        password = v.findViewById(R.id.register_password);
        passwordLayout = v.findViewById(R.id.outlined_password);

        repeatPassword = v.findViewById(R.id.register_repassword);
        repeatPasswordLayout = v.findViewById(R.id.outlined_repassword);

        email = v.findViewById(R.id.register_email);
        emailLayout = v.findViewById(R.id.outlined_email);

        name = v.findViewById(R.id.register_name);
        nameLayout = v.findViewById(R.id.outlined_name);

        surname = v.findViewById(R.id.register_surname);
        surnameLayout = v.findViewById(R.id.outlined_surname);

        terms = v.findViewById(R.id.terms_checkBox);
        register = v.findViewById(R.id.register_register);
        login = v.findViewById(R.id.login_button_register);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_register:
                verifyAll();
                break;
            case R.id.login_button_register:
                Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);
                break;
        }
    }

    private void crearusuario(String username, String password, String email, String name, String surname){
        Map<String, String> datosUsuario = new HashMap<>();
        DatabaseReference ref = MainActivity.databaseReference.push();
        datosUsuario.put("id_usuario", ref.getKey());
        datosUsuario.put("username", "@"+username);
        datosUsuario.put("password", password);
        datosUsuario.put("email_usuario", email);
        datosUsuario.put("nombre_usuario", name);
        datosUsuario.put("apellidos_usuario", surname);
        datosUsuario.put("descripcion_usuario", "");
        datosUsuario.put("imagen_usuario", POR_DEFECTO_URL_IMAGE);
        ref.setValue(datosUsuario);
        ref.child("Amigos").setValue(crearMapAmigos());
        ref.child("Publicaciones").setValue(crearMapPublicaciones()).addOnCompleteListener(this);
        getPublicaciones();
        MainActivity.user = new User(ref.getKey(),username, password, name, surname, POR_DEFECTO_URL_IMAGE, email, url_publicationlist, publicationList, "");

    }

    public void getPublicaciones(){
        MainActivity.publicacionDBReference.addValueEventListener(this);
    }

    private Map<String, String> crearMapAmigos(){
        Map<String, String> datosAmigos = new HashMap<>();
        datosAmigos.put("id","-MZn49rmuaABnBwCJ7xq");
        return datosAmigos;
    }


    private Map<String, String> crearMapPublicaciones(){
        Map<String, String> datosPublicaiones = new HashMap<>();
        datosPublicaiones.put("id","-MZijMVqgkXEsLuvzL3F");
        return datosPublicaiones;
    }

    private void verifyAll() {
        String userVerify, passwordVerify, repeatPasswordVer, emailVerify, nameVerify, surnameVerify;
        userVerify = username.getText().toString();
        passwordVerify = password.getText().toString();
        repeatPasswordVer = repeatPassword.getText().toString();
        emailVerify = email.getText().toString();
        nameVerify = name.getText().toString();
        surnameVerify = surname.getText().toString();

        if (userVerify.isEmpty()){
            usernameLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            usernameLayout.setError("required field");
            usernameLayout.isEnabled();
        }else if (passwordVerify.isEmpty()){
            usernameLayout.setErrorEnabled(false);
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            passwordLayout.setError("required field");
            passwordLayout.isEnabled();
        }else if (repeatPasswordVer.isEmpty()){
            passwordLayout.setErrorEnabled(false);
            passwordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            repeatPasswordLayout.setError("required field");
            repeatPasswordLayout.isEnabled();
        }else if (!(passwordVerify.equals(passwordVerify))){
            repeatPasswordLayout.setErrorEnabled(false);
            repeatPasswordLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            repeatPasswordLayout.setError("password isn't equal");
            repeatPasswordLayout.isEnabled();
        }else if (emailVerify.isEmpty()){
            repeatPasswordLayout.setErrorEnabled(false);
            emailLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            emailLayout.setError("required field");
            emailLayout.isEnabled();
        }else if (nameVerify.isEmpty()){
            emailLayout.setErrorEnabled(false);
            nameLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            nameLayout.setError("required field");
            nameLayout.isEnabled();
        }else if (surnameVerify.isEmpty()){
            nameLayout.setErrorEnabled(false);
            surnameLayout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            surnameLayout.setError("required field");
            surnameLayout.isEnabled();
        }else {
            surnameLayout.setErrorEnabled(false);
            crearusuario(userVerify, passwordVerify, emailVerify, nameVerify, surnameVerify);
            if (creado){
                Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_homeFragment);
            }
        }

    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isComplete()){
            creado = true;
            Toast.makeText(getContext(),"Usuario Creado",Toast.LENGTH_LONG).show();
        }else if (task.isCanceled()){
            creado = false;
            Toast.makeText(getContext(),"Usuario Cancelado",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()){
            userlist_creada = new ArrayList<String>();
            comentlist_creada = new ArrayList<>();
            String id = snapshot.child("-MZijMVqgkXEsLuvzL3F").child("id_publicacion").getValue().toString();
            String imagen = snapshot.child("-MZijMVqgkXEsLuvzL3F").child("imagen_usuario").getValue().toString();
            String texto = snapshot.child("-MZijMVqgkXEsLuvzL3F").child("texto_publicacion").getValue().toString();
            String user = snapshot.child("-MZijMVqgkXEsLuvzL3F").child("user_propietario").getValue().toString();
            for (DataSnapshot ds : snapshot.child("-MZijMVqgkXEsLuvzL3F").child("Comentarios").getChildren()){
                String com = ds.getValue().toString();
                comentlist_creada.add(com);
            }
            for (DataSnapshot dataS : snapshot.child("-MZijMVqgkXEsLuvzL3F").child("Likes").getChildren()){
                String username = dataS.getValue().toString();
                userlist_creada.add(username);
            }
            publicationList = new ArrayList<Publication>();
            url_publicationlist = new ArrayList<String>();
            publicationList.add(new Publication(id, user, texto, userlist_creada, imagen, comentlist_creada));
            url_publicationlist.add(id);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}