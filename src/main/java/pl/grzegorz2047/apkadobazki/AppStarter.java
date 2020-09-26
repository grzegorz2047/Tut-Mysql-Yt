package pl.grzegorz2047.apkadobazki;

import java.sql.SQLException;
import java.util.Optional;

public class AppStarter {

    public static void main(String... args) {
        Database mysqlDatabase = new MysqlDatabase("localhost", 3306, "java", "java", "123456789");
        mysqlDatabase.connect();

        DatabaseQueries queries = new MysqlDatabaseQueries(mysqlDatabase);
        try {
            queries.insertPlayer("Jan521", "Jan", "Kowalski", 6969);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        showPlayerData(queries, "Jan521");
        showPlayerData(queries, "grzegorz2047");
        showPlayerData(queries, "alfredo");
        mysqlDatabase.disconnect();
    }
    private static void showPlayerData(DatabaseQueries queries, String userName) {
        Optional<Player> optionalPlayer = queries.getPlayer(userName);
        boolean present = optionalPlayer.isPresent();
        optionalPlayer.ifPresent(
                x -> System.out.println(x.getPlayerName() + ", punkty " + x.getPoints()));
        if (!present) {
            System.out.println("Nie znaleziono użytkownika o nazwie " + userName);
        }


    }
}
