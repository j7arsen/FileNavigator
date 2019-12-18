package com.j7arsen.filenavigator.di.app;

import com.j7arsen.filenavigator.di.app.module.ContextModule;
import com.j7arsen.filenavigator.di.app.module.JsonParserModule;
import com.j7arsen.filenavigator.di.app.module.NavigationModule;
import com.j7arsen.filenavigator.di.app.module.ThreadingModule;
import com.j7arsen.filenavigator.di.app.module.UtilsModule;
import com.j7arsen.filenavigator.di.main.MainComponent;

import dagger.Component;

@AppScope
@Component(modules = {ContextModule.class, ThreadingModule.class, UtilsModule.class, JsonParserModule.class, NavigationModule.class})
public interface AppComponent {

    MainComponent.Builder mainComponentBuilder();

}