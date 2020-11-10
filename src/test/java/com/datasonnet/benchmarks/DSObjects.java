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

public class DSObjects {

    @Benchmark
    public Object allObjects(AllObjectsState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class AllObjectsState {
        Mapper mapper;
        Document<String> payload;

        @Param({"divideBy_OBJ","everyEntry_OBJ","everyEntry_OBJ_2",
                "mergeWith_OBJ","someEntry_OBJ", "takeWhile_OBJ_2"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(objectsScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> objectsScripts = Stream.of(new String[][]{
            {"divideBy_OBJ", "ds.objects.divideBy({a:1,b:2,c:3},2)"},
            {"everyEntry_OBJ", "ds.objects.everyEntry({a:1,b:2,c:1},function(value,key) value < 2)"},
            {"everyEntry_OBJ_2", "ds.objects.everyEntry({a:1,b:2,c:1},function(value) value < 2)"},
            {"mergeWith_OBJ", "ds.objects.mergeWith({a:1},{b:2})"},
            {"someEntry_OBJ", "ds.objects.someEntry({a:1,b:2,c:1},function(value,key) value < 2)"},
            {"takeWhile_OBJ_2", "ds.objects.takeWhile({a:1,b:2,c:1},function(value,key) value < 2)"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
