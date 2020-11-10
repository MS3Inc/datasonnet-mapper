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


public class DSCore {

    @Benchmark
    public Object fullCore(FullCoreState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullCoreState {
        Mapper mapper;
        Document<String> payload;

        /*@Param({"append", "prepend", "combine", "contains",
                "distinctBy","endsWith", "entriesOf", "filter",
                "filterObject", "find", "flatMap", "flatten",
                "floor", "groupBy", "isBlank", "isDecimal", "isEmpty",
                "isEven", "isInteger", "isLeapYear", "isOdd",
                "joinBy", "keysOf", "lower", "map",
                "mapObject","match", "matches", "max",
                "maxBy", "min", "minBy", "namesOf",
                "orderBy", "mapEntries", "read", "readUrl",
                "reduce", "replace", "scan", "sizeOf",
                "splitBy", "startsWith", "to", "trim",
                "typeOf", "unzip", "upper", "uuid",
                "valuesOf", "write", "zip"})*/
        @Param({"append_ARR","combine_ARR","combine_NUM","combine_OBJ",
                "combine_STR","contains_ARR","contains_STR","distinctBy_ARR",
                "distinctBy_ARR_2","distinctBy_OBJ","distinctBy_OBJ_2",
                "endsWith_STR","entriesOf_OBJ","filterObject_NULL",
                "filterObject_OBJ","filterObject_OBJ_2","filterObject_OBJ_3",
                "filter_ARR","filter_ARR_2","filter_NULL","find_ARR","find_STR",
                "flatMap_ARR","flatMap_ARR_2","flatten_ARR","foldLeft_ARR",
                "foldRight_ARR","groupBy_ARR","groupBy_ARR_2","groupBy_OBJ_1",
                "groupBy_OBJ_2","isArray_ARR","isBlank_STR","isBoolean_BOOL",
                "isDecimal_NUM","isEmpty_OBJ","isEven_NUM","isFunction_FUNC",
                "isInteger_NUM","isNumber_NUM","isObject_OBJ","isOdd_NUM",
                "isString_STR","joinBy_ARR","keysOf_OBJ","lower_STR",
                "mapEntries_OBJ","mapEntries_OBJ_2","mapEntries_OBJ_3",
                "mapObject_OBJ","mapObject_OBJ_2","mapObject_OBJ_3","map_ARR",
                "map_ARR_2","match_STR","matches_STR","maxBy_ARR","max_ARR",
                "minBy_ARR","min_ARR","orderBy_OBJ","orderBy_OBJ_2",
                "parseDouble_STR","parseHex_STR","parseInt_STR","parseOctal_STR",
                "prepend_ARR","range_NUM","readUrl_STR","read_STR",
                "removeMatch_ARR","removeMatch_OBJ","remove_ARR","remove_OBJ",
                "replace_STR","scan_STR","sizeOf_ARR","splitBy_STR",
                "startsWith_STR","trim_STR","typeOf_BOOL","unzip_ARR",
                "upper_STR","uuid","valuesOf_OBJ","write_ARR","write_OBJ",
                "zip_ARR"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(coreScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    //{"daysBetween", "ds.datetime.daysBetween(\"2016-10-01T23:57:59.425Z\",\"2017-10-01T23:57:59.425Z\")"},
    //            {"isLeapYear", "ds.datetime.isLeapYear(\"2016-10-01T23:57:59.425Z\")"},

    //{"floor_NUM", "ds.math.floor(1.9)"},

    public static final Map<String, String> coreScripts = Stream.of(new String[][]{
            {"append_ARR", "ds.append([1,2,3], 4)"},
            {"combine_STR", "ds.combine(\"Hel\", \"lo\")"},
            {"combine_NUM", "ds.combine(9, 11)"},
            {"combine_ARR", "ds.combine([1,2],[3,4])"},
            {"combine_OBJ", "ds.combine({a:1}, {b:2})"},
            {"contains_ARR", "ds.contains([ 1, 2, 3, 4 ],2)"},
            {"contains_STR", "ds.contains(\"Hello World\",\"Wo\")"},
            {"distinctBy_ARR", "ds.distinctBy([0,1,2,3,3,2,1,4], function(value) value)"},
            {"distinctBy_ARR_2", "ds.distinctBy([0, 1, 2, 3, 3, 2, 1, 4], function(item,index) index)"},
            {"distinctBy_OBJ", "ds.distinctBy({\"a\":0, \"b\":1, \"c\":0}, function(value) value)"},
            {"distinctBy_OBJ_2", "ds.distinctBy({\"a\":0, \"b\":1, \"c\":0}, function(value, key) value)"},
            {"endsWith_STR", "ds.endsWith(\"Hello World\", \"World\")"},
            {"entriesOf_OBJ", "ds.entriesOf({a:1})"},
            {"filter_NULL", "ds.filter(null, function(value) value > 2)"},
            {"filter_ARR", "ds.filter([9,2,3,4,5], function(value) value > 2)"},
            {"filter_ARR_2", "ds.filter([9,2,3,4,5], function(value, index) value > 2)"},
            {"filterObject_NULL", "ds.filterObject(null, function(value) value == \"apple\")"},
            {"filterObject_OBJ", "ds.filterObject({\"a\" : \"apple\", \"b\" : \"banana\"}, function(value) value == \"apple\")"},
            {"filterObject_OBJ_2", "ds.filterObject({\"a\" : \"apple\", \"b\" : \"banana\"}, function(value,a) value == \"apple\")"},
            {"filterObject_OBJ_3", "ds.filterObject({\"a\" : \"apple\", \"b\" : \"banana\"}, function(value,a,b) value == \"apple\")"},
            {"find_ARR", "ds.find([\"Bond\", \"James\", \"Bond\"], \"Bond\")"},
            {"find_STR", "ds.find(\"Hello World\", \"Hell\")"},
            {"flatMap_ARR", "ds.flatMap([ [3,5], [0.9,5.5] ], function(value) value)"},
            {"flatMap_ARR_2", "ds.flatMap([ [3,5], [0.9,5.5] ], function(value, index) value+index)"},
            {"flatten_ARR", "ds.flatten([[1,2,3], [4,5,6], [7,8,9]])"},
            {"foldLeft_ARR", "ds.foldLeft([1,2,3,4], 1, function(curr,prev) curr * prev)"},
            {"foldRight_ARR", "ds.foldRight([1,2,3,4], 1, function(curr,prev) curr * prev)"},
            {"groupBy_ARR", "ds.groupBy([1, 2, 3, 4], function(value) if (ds.isEven(value)) then 'even' else 'odd')"},
            {"groupBy_ARR_2", "ds.groupBy([1, 2, 3, 4], function(value,index) if (ds.isEven(value)) then 'even' else 'odd')"},
            {"groupBy_OBJ_1", "ds.groupBy({a:\"Alpha\", b:\"Bravo\", c: \"Alpha\"}, function(value,key) value)"},
            {"groupBy_OBJ_2", "ds.groupBy({a:\"Alpha\", b:\"Bravo\", c: \"Alpha\"}, function(value,key) value)"},
            {"isArray_ARR", "ds.isArray([])"},
            {"isBlank_STR", "ds.isBlank(\"\")"},
            {"isBoolean_BOOL", "ds.isBoolean(true)"},
            {"isDecimal_NUM", "ds.isDecimal(1.2)"},
            {"isEmpty_OBJ", "ds.isEmpty({})"},
            {"isEven_NUM", "ds.isEven(2)"},
            {"isFunction_FUNC", "ds.isFunction(function(x) x)"},
            {"isInteger_NUM", "ds.isInteger(2)"},
            {"isNumber_NUM", "ds.isNumber(2)"},
            {"isObject_OBJ", "ds.isObject({})"},
            {"isOdd_NUM", "ds.isOdd(1)"},
            {"isString_STR", "ds.isString(\"\")"},
            {"joinBy_ARR", "ds.joinBy([\"a\",\"b\",\"c\"] , \"-\")"},
            {"keysOf_OBJ", "ds.keysOf({ \"a\" : true, \"b\" : 1})"},
            {"lower_STR", "ds.lower(\"HELLO\")"},
            {"map_ARR", "ds.map([1, 2, 3, 4, 5], function(value) value + value)"},
            {"map_ARR_2", "ds.map([1, 2, 3, 4, 5], function(value,index) value + index)"},
            {"mapEntries_OBJ", "ds.mapEntries({a:1,b:2,c:3}, function(val) val)"},
            {"mapEntries_OBJ_2", "ds.mapEntries({a:1,b:2,c:3}, function(val,key) {[key]:val})"},
            {"mapEntries_OBJ_3", "ds.mapEntries({a:1,b:2,c:3}, function(val,key,indx) {[key]:val+indx})"},
            {"mapObject_OBJ", "ds.mapObject({a:1,b:2,c:3}, function(value) {[ds.combine(\"x\", value)]:value})"},
            {"mapObject_OBJ_2", "ds.mapObject({a:1,b:2,c:3}, function(value,key) {[key]:value})"},
            {"mapObject_OBJ_3", "ds.mapObject({a:1,b:2,c:3}, function(value,key,index) {[key]:value+index})"},
            {"match_STR", "ds.match(\"test@server.com\",\"([a-z]*)@([a-z]*).com\")"},
            {"matches_STR", "[ ds.matches(\"admin123\", \"a.*\\\\d+\"), ds.matches(\"admin123\", \"^b.+\") ]"},
            {"max_ARR", "ds.max([1, 1000])"},
            {"maxBy_ARR", "ds.maxBy([ { \"a\" : 1 }, { \"a\" : 3 }, { \"a\" : 2 } ], function(item) item.a)"},
            {"min_ARR", "ds.min([1, 1000])"},
            {"minBy_ARR", "ds.minBy([ { \"a\" : 1 }, { \"a\" : 3 }, { \"a\" : 2 } ], function(item) item.a)"},
            {"orderBy_OBJ", "ds.orderBy([{a:5}, {a:7}, {a:3}], function(value) value.a)"},
            {"orderBy_OBJ_2", "ds.orderBy([{a:5}, {a:7}, {a:3}], function(value,key) value.a)"},
            {"parseDouble_STR", "ds.parseDouble(\"2.5\")"},
            {"parseHex_STR", "ds.parseHex(\"F\")"},
            {"parseInt_STR", "ds.parseHex(\"15\")"},
            {"parseOctal_STR", "ds.parseOctal(\"17\")"},
            {"prepend_ARR", "ds.prepend([1,2,3], 4)"},
            {"range_NUM", "ds.range(1,10)"},
            {"read_STR", "ds.read('{ \"hello\" : \"world\" }','application/json',{})"},
            {"readUrl_STR", "ds.readUrl(\"https://jsonplaceholder.typicode.com/posts/1\")"},
            {"remove_ARR", "ds.remove([1,2,3,4], 4)"},
            {"remove_OBJ", "ds.remove({a:1,b:2,c:3}, \"b\")"},
            {"removeMatch_ARR", "ds.removeMatch([1,2,3,4], [4])"},
            {"removeMatch_OBJ", "ds.removeMatch({a:1,b:2,c:3}, {b:2,c:15})"},
            {"replace_STR", "ds.replace(\"Hello World\", \"Hello\", \"Goodbye\")"},
            {"scan_STR", "ds.scan(\"test@server.com\", \"(.*)@(.*)(.com)\")"},
            {"sizeOf_ARR", "ds.sizeOf([ \"a\", \"b\", \"c\"])"},
            {"splitBy_STR", "ds.splitBy(\"a-b-c-d\",\"-\")"},
            {"startsWith_STR", "ds.startsWith(\"Hello World\", \"Hello\")"},
            {"trim_STR", "ds.trim(\"      Hello World       \")"},
            {"typeOf_BOOL", "ds.typeOf(true)"},
            {"unzip_ARR", "ds.unzip([[1,2],[1,2]])"},
            {"upper_STR", "ds.upper(\"HeLlO wOrLd\")"},
            {"uuid", "ds.uuid"},
            {"valuesOf_OBJ", "ds.valuesOf({a:1,b:2})"},
            {"write_ARR", "ds.write([1,2,3,4], \"application/json\", {})"},
            {"write_OBJ", "ds.write({\"a\":1}, \"application/json\", {})"},
            {"zip_ARR", "ds.zip([1,2],[3,4,5])"},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));



}

