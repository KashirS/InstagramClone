package cat.itb.instagramclone.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.itb.instagramclone.models.Notification;
import cat.itb.instagramclone.models.Publication;
import cat.itb.instagramclone.models.User;

public class ActivityViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static List<Notification> notificationList = new ArrayList<Notification>();

    Notification[] notifications = {
            new Notification(1, new User(1, "@kashir22", 1), "Notification 1"),
            new Notification(2, new User(2, "@jorge35", 2), "Notification 2"),
            new Notification(1, new User(3, "@kashir11", 3), "Notification 3"),
            new Notification(1, new User(4, "@jorge22", 4), "Notification 4"),
            new Notification(1, new User(5, "@soymanolo", 5), "Notification 5"),
    };

    public ActivityViewModel() {
        Collections.addAll(notificationList, notifications);
    }
}