import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.LongAccumulator;

import java.io.Serializable;

public class Utility implements Serializable {

    static SparkSession getInstance() {

        SparkSession spark = SparkSession
                .builder()
                .appName(Configurations.getAppName())
                .master(Configurations.getMaster())
                .getOrCreate();
        return spark;

    }

    static LongAccumulator getAccumulator(SparkSession spark) {

        LongAccumulator accumulator = spark.sparkContext().longAccumulator();
        return accumulator;

    }

    static void validate(LongAccumulator succeedCount, LongAccumulator failedCount, String jsonString) {

        try {
            System.out.println("JSON received is : " + jsonString);
            JsonParser parser = new JsonParser();
            parser.parse(jsonString);
            System.out.println("***************************************************************************");
            System.out.println("Parsed JSON : " + jsonString);
            succeedCount.add(1);
        }
        catch(JsonSyntaxException jse) {
            failedCount.add(1);
            System.out.println("Not a valid Json String:"+jse.getMessage());
        }

    }

}
