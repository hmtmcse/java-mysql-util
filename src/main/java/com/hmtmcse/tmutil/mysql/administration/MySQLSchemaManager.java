package com.hmtmcse.tmutil.mysql.administration;

import com.hmtmcse.tmutil.mysql.ConnectionCredential;
import com.hmtmcse.tmutil.mysql.JMQuery;
import com.hmtmcse.tmutil.mysql.JavaMySQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLSchemaManager extends JMQuery {


    private final String SELECT_ALL_SCHEMA = "SELECT TABLE_NAME,ENGINE,TABLE_COLLATION FROM information_schema.TABLES WHERE TABLE_SCHEMA =";
    private final String SELECT_SCHEMA_CONSTRAINT = "SELECT TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_SCHEMA = '%s' AND TABLE_NAME = '%s' AND referenced_column_name IS NOT NULL";
    private final String SHOW_CREATE_TABLE = "SHOW CREATE TABLE ";
    private String databaseName;

    public MySQLSchemaManager(String databaseName) {
        super(databaseName);
        this.databaseName = databaseName;
    }

    public MySQLSchemaManager(String username, String password, String databaseName) {
        super(username, password, databaseName);
        this.databaseName = databaseName;
    }

    public MySQLSchemaManager(String host, String username, String password, String databaseName) {
        super(host, username, password, databaseName);
        this.databaseName = databaseName;
    }

    public MySQLSchemaManager(ConnectionCredential credential) {
        super(credential);
        this.databaseName = credential.databaseName;
    }


    public List<SchemaInfoData> getDatabaseSchemaList() throws JavaMySQLException {
        List<SchemaInfoData> schemaInfoDataList = new ArrayList<>();
        try {
            ResultSet resultSet =  selectSQL(SELECT_ALL_SCHEMA + "'" + this.databaseName + "'");
            while (resultSet.next()){
                schemaInfoDataList.add(
                        new SchemaInfoData()
                                .setTableName(resultSet.getString("TABLE_NAME"))
                                .setTableEngine(resultSet.getString("ENGINE"))
                                .setTableCollation(resultSet.getString("TABLE_COLLATION"))

                );
            }
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
        return schemaInfoDataList;
    }


    public List<SchemaConstraintData> getSchemaConstraintList(String tableName) throws JavaMySQLException {
        List<SchemaConstraintData> schemaConstraintDataList = new ArrayList<>();
        try {
            ResultSet resultSet = selectSQL(String.format(SELECT_SCHEMA_CONSTRAINT,databaseName,tableName));
            while (resultSet.next()){
                schemaConstraintDataList.add(
                        new SchemaConstraintData()
                                .setTableName(resultSet.getString("TABLE_NAME"))
                                .setColumnName(resultSet.getString("COLUMN_NAME"))
                                .setConstraintName(resultSet.getString("CONSTRAINT_NAME"))
                                .setReferencedColumnName(resultSet.getString("REFERENCED_COLUMN_NAME"))
                                .setReferencedTableName(resultSet.getString("REFERENCED_TABLE_NAME"))
                );
            }
        }  catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }catch (Exception e) {
            throw new JavaMySQLException(e.getMessage());
        }
        return schemaConstraintDataList;
    }

    public List<SchemaColumnData> getSchemaColumnList(ResultSet resultSet) throws JavaMySQLException {
        List<SchemaColumnData> schemaColumnDataList = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
            }
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }

        return schemaColumnDataList;
    }


    public SchemaCreateData getSchemaCreateSQL(String tableName) throws JavaMySQLException {
        try {
            String sql = SHOW_CREATE_TABLE + databaseName + "." + tableName + ";";
            ResultSet resultSet = selectSQL(sql );
            resultSet.next();
            String tableCreationString = resultSet.getString("Create Table");
            tableCreationString = tableCreationString.replace("CREATE TABLE", "CREATE TABLE IF NOT EXISTS");
            return new SchemaCreateData()
                    .setCreateTable(tableCreationString)
                    .setTable(resultSet.getString("Table"));
        } catch (SQLException e) {
            throw new JavaMySQLException(e.getMessage());
        }
    }
}
