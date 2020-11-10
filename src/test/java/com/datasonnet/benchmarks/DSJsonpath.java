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

public class DSJsonpath {

    @Benchmark
    public Object fullJsonpath(FullJsonpath state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullJsonpath {
        Mapper mapper;
        Document<String> payload;

        @Param({"select_ARR", "select_OBJ"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(jsonpathScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> jsonpathScripts = Stream.of(new String[][]{
            {"select_OBJ", "ds.jsonpath.select({message: \"Hello World\"},\".message\")"},
            {"select_ARR", "ds.jsonpath.select([1,2,3,4,5],\".*\")"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
