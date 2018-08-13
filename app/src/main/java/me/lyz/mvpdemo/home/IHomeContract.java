package me.lyz.mvpdemo.home;

/**
 * @author LYZ 2018.08.14
 */
public interface IHomeContract {


    interface View {
        void onLoginSuccess();

        void onLoginFailed();

        void onRegisterSuccess();

        void onRegisterFailed();

    }

    interface Presenter {

        void onLogin(String name, String psd);

        void onRegister(String name, String psd);
    }


    interface Model {

        boolean login(String name, String psd);

        boolean register(String name, String psd);

    }
}
