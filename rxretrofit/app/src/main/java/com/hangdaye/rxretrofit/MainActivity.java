package com.hangdaye.rxretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    private TextView mTvResult;
    private MainMvpPresent mMainMvpPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = (TextView) findViewById(R.id.tv_result);

        mMainMvpPresent = new MainPresentImpl<>(this);
    }

    @Override
    public void showWeatherInfo(String info) {
        mTvResult.setText(info);
    }

    @Override
    public void showErrorInfo(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRxJavaWeatherInfo(String info) {
        mTvResult.setText(mTvResult.getText() + info);
    }

    public void beijing(View view) {
        mMainMvpPresent.getWeatherInfo("beijing");
    }

    public void shanghai(View view) {
        mMainMvpPresent.getWeatherInfo("shanghai");
    }

    public void shenzhen(View view) {
        mMainMvpPresent.getWeatherInfo("shenzhen");
    }

    public void rxAll(View view) {
        mMainMvpPresent.getRxJavaWeatherInfo(new String[]{"beijing","shanghai","shenzhen"});
    }
}
