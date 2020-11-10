package com.datasonnet.benchmarks;

import com.datasonnet.Mapper;
import com.datasonnet.MapperBuilder;
import com.datasonnet.document.DefaultDocument;
import com.datasonnet.document.Document;
import com.datasonnet.document.MediaTypes;
import com.datasonnet.plugins.DefaultJSONFormatPlugin;
import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DSNumbers {

    @Benchmark
    public Object fullNumbers(FullNumbersState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullNumbersState {
        Mapper mapper;
        Document<String> payload;

        @Param({"fromBinary","fromHex","fromRadixNumber","toBinary","toHex","toRadixNumber"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(numbersScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> numbersScripts = Stream.of(new String[][]{
            {"fromBinary", "ds.numbers.toBinary(1100100)"},
            {"fromHex", "ds.numbers.fromHex(64)"},
            {"fromRadixNumber", "ds.numbers.fromRadixNumber(1101000,2)"},
            {"toBinary", "ds.numbers.toBinary(100)"},
            {"toHex", "ds.numbers.toHex(100)"},
            {"toRadixNumber", "ds.numbers.toRadixNumber(104, 2)"},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
