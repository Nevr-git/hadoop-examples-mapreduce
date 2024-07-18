package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class KindMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text districtName = new Text();
    private static boolean isFirstLine = true;
    private final static IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        if (isFirstLine) {
            isFirstLine = false; // Skip the first line
            return;
        }

        String line = value.toString();
        String[] fields = line.split(";");
        
        // Assuming the tree name is in the first column, adjust index if necessary
        if (fields.length > 3) {
            districtName.set(fields[3].trim());
            context.write(districtName, one);
        }
    }
}