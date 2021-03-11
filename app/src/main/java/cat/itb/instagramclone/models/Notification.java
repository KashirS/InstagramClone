package cat.itb.instagramclone.models;

public class Notification {
    int id_notification;
    User user_notification;
    String notification;

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public User getUser_notification() {
        return user_notification;
    }

    public void setUser_notification(User user_notification) {
        this.user_notification = user_notification;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}