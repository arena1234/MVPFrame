package com.wq.mvpframe.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wq.mvpframe.BR;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 (一) @Entity 定义实体
 @nameInDb 在数据库中的名字，如不写则为实体中类名
 @indexes 索引
 @createInDb 是否创建表，默认为true,false时不创建
 @schema 指定架构名称为实体
 @active 无论是更新生成都刷新
 (二) @Id
 (三) @NotNull 不为null
 (四) @Unique 唯一约束
 (五) @ToMany 一对多
 (六) @OrderBy 排序
 (七) @ToOne 一对一
 (八) @Transient 不存储在数据库中
 (九) @generated 由greendao产生的构造函数或方法

 作者：anye0803
 链接：http://www.jianshu.com/p/4986100eff90
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

@Entity(
        nameInDb = "user_table"
)
public class User extends BaseObservable {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String password;

    public User() {
        this.name = "Qiang Wang";
        this.password = "Aa123456";
    }

    public User(String name, String pwd) {
        this.name = name;
        this.password = pwd;
    }

    @Generated(hash = 1691991404)
    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User setUser(User user) {
        setName(user.name);
        setPassword(user.password);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
