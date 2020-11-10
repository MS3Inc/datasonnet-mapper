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

public class DSMath {

    @Benchmark
    public Object allMath(AllMathState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class AllMathState {
        Mapper mapper;
        Document<String> payload;

        @Param({"abs","acos","asin","atan","avg","ceil",
                "clamp","cos","exp","exponent","floor",
                "log","mantissa","mod","pow","random",
                "randomInt","round","sin","sqrt","sum","tan"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(mathScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> mathScripts = Stream.of(new String[][]{
            {"abs", "ds.math.abs(-2)"},
            {"acos", "ds.math.acos(1)"},
            {"asin", "ds.math.asin(1)"},
            {"atan", "ds.math.atan(1)"},
            {"avg", "ds.math.avg([1,2,3,4,5,6,7,8,9,10])"},
            {"ceil", "ds.math.ceil(1.01)"},
            {"clamp", "ds.math.clamp(100, 0, 10)"},
            {"cos", "ds.math.cos(0)"},
            {"exp", "ds.math.exp(2)"},
            {"exponent", "ds.math.exponent(2)"},
            {"floor", "ds.math.floor(4.99)"},
            {"log", "ds.math.log(2)"},
            {"mantissa", "ds.math.mantissa(2)"},
            {"mod", "ds.math.mod(2,4)"},
            {"pow", "ds.math.pow(2,2)"},
            {"random", "ds.math.random"},
            {"randomInt", "ds.math.randomInt(500)"},
            {"round", "ds.math.round(2.5)"},
            {"sin", "ds.math.sin(1)"},
            {"sqrt", "ds.math.sqrt(4)"},
            {"sum", "ds.math.sum([1,2,3])"},
            {"tan", "ds.math.tan(1)"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
