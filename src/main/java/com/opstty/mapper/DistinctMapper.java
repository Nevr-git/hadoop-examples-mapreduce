package com.opstty.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistinctMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text districtName = new Text();
    private static boolean isFirstLine = true;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        if (isFirstLine) {
            isFirstLine = false; // Skip the first line
            return;
        }

        String line = value.toString();
        String[] fields = line.split(";");
        
        // Assuming the tree name is in the first column, adjust index if necessary
        if (fields.length > 1) {
            districtName.set(fields[1].trim());
            context.write(districtName, new Text(""));
        }
    }
}