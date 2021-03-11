package cat.itb.instagramclone.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.itb.instagramclone.R;


public class LoginFragment extends Fragment {

    Fragment fragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        return v;
    }
    private void cargarPreferencias(){
        SharedPreferences preferencias = getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario = preferencias.getString("user", "No existe la info.");
        String password = preferencias.getString("password", "No existe la info.");

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