package cat.itb.instagramclone.fragments;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.adapters.PublicationAdapter;
import cat.itb.instagramclone.adapters.StoryAdapter;
import cat.itb.instagramclone.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    RecyclerView publicaciones_recyclerView;
    RecyclerView story;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        MainActivity.mostrarNavDrawer();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        publicaciones_recyclerView = v.findViewById(R.id.home_publication_recyclerView);
        publicaciones_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        publicaciones_recyclerView.setAdapter(new PublicationAdapter(mViewModel.publicacionesList));

        story = v.findViewById(R.id.story_recy);
        story.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        story.setAdapter(new StoryAdapter(mViewModel.storyList));

        return v;
    }



}