package com.opstty;

import com.opstty.job.WordCount;
import com.opstty.job.Distinct;
import com.opstty.job.Species;
import org.apache.hadoop.util.ProgramDriver;

public class AppDriver {
    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        try {
            programDriver.addClass("wordcount", WordCount.class,
                    "A map/reduce program that counts the words in the input files.");
            programDriver.addClass("distinct", Distinct.class,
                    "A map/reduce program that outputs the distinct tree names in the input files.");
            programDriver.addClass("species", Species.class,
                    "A map/reduce program that outputs the distinct species in the input files.");

            exitCode = programDriver.run(argv);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.exit(exitCode);
    }
}
