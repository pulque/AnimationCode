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
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

import com.lizheblogs.android.animationcode.view.animation.base.Atom;
import com.lizheblogs.android.animationcode.view.animation.base.ChangeInLaw;

/**
 * 实现类，纸片
 * 变量含义参照RainItem
 * Created by Norman.Li on 7/25/2016.
 */
public class PaperItem extends Atom {

    private Paint paint;
    private final int size = 30;
    private final int speed_control = 10;
    private final int speed_long = 10;
    private int speed_off = 0;
    private float speed_Acceleration_x = 0.01f;
    private float speed_Acceleration_y = 0.02f;
    private PointF speed;
    private Path path;
    private Point pointWH;
    private Point pointXY;
    private PointF point1;
    private PointF point2;
    private PointF point3;
    private PointF point4;
    private double angle;

    private ChangeInLaw changeInLaw;

    public PaperItem(int width, int height, int color, boolean randColor) {
        super(width, height, color, randColor);
        paint = new Paint();
        path = new Path();
        speed = new PointF();
        pointXY = new Point();
        pointWH = new Point();
        point1 = new PointF();
        point2 = new PointF();
        point3 = new PointF();
        point4 = new PointF();
        if (speed_control > speed_long) {
            speed_off = speed_control - speed_long;
        }
        reset();
    }

    @Override
    public void draw(Canvas canvas) {
        path.reset();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean move() {
        if (pointXY.x < -width / 2 || pointXY.y > height / 2 * 3) {
            if (isStopSlowly) {
                pointXY.x = 2 * width;
                pointXY.y = 2 * height;
                return false;
            } else {
                reset();
            }
        }

        pointXY.x += speed.x;
        pointXY.y += speed.y;

        updatePoint(pointXY.x, pointXY.y, changeInLaw.getNum(speed.y));
        speed.y += rand.nextBoolean() ? speed_Acceleration_y : 0;
        speed.x -= rand.nextBoolean() ? speed_Acceleration_x : 0;
        return true;
    }

    @Override
    public void reset() {
        pointWH.x = rand.nextInt(size) + size + 20;
        pointWH.y = rand.nextInt(size) + 20;

        pointXY.x = rand.nextInt(width);
        pointXY.y = -rand.nextInt(height);
        int d = rand.nextInt(90) - 45;
        if (d == 0)
            d = 1;
        angle = Math.toRadians(d);

        changeInLaw = new ChangeInLaw(pointWH.y, false);

        updatePoint(pointXY.x, pointXY.y, pointWH.y);

        float speedX = (rand.nextInt(speed_control - speed_off) + speed_off) * width / 1080f / 5f;
        float speedY = (rand.nextInt(speed_control - speed_off) + speed_off) * height / 1920f;

        speedX = speedX == 0 ? 1 : speedX;
        speedY = speedY == 0 ? 1 : speedY;
        speedX = speedX > speedY ? speedY : speedX;

        speed.x = speedX;
        speed.y = speedY;

        randomColor();
        paint.setColor(color);
    }

    private void updatePoint(int x, int y, int variableY) {
        point1.x = x - pointWH.x / 2;
        point1.y = y + variableY / 2;
        changePoint(point1);

        point2.x = x + pointWH.x / 2;
        point2.y = y + variableY / 2;
        changePoint(point2);

        point3.x = x + pointWH.x / 2;
        point3.y = y - variableY / 2;
        changePoint(point3);

        point4.x = x - pointWH.x / 2;
        point4.y = y - variableY / 2;
        changePoint(point4);
    }

    private void changePoint(PointF mPointF) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float x = mPointF.x;
        float y = mPointF.y;

        mPointF.x = x * cos + y * sin;
        mPointF.y = y * cos - x * sin;
    }
}
