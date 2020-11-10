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

public class DSArrays {

    @Benchmark
    public Object fullArrays(FullArraysState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullArraysState {
        Mapper mapper;
        Document<String> payload;

        @Param({"countBy_ARR","deepFlatten_ARR","divideBy_ARR",
                "dropWhile_ARR","drop_ARR","every_ARR","firstWith_ARR",
                "firstWith_ARR_2","indexOf_ARR","indexOf_NULL","indexOf_STR",
                "indexWhere_ARR","join_ARR","leftJoin_ARR","occurrences_ARR",
                "outerJoin_ARR","partition_ARR","slice_ARR","some_ARR",
                "some_NULL","splitAt_ARR","splitWhere_ARR","sumBy_ARR",
                "takeWhile_ARR","take_ARR"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(arraysScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> arraysScripts = Stream.of(new String[][]{
            {"countBy_ARR", "ds.arrays.countBy([1,2,3,4,5],function(item) item>2)"},
            {"deepFlatten_ARR", "ds.arrays.deepFlatten([1,2,[3,[4]]])"},
            {"divideBy_ARR", "ds.arrays.divideBy([1,2,3,4,5],2)"},
            {"drop_ARR", "ds.arrays.drop([1,2,3,4,5],3)"},
            {"dropWhile_ARR", "ds.arrays.dropWhile([1,2,3,4,5],function(item) item < 3)"},
            {"every_ARR", "ds.arrays.every([1,2,3,4,5],function(item) item >0)"},
            {"firstWith_ARR", "ds.arrays.firstWith([1,2,3,4,5],function(item) item == 1)"},
            {"firstWith_ARR_2", "ds.arrays.firstWith([1,2,3,4,5],function(item,index) item)"},
            {"indexOf_ARR", "ds.arrays.indexOf([1,2,3,4,5],3)"},
            {"indexOf_STR", "ds.arrays.indexOf(\"Hello World\",\"d\")"},
            {"indexOf_NULL", "ds.arrays.indexOf(null,\"d\")"},
            {"indexWhere_ARR", "ds.arrays.indexWhere([1,2,3,4,5],function(item) item == 3)"},
            {"join_ARR", "ds.arrays.join(\n" +
                    "    [{\"id\":1,\"v\":\"a\"},{\"id\":1,\"v\":\"b\"}],[{\"id\":1,\"v\":\"c\"}], \n" +
                    "    function(item) item.id,function(item) item.id\n" +
                    ")"},
            {"leftJoin_ARR", "ds.arrays.leftJoin(\n" +
                    "    [{\"id\":1,\"v\":\"a\"},{\"id\":1,\"v\":\"b\"}],[{\"id\":1,\"v\":\"c\"}], \n" +
                    "    function(item) item.id,function(item) item.id\n" +
                    ")"},
            {"occurrences_ARR", "ds.arrays.occurrences([1,0,0,1,1,1], function(item) item)"},
            {"outerJoin_ARR", "ds.arrays.outerJoin(\n" +
                    "    [{\"id\":1,\"v\":\"a\"},{\"id\":1,\"v\":\"b\"}],[{\"id\":1,\"v\":\"c\"}], \n" +
                    "    function(item) item.id,function(item) item.id\n" +
                    ")"},
            {"partition_ARR", "ds.arrays.partition([1,2,3,4,5],function(item) item >3)"},
            {"slice_ARR", "ds.arrays.slice([1,2,3,4,5],2,4)"},
            {"some_ARR", "ds.arrays.some([1,2,3],function(item) item<2)"},
            {"some_NULL", "ds.arrays.some(null,function(item) item<2)"},
            {"splitAt_ARR", "ds.arrays.splitAt([1,2,3,4,5],3)"},
            {"splitWhere_ARR", "ds.arrays.splitWhere([1,2,3,4,5],function(item) item >3)"},
            {"sumBy_ARR", "ds.arrays.sumBy([1,2,3,4,5],function(item) item)"},
            {"take_ARR", "ds.arrays.take([1,2,3,4],3)"},
            {"takeWhile_ARR", "ds.arrays.takeWhile([1,2,3,4,5],function(item) item <3)"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
