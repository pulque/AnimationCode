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

import java.util.ArrayList;

/**
 * 分子类，包含原子类
 * 主要是原子的集合处理
 * Created by Norman.Li on 7/25/2016.
 */
public abstract class Molecular {

    protected int itemNum = 0;//效果元素数量
    protected int itemColor = 0;//粒子颜色
    protected boolean randColor = false;//rand color
    //效果场景宽高
    protected int width;
    protected int height;
    //效果容器
    protected ArrayList<Atom> list;
    protected boolean isStopSlowly;

    /**
     * @param width
     * @param height
     * @param itemNum
     * @param itemColor
     * @param randColor if true,itemColor Invalid
     */
    public Molecular(int width, int height, int itemNum, int itemColor, boolean randColor) {
        this.width = width;
        this.height = height;
        this.itemNum = itemNum;
        this.itemColor = itemColor;
        this.randColor = randColor;
        list = new ArrayList<Atom>();
        initScene();
    }

    /**
     * 必须要实现的初始场景方法，需要
     */
    protected abstract void initScene();

    public void draw(Canvas canvas) {
        if (list.size() == 0) {
            return;
        }
        for (AnimationInterface item : list) {
            item.draw(canvas);
        }
    }

    public void move() {
        if (list.size() == 0) {
            return;
        }
        if (isStopSlowly) {
            ArrayList<Atom> listTemp = new ArrayList<Atom>();
            for (int i = 0; i < list.size(); i++) {
                Atom item = list.get(i);
                item.setStopSlowly(isStopSlowly);
                if (!item.move()) {
                    listTemp.add(item);
                }
            }
            for (AnimationInterface animationInterface : listTemp) {
                list.remove(animationInterface);
            }
        } else {
            for (AnimationInterface item : list) {
                item.move();
            }
        }
    }

    public void setStopSlowly(boolean stopSlowly) {
        isStopSlowly = stopSlowly;
    }
}
