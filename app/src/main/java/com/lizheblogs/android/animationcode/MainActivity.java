package com.lizheblogs.android.animationcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lizheblogs.android.animationcode.view.animation.widget.AllView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AllView surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button startBut = (Button) findViewById(R.id.startBut);
        startBut.setOnClickListener(this);
        Button stopBut = (Button) findViewById(R.id.stopBut);
        stopBut.setOnClickListener(this);
        Button stopSlowlyBut = (Button) findViewById(R.id.stopSlowlyBut);
        stopSlowlyBut.setOnClickListener(this);
        Button pauseBut = (Button) findViewById(R.id.pauseBut);
        pauseBut.setOnClickListener(this);
        Button resumeBut = (Button) findViewById(R.id.resumeBut);
        resumeBut.setOnClickListener(this);
        Button delayBut = (Button) findViewById(R.id.delayBut);
        delayBut.setOnClickListener(this);
        Button paperBut = (Button) findViewById(R.id.paperBut);
        paperBut.setOnClickListener(this);
        Button rainBut = (Button) findViewById(R.id.rainBut);
        rainBut.setOnClickListener(this);

        surface = (AllView) findViewById(R.id.surface);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startBut:
                surface.start();
                break;
            case R.id.stopBut:
                surface.stop();
                break;
            case R.id.stopSlowlyBut:
                surface.stopSlowly();
                break;
            case R.id.pauseBut:
                surface.pause();
                break;
            case R.id.resumeBut:
                surface.resume();
                break;
            case R.id.delayBut:
                surface.delay(5000);
                break;
            case R.id.paperBut:
                surface.setViewType(AllView.PAPER);
                surface.start();
                break;
            case R.id.rainBut:
                surface.setViewType(AllView.RAIN);
                surface.start();
                break;
            default:
                break;
        }
    }
}
