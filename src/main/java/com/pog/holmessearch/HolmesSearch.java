package com.pog.holmessearch;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.*;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import com.pog.holmesdb.HolmesDb;
import com.datastax.spark.connector.japi.CassandraRow;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

public class HolmesSearch {
  public static void main(String[] args) {

    SparkConf conf = new SparkConf().setAppName("Holmes Search");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<SectionBean> cassandraRowsRDD = javaFunctions(sc).cassandraTable("holmesdb", "books", mapRowTo(SectionBean.class));

    String searchfor = "your Majesty";

    Integer count = cassandraRowsRDD.filter(
      new Function<SectionBean, Boolean>(){
        public Boolean call(SectionBean row){
          return row.getSectionText().contains(searchfor);
        }
      }
    ).map(new Function<SectionBean, String>(){
      public String call(SectionBean row){
        StringBuilder builder = new StringBuilder();
        builder.append("Text: ");
        builder.append(row.getSectionText());
        return builder.toString();
      }
    }).map(new Function<String, Integer>(){
      public Integer call(String row){
        return count(row, searchfor);
      }
    }).reduce(new Function2<Integer, Integer,Integer>(){
      public Integer call(Integer _1, Integer _2){
        return _1 + _2;
      }
    });
    System.out.println("COUNT: " + count);


    sc.stop();
  }

  protected static Integer count(String row, String searchfor){
    int count = 0;
    int index = row.indexOf(searchfor);
    while(index != -1){
      count++;
      row = row.substring(index+ searchfor.length());
      index = row.indexOf(searchfor);
    }
    return count;
  }
}
