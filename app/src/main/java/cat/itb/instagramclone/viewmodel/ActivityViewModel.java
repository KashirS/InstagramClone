package cat.itb.instagramclone.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.itb.instagramclone.R;
import cat.itb.instagramclone.models.Notification;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

public class ActivityViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static List<Notification> notificationList = new ArrayList<Notification>();

    Notification[] notifications = {
            new Notification(1, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 1"),
            new Notification(2, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 2"),
            new Notification(3, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 3"),
            new Notification(4, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 4"),
            new Notification(5, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 5"),
            new Notification(6, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 6"),
            new Notification(7, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 7"),
            new Notification(8, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 8"),
            new Notification(9, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 9"),
            new Notification(10, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 10"),
            new Notification(11, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 11"),
            new Notification(12, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 12"),
            new Notification(13, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 13"),
            new Notification(14, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 14"),
            new Notification(15, new User(1, "@jorge", R.drawable.lambo, null, ""), "Notification 15")
    };

    public ActivityViewModel() {
        Collections.addAll(notificationList, notifications);
    }
}