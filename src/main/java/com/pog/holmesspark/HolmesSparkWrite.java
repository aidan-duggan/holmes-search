package com.pog.holmesspark;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import java.util.List;
import java.util.ArrayList;
import com.pog.holmesdb.HolmesDb;
import com.pog.holmesapp.HolmesTextPersistance;
import com.pog.holmesdb.ApplicationMain;
import scala.Tuple2;

public class HolmesSparkWrite {
  public static void main(String[] args) {
    ApplicationMain.main(new String[]{"true", "true"});

    SparkConf conf = new SparkConf().setAppName("Holmes Write");
    JavaSparkContext sc = new JavaSparkContext(conf);
    List<Tuple2<String, String>> novels = new ArrayList<>();
    novels.add(new Tuple2<>("A SCANDAL IN BOHEMIA","https://sherlock-holm.es/stories/plain-text/scan.txt"));
    JavaRDD<Tuple2<String, String>> novelsToSave = sc.parallelize(novels);

    HolmesTextPersistance persist = new HolmesTextPersistance();
    novelsToSave.foreach(t -> persist.writeTextToDb("127.0.0.1", t._2(), t._1()));


    sc.stop();
  }
}
