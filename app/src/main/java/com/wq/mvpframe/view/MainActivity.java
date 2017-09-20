package com.wq.mvpframe.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wq.mvpframe.MVPContract;
import com.wq.mvpframe.R;
import com.wq.mvpframe.dagger2.DaggerComponent;
import com.wq.mvpframe.dagger2.Module;
import com.wq.mvpframe.databinding.ActivityMainBinding;
import com.wq.mvpframe.ui.WaitDialog;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MVPContract.IMvpView {
    /**
     * 当看到某个实例化被@Inject标记时
     * 就会到他的构造方法中，如果这个构造方法也被@Inject标记的话
     * 就会自动初始化这个类，从而完成依赖注入
     * Component就是将他们连接起来的桥梁
     *
     * @Inject 带有此注解的属性或构造方法将参与到依赖注入中，Dagger2会实例化有此注解的类
     * @Module 带有此注解的类，用来提供依赖，里面定义一些用@Provides注解的以provide开头的方法，
     * 这些方法就是所提供的依赖，Dagger2会在该类中寻找实例化某个类所需要的依赖。
     * @Component 用来将@Inject和@Module联系起来的桥梁，从@Module中获取依赖并将依赖注入给@Inject
     */
    @Inject
    MVPContract.IMvpPresenter presenter;
    private ActivityMainBinding mainBinding;
    private WaitDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        presenter = DaggerComponent.builder().module(new Module(this)).build().inject();

        mainBinding.setUser(presenter.getUser());
        mainBinding.setClick(presenter);

        waitDialog = new WaitDialog(this);
    }

    @Override
    public String getUserName() {
        return mainBinding.etName.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return mainBinding.etPassword.getText().toString();
    }

    @Override
    public void showWaitingDialog() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                waitDialog.show();
            }
        });
    }

    @Override
    public void hideWaitingDialog() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                waitDialog.dismiss();
            }
        });
    }

    @Override
    public void loginFailed() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, R.string.login_fail, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
