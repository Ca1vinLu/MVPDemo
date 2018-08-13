package me.lyz.mvpdemo.home;

import me.lyz.mvpdemo.mvp.BasePresenter;

/**
 * @author LYZ 2018.08.14
 */
public class HomePresenter extends BasePresenter<IHomeContract.View, IHomeContract.Model>
        implements IHomeContract.Presenter {

    private UserModel mUserModel;

    public HomePresenter(IHomeContract.View view) {
        super(view);
        mUserModel = new UserModel();
    }


    @Override
    public void onLogin(String name, String psd) {
        IHomeContract.View view = getView();
        if (mUserModel.login(name, psd)) {
            view.onLoginSuccess();
        } else {
            view.onLoginFailed();
        }
    }

    @Override
    public void onRegister(String name, String psd) {
        IHomeContract.View view = getView();
        if (mUserModel.register(name, psd)) {
            view.onRegisterSuccess();
        } else {
            view.onRegisterFailed();
        }
    }
}
