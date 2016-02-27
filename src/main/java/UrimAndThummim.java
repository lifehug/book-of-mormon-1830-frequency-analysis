package com.dave.church;

import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import java.util.Arrays;

final public class UrimAndThummim{

	public static void main(String args[]){

		SparkConf conf = new SparkConf().setAppName("Book of Mormon").setMaster("local");;
    	JavaSparkContext sc = new JavaSparkContext(conf);

    	// parse everything into an RDD
		JavaRDD<String[]> words = sc.textFile("documents/book_of_mormon_1830/*.txt").map( line -> line.split(" "));
		System.out.println(words.count());
		HashingTF tf = new HashingTF(10000000);
		Vector hashed = tf.transform(Arrays.asList(words));
		System.out.println(hashed.numActives());


	}

}