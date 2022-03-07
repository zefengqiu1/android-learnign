package com.zefeng.recycleview.dependency;

import com.zefeng.recycleview.MainActivity;
import com.zefeng.recycleview.MainContract;
import com.zefeng.recycleview.MainRouter;
import com.zefeng.recycleview.interactor.MainInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public MainContract.Router provideRouter() {
        return new MainRouter();
    }

    @Provides
    public MainContract.Interactor provideInteractor() {
        return new MainInteractor();
    }

}
