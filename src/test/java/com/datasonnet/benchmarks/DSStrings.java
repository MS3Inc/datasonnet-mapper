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

public class DSStrings {

    @Benchmark
    public Object allStrings(AllStringsState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class AllStringsState {
        Mapper mapper;
        Document<String> payload;

        @Param({"appendIfMissing", "camelize", "capitalize", "charCode",
                "charCodeAt", "dasherize", "fromCharCode", "isAlpha",
                "isAlphanumeric", "isLowerCase", "isNumeric", "isUpperCase",
                "isWhitespace", "leftPad", "ordinalize", "pluralize",
                "prependIfMissing", "repeat", "rightPad", "singularize",
                "substringAfter", "substringAfterLast", "substringBefore",
                "substringBeforeLast", "underscore", "unwrap", "withMaxSize",
                "wrapIfMissing", "wrapWith"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(stringsScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJsonFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> stringsScripts = Stream.of(new String[][]{
            {"appendIfMissing", "ds.strings.appendIfMissing(\"World Hello\",\"Hello\")"},
            {"camelize", "ds.strings.camelize(\"Hello_world\")"},
            {"capitalize", "ds.strings.capitalize(\"hello world\")"},
            {"charCode", "ds.strings.charCode(\"*\")"},
            {"charCodeAt", "ds.strings.charCodeAt(\"*\",0)"},
            {"dasherize", "ds.strings.dasherize(\"Hello WorldX\")"},
            {"fromCharCode", "ds.strings.fromCharCode(42)"},
            {"isAlpha", "ds.strings.isAlpha(\"dfgdgd\")"},
            {"isAlphanumeric", "ds.strings.isAlphanumeric(\"ve534c5g35gb3\")"},
            {"isLowerCase", "ds.strings.isLowerCase(\"hello\")"},
            {"isNumeric", "ds.strings.isNumeric(\"34634\")"},
            {"isUpperCase", "ds.strings.isUpperCase(\"HELLO\")"},
            {"isWhitespace", "ds.strings.isWhitespace(\"      \")"},
            {"leftPad", "ds.strings.leftPad(\"Hello\",10)"},
            {"ordinalize", "ds.strings.ordinalize(1)"},
            {"pluralize", "ds.strings.pluralize(\"car\")"},
            {"prependIfMissing", "ds.strings.prependIfMissing(\" World\",\"Hello\")"},
            {"repeat", "ds.strings.repeat(\"Hello \",2)"},
            {"rightPad", "ds.strings.rightPad(\"Hello\",10)"},
            {"singularize", "ds.strings.singularize(\"cars\")"},
            {"substringAfter", "ds.strings.substringAfter(\"!XHelloXWorldXAfter\", \"X\")"},
            {"substringAfterLast", "ds.strings.substringAfterLast(\"!XHelloXWorldXAfter\", \"X\")"},
            {"substringBefore", "ds.strings.substringBefore(\"!XHelloXWorldXAfter\", \"X\")"},
            {"substringBeforeLast", "ds.strings.substringBeforeLast(\"!XHelloXWorldXAfter\", \"X\")"},
            {"underscore", "ds.strings.underscore(\"Hello WorldX\")"},
            {"unwrap", "ds.strings.unwrap(\" World \",\"Hello\")"},
            {"withMaxSize", "ds.strings.withMaxSize(\"Hello World\",5)"},
            {"wrapIfMissing", "ds.strings.wrapIfMissing(\" World \",\"Hello\")"},
            {"wrapWith", "ds.strings.wrapWith(\" World \",\"Hello\")"},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
