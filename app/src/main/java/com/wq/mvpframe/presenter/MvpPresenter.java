package com.wq.mvpframe.presenter;

import android.util.Log;
import android.view.View;

import com.wq.mvpframe.MVPContract;
import com.wq.mvpframe.bean.User;
import com.wq.mvpframe.model.MvpModel;

import javax.inject.Inject;

public class MvpPresenter implements MVPContract.IMvpPresenter, MVPContract.OnLoginListener {
    private MVPContract.IMvpModel mvpModel;
    private MVPContract.IMvpView mvpView;

    /**
     * 构造器添加注解
     *
     * @param view
     */
    @Inject
    public MvpPresenter(MVPContract.IMvpView view) {
        mvpView = view;
        mvpModel = new MvpModel();
    }

    @Override
    public void register(View view) {
        mvpModel.register(mvpView.getUserName(), mvpView.getUserPassword());
    }

    @Override
    public void login() {
        mvpModel.login(mvpView.getUserName(), mvpView.getUserPassword(), this);
    }

    @Override
    public User getUser() {
        return mvpModel.getUser();
    }

    @Override
    public void onPrepare() {
        mvpView.showWaitingDialog();
    }

    @Override
    public void onError(int error) {
        mvpView.hideWaitingDialog();
        mvpView.loginFailed();
    }

    @Override
    public void onSuccess(User user) {
        mvpView.hideWaitingDialog();
    }
}
