package com.datasonnet.benchmarks;

/*-
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        int iterations = 5;
        TimeValue time = TimeValue.seconds(2);
        for(int i =0; i<args.length ; i ++) {
            if(args[i].equals("-f") || args[i].equals("--file")){
                i++;
                fileName = args[i];
            } else if (args[i].equals("-t") || args[i].equals("--test")){
                i++;
                regex.append(args[i]).append(".*");
            }
            else if (args[i].equals("-v") || args[i].equals("--verify")){
                iterations=1;
                time = TimeValue.milliseconds(500);
            }
        }
        createFile(fileName);
        Options opt = new OptionsBuilder()
                .include(regex.toString())
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupIterations(iterations)
                .warmupTime(time)
                .measurementIterations(iterations)
                .measurementTime(time)
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
