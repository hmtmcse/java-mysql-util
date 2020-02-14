package com.hmtmcse.tmutil.mysql.administration.data;

public class MySQLTableData {
    
    public String tableName;
    public String tableEngine;
    public String tableCollation;

    
    public String getTableName() {
        return tableName;
    }

    public MySQLTableData setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getTableEngine() {
        return tableEngine;
    }

    public MySQLTableData setTableEngine(String tableEngine) {
        this.tableEngine = tableEngine;
        return this;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public MySQLTableData setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
        return this;
    }
}
