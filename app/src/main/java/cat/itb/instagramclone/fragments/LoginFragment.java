package cat.itb.instagramclone.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.User;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private TextInputEditText username;
    private TextInputEditText password;
    private MaterialButton login;
    private MaterialButton register;
    private MaterialButton forgotPassword;
    private TextInputLayout userInput;
    private TextInputLayout passInput;
    User logUser;

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

    public void getUsuarioLogIn(String log_username, String log_password){

        MainActivity.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren()){
                        String id = ds.getValue().toString();
                        String username = ds.child("username").getValue().toString();
                        String password = ds.child("password").getValue().toString();
                        String email = ds.child("email_usuario").getValue().toString();
                        String name = ds.child("nombre_usuario").getValue().toString();
                        String apellido = ds.child("apellidos_usuario").getValue().toString();
                        Toast.makeText(getContext(), ""+username, Toast.LENGTH_LONG).show();
                        if (username.equals("@"+log_username) && password.equals(log_password)){
                            logUser = new User(id, username, password, email, name, apellido);
                            Toast.makeText(getContext(), "Log In", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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
            guardarPreferencias(usernameVerify, passwordVerify);
            getUsuarioLogIn(usernameVerify, passwordVerify);
            //Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeFragment);
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

}