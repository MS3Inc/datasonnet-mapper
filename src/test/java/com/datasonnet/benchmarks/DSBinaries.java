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

import com.datasonnet.Mapper;
import com.datasonnet.MapperBuilder;
import com.datasonnet.document.DefaultDocument;
import com.datasonnet.document.Document;
import com.datasonnet.document.MediaTypes;
import com.datasonnet.plugins.DefaultJsonFormatPlugin;
import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DSBinaries {

    @Benchmark
    public Object fullBinaries(FullBinariesState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullBinariesState {
        Mapper mapper;
        Document<String> payload;

        @Param({"fromBase64","fromHex","readLinesWith","toBase64","toHex","writeLinesWith"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(binariesScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJsonFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> binariesScripts = Stream.of(new String[][]{
            {"fromBase64", "ds.binaries.fromBase64(\"SGVsbG8gV29ybGQ=\")"},
            {"fromHex", "ds.binaries.fromHex(\"48656C6C6F20576F726C64\")"},
            {"readLinesWith", "ds.binaries.readLinesWith(\"Hello World\",\"UTF-8\")"},
            {"toBase64", "ds.binaries.toBase64(\"Hello World\")"},
            {"toHex", "ds.binaries.toHex(\"Hello World\")"},
            {"writeLinesWith", "ds.binaries.writeLinesWith([\"Hello World\"],\"UTF-8\")"},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}