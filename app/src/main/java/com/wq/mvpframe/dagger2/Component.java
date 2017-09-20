package com.wq.mvpframe.dagger2;

import com.wq.mvpframe.presenter.MvpPresenter;

@dagger.Component(modules = Module.class)
public interface Component {
    MvpPresenter inject();
}
