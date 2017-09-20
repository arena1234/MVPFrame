package com.wq.mvpframe.dagger2;

import com.wq.mvpframe.MVPContract;

import dagger.Provides;

@dagger.Module
public class Module {
    private MVPContract.IMvpView mvpView;

    public Module(MVPContract.IMvpView view) {
        mvpView = view;
    }

    /**
     * Provides注解代表提供的参数，为构造器传进来的
     *
     * @return
     */
    @Provides
    public MVPContract.IMvpView inject() {
        return mvpView;
    }
}
