package query;

import datacategories.Business;
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
                .keyBy((Function<Photo, String>) photo1 -> photo1.getBusinessId());

        JavaPairRDD<String, Tip> tip = javaFunctions(sc)
                .cassandraTable(KEY_SPACE, "tip", mapRowTo(Tip.class))
                .keyBy((Function<Tip, String>) tip1 -> tip1.getBusinessId());

        JavaPairRDD<String, Business> business = javaFunctions(sc)
                .cassandraTable(KEY_SPACE, "business", mapRowTo(Business.class))
                .keyBy((Function<Business, String>) business1 -> business1.getBusinessId());

        JavaRDD<Tuple2<Tuple2<Photo, Tip>, Business>> values = photo.join(tip).join(business).values();
        for (Tuple2<Tuple2<Photo, Tip>, Business>  result : values.take(100)) {
            System.out.println(result);
        }
        sc.stop();

    }
}
