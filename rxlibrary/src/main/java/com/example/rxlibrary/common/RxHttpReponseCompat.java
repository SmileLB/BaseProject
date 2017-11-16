package com.example.rxlibrary.common;


import com.example.rxlibrary.baseBaen.Result;
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

    public static <T> ObservableTransformer<Result<T>,T> compatResult(){

        return  new ObservableTransformer<Result<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<Result<T>> ResultObservable) {

                return ResultObservable.flatMap(new Function<Result<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final Result<T> tResult) throws Exception {

                        if(tResult.getCode()==1){

                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {

                                    try {
                                        subscriber.onNext(tResult.getResult());
                                        subscriber.onComplete();

                                    } catch (Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });

                        } else {
                            return  Observable.error(new ApiException(tResult.getCode(),tResult.getMsg()));
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };

    }
}
