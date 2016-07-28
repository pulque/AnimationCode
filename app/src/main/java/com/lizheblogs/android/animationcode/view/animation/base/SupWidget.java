/*
 * Copyright 2016 Li Zhe <pulqueli@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lizheblogs.android.animationcode.view.animation.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.lizheblogs.android.animationcode.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 场景，SurfaceView
 * 主要的画布
 * 所有原子将画在这个view上
 * Created by Norman.Li on 7/25/2016.
 */
public abstract class SupWidget extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder sfh;
    private UpdateRunnable updateRunnable;

    //item attribute
    private int itemNum;
    private int itemColor = 0xffffffff;
    private int bgColor = 0xffffffff;
    private int delayStop = -1;
    private boolean randColor = false;

    public SupWidget(Context context) {
        super(context);
        initView(context, null);
    }

    public SupWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public SupWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        sfh = getHolder();
        sfh.addCallback(this);
        setZOrderOnTop(true);
        sfh.setFormat(PixelFormat.TRANSPARENT);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Molecular);
            itemNum = a.getInt(R.styleable.Molecular_itemNum, 0);
            itemColor = a.getColor(R.styleable.Molecular_itemColor, 0xff000000);
            randColor = a.getBoolean(R.styleable.Molecular_randColor, false);
            bgColor = a.getColor(R.styleable.Molecular_MBackground, -1);
            delayStop = a.getInt(R.styleable.Molecular_MDelayStop, -1);
            a.recycle();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    /**
     * need init scene
     *
     * @param itemNum
     * @param itemColor
     * @param randColor
     * @return
     */
    protected abstract Molecular initScene(int itemNum, int itemColor, boolean randColor);

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stop();
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        start();
    }

    public synchronized void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                stop();
                Molecular scene = initScene(itemNum, itemColor, randColor);
                updateRunnable = new UpdateRunnable(sfh, scene, bgColor);
                Thread spriteThread = new Thread(updateRunnable);
                spriteThread.start();
                if (delayStop > 0) {
                    delayStop();
                }
            }
        }).start();
    }

    private void delayStop() {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopSlowly();
                delayStop = -1;
            }
        }, delayStop);
    }

    public void stop() {
        if (updateRunnable != null) {
            updateRunnable.setRunning(false);
            updateRunnable = null;
        }
    }

    public void stopSlowly() {
        if (updateRunnable != null) {
            updateRunnable.setStopSlowly(true);
            updateRunnable = null;
        }
    }

    public void pause() {
        if (updateRunnable != null) {
            updateRunnable.setPause(true);
        }
    }

    public void resume() {
        if (updateRunnable != null) {
            updateRunnable.setPause(false);
        }
    }

    public void delay(int millisecond) {
        delayStop = millisecond;
        delayStop();
    }

}
