package com.hmtmcse.tmutil.mysql;

public class ConnectionCredential {

    public String hostname = "localhost";
    public String username = "root";
    public String password = "";
    public String databaseName;
    private String driverPackage = "com.mysql.cj.jdbc.Driver";

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String connectionUrl(){
        return "jdbc:mysql://" + hostname + "/" + databaseName;
    }

    public String getDriverPackage(){
        return this.driverPackage;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
