import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class Schema {
   static StructType schema =  DataTypes.createStructType(new StructField[] {
            DataTypes.createStructField("exception_key", DataTypes.StringType, true),
            DataTypes.createStructField("job_id", DataTypes.StringType, true),
            DataTypes.createStructField("job_sequence", DataTypes.StringType, true),
            DataTypes.createStructField("job_scheduler", DataTypes.StringType, true),
            DataTypes.createStructField("exception_id", DataTypes.StringType, true),
            DataTypes.createStructField("tenant", DataTypes.StringType, true),
            DataTypes.createStructField("business_category", DataTypes.StringType, true),
            DataTypes.createStructField("business_subcategory", DataTypes.StringType, true),
            DataTypes.createStructField("client_name", DataTypes.StringType, true),
            DataTypes.createStructField("origin", DataTypes.StringType, true),
            DataTypes.createStructField("component", DataTypes.StringType, true),
            DataTypes.createStructField("sub_component", DataTypes.StringType, true),
            DataTypes.createStructField("exception_type", DataTypes.StringType, true),
            DataTypes.createStructField("exception_subtype", DataTypes.StringType, true),
            DataTypes.createStructField("status", DataTypes.StringType, true),
            DataTypes.createStructField("severity", DataTypes.StringType, true),
            DataTypes.createStructField("owned_by", DataTypes.StringType, true),
            DataTypes.createStructField("exception_message", DataTypes.StringType, true),
            DataTypes.createStructField("exception_message_detail", DataTypes.StringType, true),
            DataTypes.createStructField("exception_data", DataTypes.StringType, true),
            DataTypes.createStructField("retry_count", DataTypes.StringType, true),
            DataTypes.createStructField("exception_created_time", DataTypes.StringType, true),
            DataTypes.createStructField("remediation_status", DataTypes.StringType, true),
            DataTypes.createStructField("resolved_by", DataTypes.StringType, true),
            DataTypes.createStructField("resolved_time", DataTypes.StringType, true),
            DataTypes.createStructField("created_by", DataTypes.StringType, true),
            DataTypes.createStructField("created_time", DataTypes.StringType, true),
            DataTypes.createStructField("updated_by", DataTypes.StringType, true),
            DataTypes.createStructField("updated_time", DataTypes.StringType, true)
    });

    public static StructType getSchema() {
        return schema;
    }
}
