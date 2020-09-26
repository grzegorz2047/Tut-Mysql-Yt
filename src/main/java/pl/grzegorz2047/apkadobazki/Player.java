package pl.grzegorz2047.apkadobazki;

public class Player {
    private final String playerName;
    private final String firstName;
    private final String lastName;
    private final int points;

    public Player(String playerName, String firstName, String lastName, int points) {
        this.playerName = playerName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPoints() {
        return points;
    }
}
