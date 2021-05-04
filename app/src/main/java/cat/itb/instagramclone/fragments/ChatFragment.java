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

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.activities.MainActivity;
import cat.itb.instagramclone.adapters.ChatAdapter;
import cat.itb.instagramclone.viewmodel.ChatViewModel;

public class ChatFragment extends Fragment {

    private ChatViewModel mViewModel;
    RecyclerView chat_recyclerView;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
        MainActivity.quitarNavDrawer();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_fragment, container, false);
        chat_recyclerView = v.findViewById(R.id.chat_recyclerView);
        chat_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chat_recyclerView.setAdapter(new ChatAdapter(mViewModel.chatsList));
        return v;
    }

}