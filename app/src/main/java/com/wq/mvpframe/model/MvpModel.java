package com.wq.mvpframe.model;

import com.wq.mvpframe.App;
import com.wq.mvpframe.MVPContract;
import com.wq.mvpframe.bean.User;
import com.wq.mvpframe.greendao.DaoSession;
import com.wq.mvpframe.greendao.UserDao;

import java.util.List;

public class MvpModel implements MVPContract.IMvpModel {
    private User user;
    private UserDao userDao;

    public MvpModel() {
        user = new User();
        userDao = App.getInstance().getDaoSession().getUserDao();
    }

    @Override
    public void login(final String name, final String pwd, final MVPContract.OnLoginListener listener) {
        if (listener != null) listener.onPrepare();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<User> users = userDao.loadAll();
                for(User u:users){
                    if (name.equals(u.getName()) && pwd.equals(u.getPassword())) {
                        if (listener != null) listener.onSuccess(user.setUser(new User(name, pwd)));
                        return;
                    }
                }
                if (listener != null) listener.onError(-1);
            }
        }).start();
    }

    @Override
    public void register(String name, String pwd) {
        userDao.insert(new User(name, pwd));
    }

    @Override
    public User getUser() {
        return user;
    }
}
