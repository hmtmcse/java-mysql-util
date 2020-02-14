package com.hmtmcse.tmutil.mysql.administration.data;

public class MySQLTableSchemaData {

    public String table;
    public String tableSchema;

    public String getTable() {
        return table;
    }

    public MySQLTableSchemaData setTable(String table) {
        this.table = table;
        return this;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public MySQLTableSchemaData setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
        return this;
    }
}
