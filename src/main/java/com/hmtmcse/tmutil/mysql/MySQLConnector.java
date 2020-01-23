package com.hmtmcse.tmutil.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {

    private ConnectionCredential credential;
    private Connection connection = null;
    private Statement statement = null;

    public MySQLConnector(String databaseName){
        credential = new ConnectionCredential();
        credential.databaseName = databaseName;
    }

    public MySQLConnector(String username, String password, String databaseName){
        credential = new ConnectionCredential();
        credential.databaseName = databaseName;
        credential.username = username;
        credential.password = password;
    }

    public MySQLConnector(String username, String password){
        credential = new ConnectionCredential();
        credential.username = username;
        credential.password = password;
    }

    public MySQLConnector (String host, String username, String password, String databaseName){
        credential = new ConnectionCredential();
        credential.databaseName = databaseName;
        credential.username = username;
        credential.hostname = host;
        credential.password = password;
    }

    public MySQLConnector setConnectionUrl(String connectionUrl) {
        credential.setConnectionString(connectionUrl);
        return this;
    }

    public MySQLConnector (ConnectionCredential credential){
        this.credential = credential;
    }



    private Statement open() throws JavaMySQLException {
        try {
            Class.forName(credential.getDriverPackage());
            connection = DriverManager.getConnection(credential.connectionUrl(), credential.username, credential.password);
            return statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
           throw new JavaMySQLException(e.getMessage());
        }
    }


    public Statement start() throws JavaMySQLException {
        try {
            if (connection == null){
                return open();
            }else if (connection.isClosed()){
                return open();
            }else{
                return statement;
            }
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }

    public void close() throws JavaMySQLException {
        stop();
    }

    public void stop() throws JavaMySQLException {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }
}
