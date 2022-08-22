package com.bigdatacompany.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSqlFirst {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");

        SparkSession sparkSession = SparkSession.builder().master("local").appName("First_Exam").getOrCreate();
        Dataset<Row> rawDS = sparkSession.read().csv("C:\\Users\\pc\\Desktop\\whitehouse.csv");
        rawDS.show();

    }
}
