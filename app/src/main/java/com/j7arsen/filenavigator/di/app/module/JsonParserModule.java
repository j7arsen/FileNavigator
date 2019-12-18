package com.j7arsen.filenavigator.di.app.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j7arsen.filenavigator.di.app.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class JsonParserModule {

    @AppScope
    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        gsonBuilder.enableComplexMapKeySerialization();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setVersion(1.0);
        return gsonBuilder.create();
    }

}
