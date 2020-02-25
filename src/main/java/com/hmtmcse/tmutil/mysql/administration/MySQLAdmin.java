package com.hmtmcse.tmutil.mysql.administration;

import com.hmtmcse.tmutil.mysql.JavaMySQLException;
import com.hmtmcse.tmutil.mysql.MySQLConnector;
import com.hmtmcse.tmutil.mysql.administration.data.MySQLTableConstraintData;
import com.hmtmcse.tmutil.mysql.administration.data.MySQLTableData;
import com.hmtmcse.tmutil.mysql.administration.data.MySQLTableSchemaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    public Boolean dropUser(String username, String host) throws JavaMySQLException {
        String sql = "DROP USER '" + username + "'@'" + host + "';";
        execute(sql);
        return true;
    }

    public List<LinkedHashMap<String, String>> getAllUsers() throws JavaMySQLException {
        String sql = "SELECT User, Host FROM mysql.user;";
        List<LinkedHashMap<String, String>> list = new ArrayList<>();
        ResultSet resultSet = query(sql);
        LinkedHashMap<String, String> map;
        try {
            while (resultSet.next()) {
                map = new LinkedHashMap<>();
                map.put("username", resultSet.getString("User"));
                map.put("host", resultSet.getString("Host"));
                list.add(map);
            }
        } catch (Exception e) {
            throw new JavaMySQLException(e.getMessage());
        }
        return list;
    }

    public List<String> getAllDatabase() throws JavaMySQLException {
        String sql = "SHOW DATABASES;";
        List<String> list = new ArrayList<>();
        ResultSet resultSet = query(sql);
        try {
            while (resultSet.next()) {
                list.add(resultSet.getString("Database"));
            }
        } catch (Exception e) {
            throw new JavaMySQLException(e.getMessage());
        }
        return list;
    }

    public Boolean createDatabase(String databaseName) throws JavaMySQLException {
        return createDatabase(databaseName, "default character set utf8 default collate utf8_general_ci");
    }


    public Boolean userCreateAndGrantAccess(String username, String password, String host, String databaseName) throws JavaMySQLException {
        createUser(username, password, host);
        grantAccess(username, host, databaseName);
        return true;
    }

    public Boolean grantAllAccess(String username, String password, String host, String databaseName) throws JavaMySQLException {
        String sql = "GRANT ALL ON " + databaseName + ".* TO '" + username + "'@'" + host + "' IDENTIFIED BY '" + password + "';";
        execute(sql);
        execute("FLUSH PRIVILEGES;");
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

    public ResultSet query(String sql) throws JavaMySQLException {
        try {
            return mySQLConnector.start().executeQuery(sql);
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }

    public int execute(String sql) throws JavaMySQLException {
        try {
            return mySQLConnector.start().executeUpdate(sql);
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }

    public List<MySQLTableData> getAllTableName(String database) throws JavaMySQLException {
        String SHOW_TABLES = "SELECT TABLE_NAME,ENGINE,TABLE_COLLATION FROM information_schema.TABLES WHERE TABLE_SCHEMA =";
        String sql = SHOW_TABLES + "'" + database + "'";
        ResultSet resultSet = query(sql);
        List<MySQLTableData> tables = new ArrayList<>();
        MySQLTableData tableData;
        try {
            while (resultSet.next()) {
                tableData = new MySQLTableData();
                tableData.tableName = resultSet.getString("TABLE_NAME");
                tableData.tableEngine = resultSet.getString("ENGINE");
                tableData.tableCollation = resultSet.getString("TABLE_COLLATION");
                tables.add(tableData);
            }
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
        return tables;
    }

    public List<MySQLTableConstraintData> getTableConstraint(String database, String table) throws JavaMySQLException {
        String TABLE_CONSTRAINT = "SELECT TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_SCHEMA = '%s' AND TABLE_NAME = '%s' AND referenced_column_name IS NOT NULL";
        String sql = String.format(TABLE_CONSTRAINT, database, table);
        ResultSet resultSet = query(sql);
        List<MySQLTableConstraintData> constraints = new ArrayList<>();
        MySQLTableConstraintData tableConstraint;
        try {
            while (resultSet.next()) {
                tableConstraint = new MySQLTableConstraintData();
                tableConstraint.tableName = resultSet.getString("TABLE_NAME");
                tableConstraint.columnName = resultSet.getString("COLUMN_NAME");
                tableConstraint.constraintName = resultSet.getString("CONSTRAINT_NAME");
                tableConstraint.referencedTableName = resultSet.getString("REFERENCED_TABLE_NAME");
                tableConstraint.referencedColumnName = resultSet.getString("REFERENCED_COLUMN_NAME");
                constraints.add(tableConstraint);
            }
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
        return constraints;
    }


    public MySQLTableSchemaData tableCreateSchema(String database, String table) throws JavaMySQLException {
        String SHOW_CREATE_TABLE = "SHOW CREATE TABLE ";
        String sql = SHOW_CREATE_TABLE + database + "." + table + ";";
        ResultSet resultSet = query(sql);
        MySQLTableSchemaData tableSchemaData = new MySQLTableSchemaData();
        try {
            resultSet.next();
            tableSchemaData.tableSchema = resultSet.getString("Create Table");
            tableSchemaData.tableSchema = tableSchemaData.tableSchema.replace("CREATE TABLE", "CREATE TABLE IF NOT EXISTS");
            tableSchemaData.table = resultSet.getString("Table");
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
        return tableSchemaData;
    }

}
