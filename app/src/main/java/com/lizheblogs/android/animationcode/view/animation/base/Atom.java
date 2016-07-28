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

import java.util.Random;

/**
 * Atom 抽象类，保存一些基础公共变量
 * Created by Norman.Li on 7/25/2016.
 */
public abstract class Atom implements AnimationInterface {

    /**
     * 视图显示区域的宽度
     */
    protected int width;
    /**
     * 视图显示区域的高度
     */
    protected int height;
    /**
     * 效果元素的随机对象
     */
    protected Random rand;
    /**
     * item color 原子的画笔颜色，
     * 如果randColor为true，此变量失效。
     */
    protected int color;
    /**
     * rand item color
     * 是否随机颜色
     */
    protected boolean randColor;

    /**
     * 是否是全部元素退出后就结束动画线程
     */
    protected boolean isStopSlowly;

    /**
     * 构造方法
     *
     * @param width     surface view的宽
     * @param height    surface view的高
     * @param color     原子的画笔颜色
     * @param randColor 是否随机颜色
     */
    public Atom(int width, int height, int color, boolean randColor) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.randColor = randColor;
        rand = new Random();
    }

    /**
     * 随机颜色
     */
    protected void randomColor() {
        if (randColor) {
            int alpha = 200;
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            color = alpha << 24 | r << 16 | g << 8 | b;
        }
    }

    /**
     * 是否是全部元素退出后就结束动画线程
     *
     * @param stopSlowly false 立即结束
     */
    public void setStopSlowly(boolean stopSlowly) {
        isStopSlowly = stopSlowly;
    }
}
