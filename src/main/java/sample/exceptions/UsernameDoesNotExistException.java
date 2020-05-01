package sample.exceptions;

public class UsernameDoesNotExistException extends Exception {
    private String username;

    public UsernameDoesNotExistException(String username) {
        super(String.format("'%s' is not a valid username", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
