package cat.itb.instagramclone.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cat.itb.instagramclone.R;


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
                Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_homeFragment);
                break;
            case R.id.login_button_register:
                Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);
                break;
        }
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
            usernameLayout.setError("required field");
            usernameLayout.isEnabled();
        }else if (passwordVerify.isEmpty()){
            usernameLayout.setErrorEnabled(false);
            passwordLayout.setError("required field");
            passwordLayout.isEnabled();
        }else if (repeatPasswordVer.isEmpty()){
            passwordLayout.setErrorEnabled(false);
            repeatPasswordLayout.setError("required field");
            repeatPasswordLayout.isEnabled();
        }else if (!(passwordVerify.equals(passwordVerify))){
            repeatPasswordLayout.setErrorEnabled(false);
            repeatPasswordLayout.setError("password isn't equal");
            repeatPasswordLayout.isEnabled();
        }else if (emailVerify.isEmpty()){
            repeatPasswordLayout.setErrorEnabled(false);
            emailLayout.setError("required field");
            emailLayout.isEnabled();
        }else if (nameVerify.isEmpty()){
            emailLayout.setErrorEnabled(false);
            nameLayout.setError("required field");
            nameLayout.isEnabled();
        }else if (surnameVerify.isEmpty()){
            nameLayout.setErrorEnabled(false);
            surnameLayout.setError("required field");
            surnameLayout.isEnabled();
        }else {
            surnameLayout.setErrorEnabled(false);
        }

    }
}