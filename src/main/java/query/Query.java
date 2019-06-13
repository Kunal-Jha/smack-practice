package query;

import datacategories.Photo;
import datacategories.Tip;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapRowTo;

public class Query {
    private static final String KEY_SPACE = "spark_db";
    private static final String TABLE_NAME = "photo";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf(true)
                .setAppName("Photo Reader")
                .set("spark.cassandra.connection.host", args[0]);
        getDataFromCassandra(conf);
    }

    public static void getDataFromCassandra(SparkConf conf) {
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaPairRDD<String, Photo> photo = javaFunctions(sc)
                .cassandraTable(KEY_SPACE, "photo", mapRowTo(Photo.class))
                .keyBy((Function<Photo, String>) sale -> sale.getBusinessId());

        JavaPairRDD<String, Tip> tip = javaFunctions(sc)
                .cassandraTable(KEY_SPACE, "tip", mapRowTo(Tip.class))
                .keyBy((Function<Tip, String>) sale -> sale.getBusinessId());

        JavaRDD<Tuple2<Photo, Tip>> values = photo.join(tip).values();
        for (Tuple2<Photo, Tip> result : values.take(100)) {
            System.out.println(result);
        }
        sc.stop();

    }
}
