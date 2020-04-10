
public class Configurations {

    final static String appName = "kafka-to-Hive-Batch-job";
    final static String master = "local[*]";
    final static String source = "kafka";
    final static String topic = "excep_Topic";
    final static String autoOffset = "latest";
    final static String enableAutoOffset = "true";
    final static String bootStrapServer = "localhost:9092";


    static String getSource() {
        return source;
    }

    static String getTopic() {
        return topic;
    }

    static String getAutoOffset() {
        return autoOffset;
    }

    static String getBootStrapServer() {
        return bootStrapServer;
    }

    static String getEnableAutoOffset() {
        return  enableAutoOffset;
    }

    static String getAppName() {
        return appName;
    }

    static String getMaster() {
        return master;
    }

}
