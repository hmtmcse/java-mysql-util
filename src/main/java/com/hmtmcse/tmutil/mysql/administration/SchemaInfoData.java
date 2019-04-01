package com.hmtmcse.tmutil.mysql.administration;

public class SchemaInfoData {

    public String tableName;
    public String tableEngine;
    public String tableCollation;
    public SchemaConstraintData schemaConstraintData = null;

    public String getTableName() {
        return tableName;
    }

    public SchemaInfoData setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getTableEngine() {
        return tableEngine;
    }

    public SchemaInfoData setTableEngine(String tableEngine) {
        this.tableEngine = tableEngine;
        return this;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public SchemaInfoData setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
        return this;
    }

    public SchemaConstraintData getSchemaConstraintData() {
        return schemaConstraintData;
    }

    public SchemaInfoData setSchemaConstraintData(SchemaConstraintData schemaConstraintData) {
        this.schemaConstraintData = schemaConstraintData;
        return this;
    }
}
