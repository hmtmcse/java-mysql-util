package com.hmtmcse.tmutil.mysql;

public class ConnectionCredential {

    public String hostname = "localhost";
    public String username = "root";
    public String password = "";
    public String databaseName;
    public String connectionString;
    private String driverPackage = "com.mysql.cj.jdbc.Driver";

    public String getHostname() {
        return hostname;
    }

    public ConnectionCredential setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ConnectionCredential setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ConnectionCredential setPassword(String password) {
        this.password = password;
        return this;
    }

    public String connectionUrl() {
        if (connectionString != null) {
            return connectionString;
        }
        return "jdbc:mysql://" + hostname + "/" + databaseName;
    }

    public String getDriverPackage(){
        return this.driverPackage;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public ConnectionCredential setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public ConnectionCredential setConnectionString(String connectionString) {
        this.connectionString = connectionString;
        return this;
    }
}
