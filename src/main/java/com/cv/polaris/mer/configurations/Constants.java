package com.cv.polaris.mer.configurations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Constants {

    final public static String appName = "kafka-to-Hive-Batch-job";
    final public static String master = "local[*]";
    final public static String source = "kafka";
    final public static String topic = "logging_Topic";
    final public static String autoOffset = "latest";
    final public static String enableAutoOffset = "true";
    final public static String bootStrapServer = "localhost:9092";

    final public static int lastIndex = 12;
    final public static String destHiveTab = "logging_detail";
    final public static String defaultStrValue = "NA";
    final public static int defaultIntValue = -1;
    final public static String[] stringColList = {"job_execution_id","client_name","client_app_name","tenant","business_category"
            ,"business_subcategory","execution_mode","rule_set_detail","rule_detail","source_detail","target_detail"};
    final public static String[] numColList = {"status"};

    final public static String col1 = "id";
    final public static String col2 = "job_execution_id";
    final public static String col3 = "client_name";
    final public static String col4 = "client_app_name";
    final public static String col5 = "tenant";
    final public static String col6 = "business_category";
    final public static String col7 = "business_subcategory";
    final public static String col8 = "execution_mode";
    final public static String col9 = "status";
    final public static String col10 = "rule_set_detail";
    final public static String col11= "rule_detail";
    final public static String col12 = "source_detail";
    final public static String col13 = "target_detail";
    final public static String col14 = "time_created";
    final public static String col15 = "year";
    final public static String col16= "month";
    final public static String col17= "day";
    final public static String col18 = "hour";

}
