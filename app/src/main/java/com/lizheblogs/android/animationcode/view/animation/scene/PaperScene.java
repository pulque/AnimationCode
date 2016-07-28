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

package com.lizheblogs.android.animationcode.view.animation.scene;

import com.lizheblogs.android.animationcode.view.animation.base.Molecular;
import com.lizheblogs.android.animationcode.view.animation.item.PaperItem;

/**
 * 实现分子，添加原子
 * Created by Norman.Li on 7/25/2016.
 */
public class PaperScene extends Molecular {
    /**
     * @param width     surface view width
     * @param height    surface view height
     * @param itemNum   原子的数量
     * @param itemColor 原子的颜色
     * @param randColor if true,itemColor Invalid
     */
    public PaperScene(int width, int height, int itemNum, int itemColor, boolean randColor) {
        super(width, height, itemNum, itemColor, randColor);
    }

    @Override
    protected void initScene() {
        for (int i = 0; i < itemNum; i++) {
            list.add(new PaperItem(width, height, itemColor, randColor));
        }
    }
}
