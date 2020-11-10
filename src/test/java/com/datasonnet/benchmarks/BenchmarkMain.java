package com.datasonnet.benchmarks;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BenchmarkMain {



    public static void main(String[] args) throws Exception {
        //System.out.println(DSObjects.objectsScripts.keySet().stream().sorted().map(it -> "\"" + it + "\"").collect(Collectors.joining(",")));
        String fileName = "target/jmh-reports/jmh-benchmark-report.csv";
        StringBuilder regex = new StringBuilder(".*DS.*");
        for(int i =0; i<args.length ; i ++) {
            if(args[i].equals("-f") || args[i].equals("--file")){
                i++;
                fileName = args[i];
            } else if (args[i].equals("-t") || args[i].equals("--test")){
                i++;
                regex.append(args[i]).append(".*");
            }
        }
        createFile(fileName);
        Options opt = new OptionsBuilder()
                .include(regex.toString())
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(2))
                .threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.CSV)
                .result(fileName)
                .build();

        Collection<RunResult> curr = new Runner(opt).run();
        assertTrue(true);
    }

    private static void createFile(String str) throws IOException {
        File file = new File(str);
        file.getParentFile().mkdirs();
        file.createNewFile(); // create file if not exist
    }
}
