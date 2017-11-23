package com.example.administrator.baseproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.baseproject.base.BaseActivity;
import com.example.administrator.baseproject.http.RetrofitHelper;
import com.example.administrator.baseproject.request.Requstdate2;
import com.example.rxlibrary.baseBaen.Msg;
import com.example.rxlibrary.common.RxHttpReponseCompat;
import com.example.rxlibrary.rx.ProgressDialogSubcriber;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;

public class MainActivity extends BaseActivity{

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDatePost();
//                loadDate();
//                startActivity(new Intent(MainActivity.this,TwoActivity.class));
            }
        });
    }

    private void loadDatePost() {
        HashMap<String,String> map=new HashMap<>();
        map.put("chars", "聚合数据");
        map.put("key", "a8cf8dac8c50d46000b41d2e08727862");

        Requstdate2 body=new Requstdate2("聚合数据");

        RetrofitHelper.getService(getApplication())
                .testPost(body)
                .delay(2, TimeUnit.SECONDS)
                .compose(RxHttpReponseCompat.<Msg>compatResult())
                .subscribe(new ProgressDialogSubcriber<Msg>(this) {
                    @Override
                    public void onNext(@NonNull Msg msg) {
                        Toast.makeText(MainActivity.this, msg.getTelecodes(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void helloEventBus(Object obj) {

    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    private void loadDate() {

        RetrofitHelper.getService(getApplication())
                .test("聚合数据")
                .delay(2, TimeUnit.SECONDS)
                .compose(RxHttpReponseCompat.<Msg>compatResult())
                .subscribe(new ProgressDialogSubcriber<Msg>(this) {
                    @Override
                    public void onNext(@NonNull Msg msg) {
                        Toast.makeText(MainActivity.this, msg.getTelecodes(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }
}
