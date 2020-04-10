
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.*;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.LongAccumulator;

public class ETL {

    public static void main(String args[]) throws InterruptedException {

        SparkSession spark = Utility.getInstance();

        LongAccumulator succeedCount = Utility.getAccumulator(spark);
        LongAccumulator failedCount = Utility.getAccumulator(spark);

        Dataset<Row> ds = spark
                .read()
                .format(Configurations.getSource())
                .option("kafka.bootstrap.servers", Configurations.getBootStrapServer())
                .option("subscribe", Configurations.getTopic())
                .option("auto.offset.reset", Configurations.getAutoOffset())
                .option("enable.auto.commit", Configurations.getEnableAutoOffset())
                .load();

        Dataset<Row> ds_tmp= ds.selectExpr("CAST(value AS STRING) as message");
        ds_tmp.foreach((ForeachFunction<Row>) row -> Utility.validate(succeedCount, failedCount, row.toString()));
        Dataset<Row> ds1 = ds.selectExpr("CAST(value AS STRING) as message")
                .select(functions.from_json(functions.col("message"),Schema.getSchema()).as("json"))
                .select("json.*");

        ds1.printSchema();
        ds1.show();
        System.out.println("the number of records stored : " + ds1.count());
        System.out.println("Success record count : " + succeedCount);
        System.out.println("Failed record count : " + failedCount);

    }
}
