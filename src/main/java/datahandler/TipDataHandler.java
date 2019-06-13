package datahandler;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import datacategories.Tip;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapToRow;

public class TipDataHandler {
    private static final String KEY_SPACE = "spark_db";
    private static final String TABLE_NAME = "tip";
    public static void main(String[] args) {

        final String inputFile = args[0];
        SparkConf conf = new SparkConf(true)
                .setAppName("Tip Reader")
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
                    "(user_id text, business_id text, text text,date timestamp, compliment_count int, " +
                    "primary key (user_id, business_id, date));");
        }
        JavaRDD<Tip> map = sc.textFile(inputFile)
                .map(text -> Arrays.asList(text.split("/n")))
                .map(ele -> String.join("", ele))
                .map(ele -> Tip.parseJson(ele));

        javaFunctions(map).writerBuilder(KEY_SPACE, TABLE_NAME,
                mapToRow(Tip.class)).saveToCassandra();
        sc.stop();
    }
}
