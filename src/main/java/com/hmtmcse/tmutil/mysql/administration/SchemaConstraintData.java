package com.hmtmcse.tmutil.mysql.administration;

public class SchemaConstraintData {

    public String tableName;
    public String columnName;
    public String constraintName;
    public String referencedTableName;
    public String referencedColumnName;


    public String getTableName() {
        return tableName;
    }

    public SchemaConstraintData setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public SchemaConstraintData setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public SchemaConstraintData setConstraintName(String constraintName) {
        this.constraintName = constraintName;
        return this;
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public SchemaConstraintData setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
        return this;
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public SchemaConstraintData setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
        return this;
    }
}
