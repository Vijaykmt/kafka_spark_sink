package com.cv.polaris.mer.utils;

import com.cv.polaris.mer.configurations.Constants;
import com.cv.polaris.mer.model.LoggingDetail;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.spark.sql.*;
import org.apache.spark.util.LongAccumulator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import java.io.Serializable;
import java.util.List;

import static org.apache.spark.sql.functions.lit;
//import static org.apache.spark.sql.functions.monotonically_increasing_id;

public class Utility implements Serializable {

    AtomicLong value = new AtomicLong(1);

    public long getNext() {
        return value.getAndIncrement();
    }

    public static SparkSession getInstance() {

        SparkSession spark = SparkSession
                .builder()
                .appName(Constants.appName)
                .master(Constants.master)
              //  .enableHiveSupport()
                .getOrCreate();
        return spark;

    }

   public static LongAccumulator getAccumulator(SparkSession spark) {

        LongAccumulator accumulator = spark.sparkContext().longAccumulator();
        return accumulator;

    }

    public static void validateJson(LongAccumulator failedCount, String jsonString) {

        try {
            System.out.println("JSON received is : " + jsonString);
            JsonParser parser = new JsonParser();
            parser.parse(jsonString);
            System.out.println("***************************************************************************");
            System.out.println("Parsed JSON : " + jsonString);
        }
        catch(JsonSyntaxException jse) {
            failedCount.add(1);
            System.out.println("Not a valid Json String:" + jse.getMessage());
        }
    }

    public static Boolean validateRecord(LoggingDetail row) {
        Boolean valid = true;
        //int index;
        String[] strColValues = {row.getJob_execution_id(), row.getBusiness_category(), row.getBusiness_subcategory()
                , row.getClient_app_name(), row.getClient_name(), row.getExecution_mode(), row.getSource_detail()
                , row.getTarget_detail(), row.getTenant(), row.getExecution_mode(),  row.getRule_set_detail()
                , row.getRule_detail()};

        if(strColValues.equals(null) && row.getStatus().equals(null) && row.getTime_created().equals(null))
            valid = false;

        System.out.println(valid);

        return valid;
    }

    public static LoggingDetail getFinalDs(LoggingDetail ds) {

        Long curTime = System.currentTimeMillis();

        Long id = ((curTime/(1000*60)) << 32) + SequenceGenerator.getNext();
        ds.setId(id);

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        ds.setYear(now.getYear());
        ds.setMonth(now.getMonth().toString());
        ds.setDay(now.getDayOfMonth());
        ds.setHour(now.getHour());

        return ds;

    }

    public static Dataset<LoggingDetail> getOrderedFinalDs(Dataset<LoggingDetail> ds) {

        Dataset<Row> temp_ds = ds.select(Constants.col1, Constants.col2, Constants.col3, Constants.col4, Constants.col5
                , Constants.col6, Constants.col7, Constants.col8, Constants.col9, Constants.col10, Constants.col11, Constants.col12
                , Constants.col13, Constants.col14, Constants.col15, Constants.col16, Constants.col17, Constants.col18);

        Dataset<LoggingDetail> order_ds = temp_ds.as(Encoders.bean(LoggingDetail.class));
        return order_ds;
    }

    public static void writeDataToHive(Dataset<LoggingDetail> ds) {
        ds.write().mode(SaveMode.Append).insertInto(Constants.destHiveTab);
    }

}
