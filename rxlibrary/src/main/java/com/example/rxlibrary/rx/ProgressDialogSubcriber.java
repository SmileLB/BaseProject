package com.example.rxlibrary.rx;

import android.content.Context;

import com.example.rxlibrary.common.ProgressDialogHandler;

import io.reactivex.disposables.Disposable;

/**
 * 带进度加载对话框的观察者
 * @param <T>
 */
public abstract class ProgressDialogSubcriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    private Disposable mDisposable;


    public ProgressDialogSubcriber(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(mContext, true, this);
    }

    protected boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onCancelProgress() {
        mDisposable.dispose();
    }

    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if (isShowProgressDialog()) {
            this.mProgressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onComplete() {
        if (isShowProgressDialog()) {
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowProgressDialog()) {
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }
}
