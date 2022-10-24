package models;

public record User(int id, String username, String password) {

    public boolean checkPassword(String candidatePassword) {
        return candidatePassword.equals(password);
    }
}
