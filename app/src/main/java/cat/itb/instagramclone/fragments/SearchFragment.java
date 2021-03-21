package cat.itb.instagramclone.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.adapters.SearchAdapter;
import cat.itb.instagramclone.viewmodel.SearchViewModel;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    RecyclerView search_recyclerView;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.search_fragment, container, false);
        search_recyclerView = v.findViewById(R.id.search_recyclerView);
        search_recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        search_recyclerView.setAdapter(new SearchAdapter(SearchViewModel.searchesList));

        return v;
    }

}