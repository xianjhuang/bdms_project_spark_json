import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.*;

import java.util.Date;

public class ReadParquet {
    public static void main(String[] args) throws AnalysisException {
        //ignore useless warning
        Logger.getLogger("org").setLevel(Level.ERROR);

        //spark session
        SparkSession session = SparkSession.builder().master("local").appName("parquetreader").getOrCreate();

        //read file test
        Dataset<Row> tmp_data = session.read().option("multiline","false").json("D:\\IIE\\ucas\\SparkJsonTest\\src\\main\\java\\test.txt");
        tmp_data.write().parquet("D:\\IIE\\ucas\\SparkJsonTest\\src\\main\\java\\text.parquet");

        Double t1 = Double.parseDouble(String.valueOf(new Date().getTime()));
        Dataset<Row> data = session.read().option("multiline","false").parquet("D:\\IIE\\ucas\\SparkJsonTest\\src\\main\\java\\text.parquet");

        System.out.println("Data Schema is shown as follows: ");
        data.printSchema();
        data.createTempView("v_tmp");
        Double t2 = Double.parseDouble(String.valueOf(new Date().getTime()));
        System.out.println("It takes " + (t2-t1) + " ms to read data into memory.");
        System.out.println("*****************************************************************************************************************");

        //selection test
        System.out.println("This is Selection test. ");
        Double t3 = Double.parseDouble(String.valueOf(new Date().getTime()));
        Dataset<Row> selectionRes = session.sql("select * from v_tmp where gender='male' and age>18 and age>60 and score>60 ");
        System.out.println("Selection Result is shown as follows: ");
        selectionRes.show();
        Double t4 = Double.parseDouble(String.valueOf(new Date().getTime()));
        System.out.println("Selection operation takes "+ (t4-t3) + " ms. ");
        System.out.println("*****************************************************************************************************************");

        //projection test1
        System.out.println("This is Projection test1. (Nested JSON)");
        Double t5 = Double.parseDouble(String.valueOf(new Date().getTime()));
        Dataset<Row> projectionRes1 = session.sql("select id,motivation,vocation.job,vocation.salary,survey_location.city from v_tmp");
        System.out.println("Projection Result1 is shown as follows: ");
        projectionRes1.show();
        Double t6 = Double.parseDouble(String.valueOf(new Date().getTime()));
        System.out.println("Projection operation1 takes "+ (t6-t5) + " ms. ");
        System.out.println("*****************************************************************************************************************");

        //projection test2
        System.out.println("This is Projection test2. (JSON Array)");
        Double t7 = Double.parseDouble(String.valueOf(new Date().getTime()));
        Dataset<Row> projectionRes2 =  session.sql("select education from v_tmp where gender='female' and age<30 ").withColumn("education",functions.explode(functions.col("education"))).select("education.*");
        System.out.println("Projection Result2 is shown as follows: ");
        projectionRes2.show();
        Double t8 = Double.parseDouble(String.valueOf(new Date().getTime()));
        System.out.println("Projection operation2 takes "+ (t8-t7) + " ms. ");
        System.out.println("*****************************************************************************************************************");

        //aggregation test
        System.out.println("This is Aggregation test.");
        Double t9 = Double.parseDouble(String.valueOf(new Date().getTime()));
        Dataset<Row> aggRes = data.select("id","age", "gender", "score", "motivation").groupBy("gender").agg(functions.count("gender").as("Count_Gender"), functions.avg("score").as("AVG_SCORE"),functions.avg("age").as("AVG_Age"));
        System.out.println("Aggregation Result is shown as follows: ");
        aggRes.show();
        Double t10 = Double.parseDouble(String.valueOf(new Date().getTime()));
        System.out.println("Aggregation operation takes "+ (t10-t9) + " ms. ");
    }
}
