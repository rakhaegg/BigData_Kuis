package org.example;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.BufferedReader;
import java.io.IOException;
public class AggMapperViews extends MapReduceBase implements Mapper<LongWritable , Text , Text , Text>{

    String views = new String();
    String likes = new String();
    String dislikes = new String();
    String comments = new String();
    private Text textVideos = new Text();
    private Text valueVideios = new Text();


        @Override
        public void map(LongWritable longWritable, Text text, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {
            String line = text.toString();
            String delimeter_first = "(\"),(\")";
            String delimeter_second = "(\"),((\\d\\d|\\d),\\d\\d\\d\\d-)";
            String delimeter_third = "(\\W|\"),";





            String[] result_delimeter_first =line.split(delimeter_first);

            if (result_delimeter_first[0].contains("\n")|| result_delimeter_first.length == 1) {
                //System.out.println("SKIP");
            }else{
                String[] result_delimeter_second = result_delimeter_first[1].split(delimeter_second);
                this.textVideos.set(result_delimeter_second[0]);
                String[] result_delimeter_third = result_delimeter_second[1].split(delimeter_third);
                String[] value_map =result_delimeter_third[1].split(",");
                 views = value_map[0];
                 likes = value_map[1];
                 dislikes = value_map[2];
                comments = value_map[3];
                String arr = views + ',' +likes +',' + dislikes +',' +comments;
                this.valueVideios.set(arr);
                outputCollector.collect(this.textVideos , this.valueVideios );
            }


        }


    }



