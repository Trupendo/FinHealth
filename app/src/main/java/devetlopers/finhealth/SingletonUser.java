package devetlopers.finhealth;

public class SingletonUser {

    private static SingletonUser instance;
    private User loggedUser;

    private SingletonUser() {
    }

    public static SingletonUser getInstance() {
        if (instance == null)
            instance = new SingletonUser();
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
