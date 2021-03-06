package com.ui.login;

import com.C;
import com.base.util.SpUtil;

/**
 * Created by baixiaokang on 16/4/29.
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login(String name, String pass) {
        //用Rx访问网络
        mRxManager.add(mModel.login(name, pass).subscribe(user -> {
            //存到sp文件
            SpUtil.setUser(user);
            mRxManager.post(C.EVENT_LOGIN, user);
            mView.loginSuccess();
        },
                //登录失败显示
                e -> {
                    mView.showMsg("登录失败!");
                }
        ));
    }

    @Override
    public void sign(String name, String pass) {
        mRxManager.add(mModel.sign(name, pass)
                .subscribe(res -> mView.signSuccess(),
                        e -> mView.showMsg("注册失败!")));
    }
}
