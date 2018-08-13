package me.lyz.mvpdemo.mvp;

/**
 * @author LYZ 2018.08.14
 */
public abstract class BasePresenter<MvpView, MvpModel> {
    private MvpView mMvpView;
    private MvpModel mMvpModel;

    public BasePresenter(MvpView view) {
        mMvpView = view;
    }

    public MvpView getView() {
        return mMvpView;
    }

    public void detachView() {
        mMvpView = null;
    }

}
