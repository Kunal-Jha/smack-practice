package datahandler;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import datacategories.User;
import datacategories.UserJson;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;

public class UserDataHandler {
    private static final String KEY_SPACE = "spark_db";
    private static final String TABLE_NAME = "user";
    public static void main(String[] args) {
        final String inputFile = args[0];
        SparkConf conf = new SparkConf(true)
                .setAppName("UserJson Reader")
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
                    "(user_id text primary key,name text,review_count int, yelping_since text,useful int, " +
                    "funny int, cool int, elite text, friends list<text>,fans int,average_stars decimal,compliment_hot int," +
                    "compliment_more int, compliment_profile int, compliment_cute int, compliment_list int, " +
                    "compliment_note int, compliment_plain int, compliment_cool int, compliment_funny int, " +
                    "compliment_writer int, compliment_photos int);");
        }
        JavaRDD<User> map = sc.textFile(inputFile)
                .map(text -> Arrays.asList(text.split("/n")))
                .map(ele -> String.join("", ele))
                .map(ele -> UserJson.parseJson(ele));

        javaFunctions(map).writerBuilder(KEY_SPACE, TABLE_NAME, mapToRow(User.class)).saveToCassandra();
        sc.stop();
    }
}
