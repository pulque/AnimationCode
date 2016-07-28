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

/**
 * 原子接口类
 * Created by Norman.Li on 7/25/2016.
 */
public interface AnimationInterface {
    /**
     * 画图
     *
     * @param canvas 来着Surface view
     */
    void draw(Canvas canvas);

    /**
     * 计算下次绘图参数
     *
     * @return 是否进行了移动处理
     */
    boolean move();

    /**
     * 重置数据
     */
    void reset();
}
