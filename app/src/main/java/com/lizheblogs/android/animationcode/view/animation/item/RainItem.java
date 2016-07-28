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

package com.lizheblogs.android.animationcode.view.animation.item;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.lizheblogs.android.animationcode.view.animation.base.Atom;

/**
 * 实现类 下雨的雨滴
 * Created by Norman.Li on 7/25/2016.
 */
public class RainItem extends Atom {

    private Paint paint = new Paint();
    private final int size = 30;    //雨滴的最大长度
    private final int speed_control = 25;    //控制速度，越大下降越快
    private final int speed_long = 10;    //速度的差异变量，确保雨滴的速度差不会太大
    private int speed_off = 0;    //速度基础变量，即最低速度，由计算得出
    private float speed_Acceleration_x = 0.01f;    //X轴加速度
    private float speed_Acceleration_y = 0.2f;    //Y轴加速度
    private RectF point;        //雨点
    private PointF speed;        //雨点x,y方向速度

    public RainItem(int width, int height, int color, boolean randColor) {
        super(width, height, color, randColor);
        paint.setStrokeWidth(5);
        point = new RectF();
        speed = new PointF();
        if (speed_control > speed_long) {
            speed_off = speed_control - speed_long;
        }
        reset();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(point.left, point.top, point.right, point.bottom, paint);
    }

    @Override
    public boolean move() {
        if (point.left < 0 || point.bottom > height) {
            if (isStopSlowly) {
                point.left = 2 * width;
                point.top = 2 * height;
                point.right = 2 * width;
                point.bottom = 2 * height;
                return false;
            } else {
                reset();
            }
        }

        point.left += speed.x;
        point.top += speed.y;
        point.right += speed.x;
        point.bottom += speed.y;

        speed.y += rand.nextBoolean() ? speed_Acceleration_y : 0;
        speed.x -= rand.nextBoolean() ? speed_Acceleration_x : 0;
        return true;
    }

    @Override
    public void reset() {
        int x = rand.nextInt(width) + width / 4;
        int y = -rand.nextInt(height);
        int w = rand.nextInt(size / 3);
        int h = rand.nextInt(size) + 20;

        w = w > h ? h : w;

        point.left = x;
        point.top = y;
        point.right = x - w;
        point.bottom = y + h;

        //1080f和1920f是我手机的分辨率，想确保所有屏幕的上的速度差异不大，可能不太对，还未验证
        float speedX = (rand.nextInt(speed_control - speed_off) + speed_off) * width / 1080f / 5f;
        float speedY = (rand.nextInt(speed_control - speed_off) + speed_off) * height / 1920f;

        speedX = speedX == 0 ? 1 : speedX;
        speedY = speedY == 0 ? 1 : speedY;
        speedX = speedX > speedY ? speedY : speedX;

        speed.x = -speedX;
        speed.y = speedY;
        randomColor();
        paint.setColor(color);
    }
}
