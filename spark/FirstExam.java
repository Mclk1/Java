package com.bigdatacompany.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class FirstExam {
    public static  void main(String[] arg){
        System.setProperty("hadoop.home.dir","C:\\hadoop-common-2.2.0-bin-master");
        JavaSparkContext javaSparkContext=new JavaSparkContext("local","First Exam Spark");
        JavaRDD<String> firstData = javaSparkContext.textFile("C:\\a.txt");
        System.out.println(firstData.first());
    }
}
