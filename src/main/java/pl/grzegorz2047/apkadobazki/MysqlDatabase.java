package pl.grzegorz2047.apkadobazki;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDatabase implements Database {

    private String host, user, password, db;
    private int port;
    private HikariDataSource hikari;
    //Uzywaj hikari

    private MysqlDatabase() {
    }

    public MysqlDatabase(String host, int port, String db, String user, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("org.mariadb.jdbc.MariaDbDataSource");
        hikari.addDataSourceProperty("serverName", host);
        hikari.addDataSourceProperty("port", port);
        hikari.addDataSourceProperty("databaseName", db);
        hikari.addDataSourceProperty("user", user);
        hikari.addDataSourceProperty("password", password);
    }

    @Override
    public void disconnect() {
        hikari.close();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return hikari.getConnection();
    }
}
