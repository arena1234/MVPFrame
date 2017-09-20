package com.wq.mvpframe;

import android.app.Application;

import com.wq.mvpframe.greendao.DaoMaster;
import com.wq.mvpframe.greendao.DaoMaster.DevOpenHelper;
import com.wq.mvpframe.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    // 标准数据库还是加密数据库（是否加密）
    private static final boolean ENCRYPTED = false;
    private static volatile App app;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        app = this;
    }

    public static App getInstance(){
        return app;
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
