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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;

/**
 * update data
 * 主要更新原子的数据和画图
 * Created by Norman.Li on 7/26/2016.
 */
public class UpdateRunnable implements Runnable {
    private static final long LONG_RUN = 16L;
    private boolean isRunning = true;
    private boolean isPause = false;
    private boolean isStopSlowly = false;

    protected Molecular scene;
    private SurfaceHolder surfaceHolder;
    private int bgColor = -1;


    public UpdateRunnable(SurfaceHolder sfh, Molecular scene, int bgColor) {
        this.surfaceHolder = sfh;
        this.scene = scene;
        this.bgColor = bgColor;
    }

    @Override
    public void run() {
        while (true) {
            if (isPause) {
                sleep(LONG_RUN);
                continue;
            } else if (isRunning) {
                long Start = System.currentTimeMillis();
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas == null) {
                    sleep(LONG_RUN);
                    continue;
                }

                if (bgColor == -1)
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                else
                    canvas.drawColor(bgColor);

                if (scene != null) {
                    scene.move();
                    if (isStopSlowly) {
                        scene.setStopSlowly(isStopSlowly);
                        if (scene.list != null && scene.list.size() < 1) {
                            isRunning = false;
                        }
                    }
                    scene.draw(canvas);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
                long End = System.currentTimeMillis();
                if (End - Start >= LONG_RUN)
                    continue;
                long Difference = LONG_RUN - (End - Start);
                sleep(Difference);
            } else {
                Canvas canvas = surfaceHolder.lockCanvas();
                if (canvas == null) {
                    sleep(LONG_RUN);
                    continue;
                }
                if (bgColor == -1)
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                else
                    canvas.drawColor(bgColor);
                surfaceHolder.unlockCanvasAndPost(canvas);
                break;
            }
        }
    }

    private void sleep(long difference) {
        try {
            Thread.sleep(difference);
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public void setStopSlowly(boolean stopSlowly) {
        isStopSlowly = stopSlowly;
    }
}
