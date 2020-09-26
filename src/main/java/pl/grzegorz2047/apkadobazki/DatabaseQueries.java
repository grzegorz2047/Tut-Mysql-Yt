package pl.grzegorz2047.apkadobazki;

import java.sql.SQLException;
import java.util.Optional;

public interface DatabaseQueries {
    void insertPlayer(String userName, String firstName, String lastName, int points) throws SQLException;

    Optional<Player> getPlayer(String userName);
}
