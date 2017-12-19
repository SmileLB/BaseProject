package com.example.rxlibrary.common;


import com.example.rxlibrary.baseBaen.BaseRespMsg;
import com.example.rxlibrary.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxHttpReponseCompat {

    public static <T> ObservableTransformer<BaseRespMsg<T>, T> compatResult() {

        return new ObservableTransformer<BaseRespMsg<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseRespMsg<T>> BaseRespMsgObservable) {

                return BaseRespMsgObservable.flatMap(new Function<BaseRespMsg<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseRespMsg<T> tBaseRespMsg) throws Exception {

                        if ("success".equals(tBaseRespMsg.getReason())) {

                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {

                                    try {
                                        subscriber.onNext(tBaseRespMsg.getData());
                                        subscriber.onComplete();

                                    } catch (Exception e) {
                                        subscriber.onError(e);
                                    }
                                }
                            });

                        } else {
//                            return  Observable.error(new ApiException(tBaseRespMsg.getError_code(),tBaseRespMsg.getReason()));

                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                                    subscriber.onError(new ApiException(tBaseRespMsg.getError_code(), tBaseRespMsg.getReason()));
                                }
                            });

                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };

    }
}
