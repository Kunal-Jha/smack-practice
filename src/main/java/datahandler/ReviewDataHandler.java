package datahandler;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import datacategories.Review;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;

public class ReviewDataHandler {

    private static final String KEY_SPACE = "spark_db";
    private static final String TABLE_NAME = "review";

    public static void main(String[] args) {
        final String inputFile = args[0];
        SparkConf conf = new SparkConf(true)
                .setAppName("Review Reader")
                .set("spark.cassandra.connection.host", args[1]);
        saveDataToCassandra(conf, inputFile);
    }

    public static void saveDataToCassandra(SparkConf conf, String inputFile) {
        JavaSparkContext sc = new JavaSparkContext(conf);
        CassandraConnector connector = CassandraConnector.apply(sc.getConf());
        try (Session session = connector.openSession()) {
            session.execute("create keyspace if not exists " + KEY_SPACE + " WITH replication " +
                    "= {'class': 'SimpleStrategy', 'replication_factor': 1}");
            session.execute("create table if not exists " + KEY_SPACE + "." + TABLE_NAME +
                    " (review_id text primary key,user_id text,business_id " +
                    "text,stars decimal, useful int,funny int, cool int,text text, date timestamp);");
        }
        JavaRDD<Review> map = sc.textFile(inputFile)
                .map(text -> Arrays.asList(text.split("/n")))
                .map(ele -> String.join("", ele))
                .map(ele -> Review.parseJson(ele));

        javaFunctions(map).writerBuilder(KEY_SPACE, TABLE_NAME, mapToRow(Review.class)).saveToCassandra();
        sc.stop();

    }

}
