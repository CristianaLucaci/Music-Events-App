package sample.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    private String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("'%s' username is unavailable", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
