package me.lyz.mvpdemo.mvp;

import java.lang.ref.WeakReference;

/**
 * @author LYZ 2018.08.14
 */
public abstract class BasePresenter<MvpView, MvpModel> {
    private WeakReference<MvpView> mMvpView;

    public BasePresenter(MvpView view) {
        mMvpView = new WeakReference<>(view);
    }

    public MvpView getView() {
        return mMvpView.get();
    }

}
