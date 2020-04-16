package com.cv.polaris.mer.runner;

import com.cv.polaris.mer.model.LoggingDetail;
import com.cv.polaris.mer.utils.Utility;
import com.cv.polaris.mer.configurations.Constants;
import com.cv.polaris.mer.configurations.Schema;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.MapFunction;
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
                .format(Constants.source)
                .option("kafka.bootstrap.servers", Constants.bootStrapServer)
                .option("subscribe", Constants.topic)
                .option("auto.offset.reset", Constants.autoOffset)
                .option("enable.auto.commit", Constants.enableAutoOffset)
                .load();

        Dataset<Row> ds_tmp= ds.selectExpr("CAST(value AS STRING) as message");
        ds_tmp.foreach((ForeachFunction<Row>) row -> Utility.validateJson(failedCount, row.toString()));
        Dataset<Row> raw_ds = ds.selectExpr("CAST(value AS STRING) as message")
                .select(functions.from_json(functions.col("message"), Schema.getSchema()).as("json"))
                .select("json.*");

        raw_ds.printSchema();
        long totalRecCount = raw_ds.count();
        raw_ds.show();

        Dataset<LoggingDetail> dsPojo = raw_ds.as(Encoders.bean(LoggingDetail.class));
        Encoder<LoggingDetail> loggingDetailEncoder = Encoders.bean(LoggingDetail.class);

        Dataset<LoggingDetail> final_ds = dsPojo.map((MapFunction<LoggingDetail, LoggingDetail>) value -> Utility.getFinalDs(value), loggingDetailEncoder);

        Dataset<LoggingDetail> order_ds = Utility.getOrderedFinalDs(final_ds);

        order_ds.show();

        Utility.writeDataToHive(order_ds);

        System.out.println("the number of records consumed from Kafka : " + totalRecCount);
        System.out.println("the number of records stored in final ordered ds: " + order_ds.count());

        succeedCount.setValue(totalRecCount - failedCount.value());

        System.out.println("Success record count : " + succeedCount);
        System.out.println("Failed record count : " + failedCount);

    }
}
