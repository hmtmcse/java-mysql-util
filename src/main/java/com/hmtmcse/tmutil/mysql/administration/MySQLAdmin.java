package com.hmtmcse.tmutil.mysql.administration;

import com.hmtmcse.tmutil.mysql.JavaMySQLException;
import com.hmtmcse.tmutil.mysql.MySQLConnector;

import java.sql.SQLException;

public class MySQLAdmin {

    private MySQLConnector mySQLConnector;

    public MySQLAdmin(String host, String username, String password) {
        this.mySQLConnector = new MySQLConnector(username, password);
        this.mySQLConnector.getCredential().setHostname(host);
    }

    public Boolean createDatabase(String databaseName, String extension) throws JavaMySQLException {
        String sql = "CREATE DATABASE " + databaseName + " " + extension + ";";
        execute(sql);
        return true;
    }

    public Boolean dropDatabase(String databaseName) throws JavaMySQLException {
        String sql = "DROP DATABASE IF EXISTS " + databaseName + ";";
        execute(sql);
        return true;
    }

    public Boolean createDatabase(String databaseName) throws JavaMySQLException {
        return createDatabase(databaseName, "default character set utf8 default collate utf8_general_ci");
    }


    public Boolean userCreateAndGrantAccess(String username, String password, String host, String databaseName) throws JavaMySQLException {
        createUser(username, password, host);
        grantAccess(username, host, databaseName);
        return true;
    }


    public Boolean grantAccess(String username, String host, String databaseName) throws JavaMySQLException {
        String sql = "GRANT ALL PRIVILEGES ON " + databaseName + ".* TO '" + username + "'@'" + host + "';";
        execute(sql);
        return true;
    }


    public Boolean createUser(String username, String password, String host) throws JavaMySQLException {
        String sql = "CREATE USER '" + username + "'@'" + host + "' IDENTIFIED BY '" + password + "';";
        execute(sql);
        return true;
    }

    public int execute(String sql) throws JavaMySQLException {
        try {
            return mySQLConnector.start().executeUpdate(sql);
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }


}
