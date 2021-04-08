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
            new Notification(1, new User("", "@kashir22", R.drawable.vegeta, null, ""), "te empieza a seguir"),
            new Notification(2, new User("", "@manolo33", R.drawable.gohan, null, ""), "le ha dado like a tu foto"),
            new Notification(3, new User("", "@lAyoLi_34", R.drawable.instagram_logo, null, ""), "te empieza a seguir"),
            new Notification(4, new User("", "@juan_3", R.drawable.lambo, null, ""), "le ha dado like a tu foto"),
            new Notification(5, new User("", "@_elpepe956", R.drawable.vegeta, null, ""), "te empieza a seguir"),
            new Notification(6, new User("", "@goku", R.drawable.gogeta, null, ""), "le ha dado like a tu foto"),
            new Notification(7, new User("", "@the_boss", R.drawable.lambo, null, ""), "te ha empezado a seguir"),
            new Notification(8, new User("", "@juan2", R.drawable.instagram_logo, null, ""), "le ha dado like a tu foto"),
            new Notification(9, new User("", "@laura4", R.drawable.instagram_logo, null, ""), "le ha dado like a tu foto"),
            new Notification(10, new User("", "@", R.drawable.lambo, null, ""), "te empieza a seguir"),
            new Notification(11, new User("", "@manolo33", R.drawable.gohan, null, ""), "le ha dado like a tu foto"),
            new Notification(12, new User("", "@carlos5", R.drawable.broly, null, ""), "le ha dado like a tu foto"),
            new Notification(13, new User("", "@juan2", R.drawable.lambo, null, ""), "te empieza a seguir"),
            new Notification(14, new User("", "@carlos5", R.drawable.broly, null, ""), "le ha dado like a tu foto"),
            new Notification(15, new User("", "@manolo33", R.drawable.gohan, null, ""), "te empieza a seguir")
    };

    public ActivityViewModel() {
        Collections.addAll(notificationList, notifications);
    }
}