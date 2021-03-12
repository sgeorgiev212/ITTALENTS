package gastation_task.databaseOperations;

public class DatabaseInfo {

    private String url;
    private String username;
    private String password;

    public DatabaseInfo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
