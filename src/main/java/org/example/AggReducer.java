package org.example;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;
public class AggReducer extends MapReduceBase implements Reducer<Text , Text , Text  , Text> {
    @Override
    public void reduce(Text text, Iterator<Text> iterator, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {



        int views = 0;
        int likes = 0;
        int dislikes = 0;
        int comments = 0;
            while (iterator.hasNext()) {
                Text currentRevenue = iterator.next();

                String[] split_data_value = currentRevenue.toString().trim().split(",");

                views = views +Integer.parseInt(split_data_value[0]);
                likes = likes + Integer.parseInt(split_data_value[1]);
                dislikes = dislikes + Integer.parseInt(split_data_value[2]);
                comments = comments + Integer.parseInt(split_data_value[3]);

            }
        String output =  String.valueOf(views) + "," + String.valueOf(likes) + "," + String.valueOf(dislikes) + ","
                + String.valueOf(comments);
        outputCollector.collect(text , new Text(output));
        }


    }

