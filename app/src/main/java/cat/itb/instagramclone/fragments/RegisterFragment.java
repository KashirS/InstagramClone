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
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.models.User;


public class RegisterFragment extends Fragment implements View.OnClickListener{

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
        //datosUsuario.put("publicaciones",new ArrayList<String>());

        ref.setValue(datosUsuario).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){
                    Toast.makeText(getContext(),"Usuario Creado",Toast.LENGTH_LONG).show();
                }else if (task.isCanceled()){
                    Toast.makeText(getContext(),"Usuario Cancelado",Toast.LENGTH_LONG).show();
                }
            }
        });
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
            //Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_homeFragment);
        }

    }
}