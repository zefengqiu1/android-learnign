package com.zefeng.recycleview.dependency;

import com.zefeng.recycleview.MainActivity;
import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
