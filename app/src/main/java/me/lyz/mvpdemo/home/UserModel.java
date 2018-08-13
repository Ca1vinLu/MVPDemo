package me.lyz.mvpdemo.home;

/**
 * @author LYZ 2018.08.14
 */
public class UserModel implements IHomeContract.Model {
    @Override
    public boolean login(String name, String psd) {
        return true;
    }

    @Override
    public boolean register(String name, String psd) {
        return true;
    }
}
