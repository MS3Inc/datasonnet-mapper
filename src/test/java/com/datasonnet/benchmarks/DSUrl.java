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

public class DSUrl {

    @Benchmark
    public Object fullUrl(FullUrl state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullUrl {
        Mapper mapper;
        Document<String> payload;

        @Param({"decode_STR", "encode_STR"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(urlScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> urlScripts = Stream.of(new String[][]{
            {"decode_STR", "ds.url.decode(\"Hello+World\", \"UTF-8\")"},
            {"encode_STR", "ds.url.encode(\"Hello World\", \"UTF-8\")"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
