package cat.itb.instagramclone.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cat.itb.instagramclone.R;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private TextInputEditText username;
    private TextInputEditText password;
    private MaterialButton login;
    private MaterialButton register;
    private MaterialButton forgotPassword;
    private TextInputLayout userInput;
    private TextInputLayout passInput;

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


        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

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
            Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeFragment);
        }
    }
    private void cargarPreferencias(){
        SharedPreferences preferencias = getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario = preferencias.getString("user", "No existe la info.");
        String password = preferencias.getString("password", "No existe la info.");

        //textView.setText(usuario)
        //textView.setText(password)
    }

    private void guardarPreferencias(){
        SharedPreferences preferencias = getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario = "" ; //TextView.getText().toString();
        String password = ""; //TextView.getText().toString();
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("user", usuario);
        editor.putString("password", password);
        editor.commit();
    }

}