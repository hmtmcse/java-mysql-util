package com.hmtmcse.tmutil.mysql.administration;

public class SchemaCreateData {

    public String table;
    public String createTable;

    public String getTable() {
        return table;
    }

    public SchemaCreateData setTable(String table) {
        this.table = table;
        return this;
    }

    public String getCreateTable() {
        return createTable;
    }

    public SchemaCreateData setCreateTable(String createTable) {
        this.createTable = createTable;
        return this;
    }

}
