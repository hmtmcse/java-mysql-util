package com.hmtmcse.tmutil.mysql.administration.data;

public class MySQLTableConstraintData {

    public String tableName;
    public String columnName;
    public String constraintName;
    public String referencedTableName;
    public String referencedColumnName;


    public String getTableName() {
        return tableName;
    }

    public MySQLTableConstraintData setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public MySQLTableConstraintData setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public MySQLTableConstraintData setConstraintName(String constraintName) {
        this.constraintName = constraintName;
        return this;
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public MySQLTableConstraintData setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
        return this;
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public MySQLTableConstraintData setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
        return this;
    }
}
