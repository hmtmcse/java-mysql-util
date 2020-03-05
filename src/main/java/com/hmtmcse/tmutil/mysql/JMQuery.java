package com.hmtmcse.tmutil.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JMQuery extends MySQLConnector{


    public JMQuery(String databaseName) {
        super(databaseName);
    }

    public JMQuery(String username, String password) {
        super(username, password);
    }

    public JMQuery(String username, String password, String databaseName) {
        super(username, password, databaseName);
    }

    public JMQuery(String host, String username, String password, String databaseName) {
        super(host, username, password, databaseName);
    }

    public JMQuery(ConnectionCredential credential) {
        super(credential);
    }

    public ResultSet selectSQL(String sql) throws JavaMySQLException {
        try {
            if (sql == null || sql.equals("")) {
                throw new JavaMySQLException("Null or Empty SQL");
            }
            return start().executeQuery(sql);
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }

    public int createUpdateSQL(String sql) throws JavaMySQLException {
        try {
            if (sql == null || sql.equals("")) {
                throw new JavaMySQLException("Null or Empty SQL");
            }
            return start().executeUpdate(sql);
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }
}
