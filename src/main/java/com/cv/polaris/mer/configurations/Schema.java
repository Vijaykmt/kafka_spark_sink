package com.cv.polaris.mer.configurations;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class Schema {
   static StructType schema =  DataTypes.createStructType(new StructField[] {
            DataTypes.createStructField("id", DataTypes.LongType, true),
            DataTypes.createStructField("job_execution_id", DataTypes.StringType, true),
            DataTypes.createStructField("client_name", DataTypes.StringType, true),
            DataTypes.createStructField("client_app_name", DataTypes.StringType, true),
            DataTypes.createStructField("tenant", DataTypes.StringType, true),
            DataTypes.createStructField("business_category", DataTypes.StringType, true),
            DataTypes.createStructField("business_subcategory", DataTypes.StringType, true),
            DataTypes.createStructField("execution_mode", DataTypes.StringType, true),
            DataTypes.createStructField("status", DataTypes.IntegerType, true),
            DataTypes.createStructField("rule_set_detail", DataTypes.StringType, true),
            DataTypes.createStructField("rule_detail", DataTypes.StringType, true),
            DataTypes.createStructField("source_detail", DataTypes.StringType, true),
            DataTypes.createStructField("target_detail", DataTypes.StringType, true),
            DataTypes.createStructField("time_created", DataTypes.LongType, true),
            DataTypes.createStructField("year", DataTypes.IntegerType, true),
            DataTypes.createStructField("month", DataTypes.StringType, true),
            DataTypes.createStructField("day", DataTypes.IntegerType, true),
            DataTypes.createStructField("hour", DataTypes.IntegerType, true)
    });

    public static StructType getSchema() {
        return schema;
    }
}
