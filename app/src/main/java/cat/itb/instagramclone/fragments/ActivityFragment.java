package cat.itb.instagramclone.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.adapters.ActivityAdapter;
import cat.itb.instagramclone.viewmodel.ActivityViewModel;

public class ActivityFragment extends Fragment {

    private ActivityViewModel mViewModel;
    RecyclerView notification_recyclerView;


    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ActivityViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment, container, false);
        notification_recyclerView = v.findViewById(R.id.notification_recyclerView);
        notification_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notification_recyclerView.setAdapter(new ActivityAdapter(mViewModel.notificationList));
        return v;
    }


}