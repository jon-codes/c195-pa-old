package models;

public record User(Integer id, String username, String password) {

    public boolean checkPassword(String candidatePassword) {
        return candidatePassword.equals(password);
    }
}
