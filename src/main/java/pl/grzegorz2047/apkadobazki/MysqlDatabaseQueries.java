package pl.grzegorz2047.apkadobazki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MysqlDatabaseQueries implements DatabaseQueries {
    private final Database database;

    public MysqlDatabaseQueries(Database database) {
        this.database = database;
    }

    public void insertPlayer(String playerName, String firstName, String lastName, int points) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT IGNORE INTO players (playerName, firstName, lastName, points) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, playerName);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setInt(4, points);
        preparedStatement.execute();
        preparedStatement.clearParameters();
        preparedStatement.close();
    }


    @Override
    public Optional<Player> getPlayer(String playerName) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE playerName=?");
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (!next) {
                return Optional.empty();
            }
            return Optional.of(new Player(
                    resultSet.getString("playerName"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("points")
            ));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Optional.empty();
        }
    }
}
