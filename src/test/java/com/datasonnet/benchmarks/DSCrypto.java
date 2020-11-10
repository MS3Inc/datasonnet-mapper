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

public class DSCrypto {

    @Benchmark
    public Object fullCrypto(FullCryptoState state) {
        return state.mapper.transform(state.payload).getContent();
    }

    @State(Scope.Benchmark)
    public static class FullCryptoState {
        Mapper mapper;
        Document<String> payload;

        @Param({"hash_MD2","hash_MD5","hash_SHA-1","hash_SHA-256","hash_SHA-384","hash_SHA-512","hmac_SHA1","hmac_SHA256","hmac_SHA512"})
        public String scriptName;

        @Setup(Level.Trial)
        public void setup() throws Exception {
            mapper = new MapperBuilder(cryptoScripts.get(scriptName))
                    .configurePlugins(list -> list.add(new DefaultJSONFormatPlugin()))
                    .build();
            payload = new DefaultDocument<>(null, MediaTypes.APPLICATION_JSON);
        }
    }

    public static final Map<String, String> cryptoScripts = Stream.of(new String[][]{
            {"hash_MD2", "ds.crypto.hash(\"Hello World\", \"MD2\")"},
            {"hash_MD5", "ds.crypto.hash(\"Hello World\", \"MD5\")"},
            {"hash_SHA-1", "ds.crypto.hash(\"Hello World\", \"SHA-1\")"},
            {"hash_SHA-256", "ds.crypto.hash(\"Hello World\", \"SHA-256\")"},
            {"hash_SHA-384", "ds.crypto.hash(\"Hello World\", \"SHA-384\")"},
            {"hash_SHA-512", "ds.crypto.hash(\"Hello World\", \"SHA-512\")"},
            {"hmac_SHA1", "ds.crypto.hmac(\"Hello World\", \"key\", \"HmacSHA1\")"},
            {"hmac_SHA256", "ds.crypto.hmac(\"Hello World\", \"key\", \"HmacSHA256\")"},
            {"hmac_SHA512", "ds.crypto.hmac(\"Hello World\", \"key\", \"HmacSHA512\")"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
}
