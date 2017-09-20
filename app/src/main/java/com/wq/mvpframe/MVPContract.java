package com.wq.mvpframe;

import android.view.View;

import com.wq.mvpframe.bean.User;

/**
 * Created by qiangwang on 9/17/17.
 * MVP框架的契约类，定义MVP的接口
 */

public interface MVPContract {
    interface IMvpView {
        String getUserName();

        String getUserPassword();

        void showWaitingDialog();

        void hideWaitingDialog();

        void loginFailed();
    }

    interface IMvpPresenter {
        void register(View view);

        void login();

        User getUser();
    }

    interface IMvpModel {
        void login(String name, String pwd, OnLoginListener listener);

        void register(String name, String pwd);

        User getUser();
    }

    interface OnLoginListener {
        void onPrepare();

        void onError(int error);

        void onSuccess(User user);
    }
}
