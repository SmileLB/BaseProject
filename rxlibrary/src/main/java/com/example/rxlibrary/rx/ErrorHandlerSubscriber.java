package com.example.rxlibrary.rx;

import android.content.Context;
import android.util.Log;

import com.example.rxlibrary.common.RxErrorHandler;
import com.example.rxlibrary.exception.BaseException;

import io.reactivex.disposables.Disposable;

/**
 * 错误处理的观察者
 *
 * @param <T>
 */
public abstract class ErrorHandlerSubscriber<T> extends DefualtSubscriber<T> {

    protected RxErrorHandler mErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context context) {
        this.mContext = context;
        mErrorHandler = new RxErrorHandler(mContext);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException = mErrorHandler.handleError(e);
        if (baseException == null) {
            e.printStackTrace();
            Log.e("ErrorHandlerSubscriber", e.getMessage());
        } else {
            mErrorHandler.showErrorMessage(baseException);
            if (baseException.getCode() == BaseException.ERROR_TOKEN) {

            }
        }
    }
}
