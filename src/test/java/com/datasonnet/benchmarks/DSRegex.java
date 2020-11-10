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
import com.datasonnet.plugins.DefaultJSONFormatPlugin;
import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO
public class DSRegex {

    @Benchmark
    public Object fullRegex(FullRegex state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullRegex {
        Mapper mapper;
        Document<String> payload;

        @Param({"select_ARR", "select_OBJ"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(regexScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> regexScripts = Stream.of(new String[][]{
            {"select_OBJ", "ds.jsonpath.select({message: \"Hello World\"},\".message\")"},
            {"select_ARR", "ds.jsonpath.select([1,2,3,4,5],\".*\")"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
