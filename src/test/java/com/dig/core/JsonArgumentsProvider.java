package com.dig.core;

import com.google.gson.Gson;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.Arrays;
import java.util.stream.Stream;

/* Junit5 extension to use JSON as test data source */
public class JsonArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<JsonSource> {

    private String[] values;
    private Class<?> type;

    @Override
    public void accept(final JsonSource annotation) {
        values = annotation.value();
        type = annotation.type();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext context) {
        return Arrays.stream(values)
                .map(value -> new Gson().fromJson(value, type))
                .map(Arguments::of);
    }
}