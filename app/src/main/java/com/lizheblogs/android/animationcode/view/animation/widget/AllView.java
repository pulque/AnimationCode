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

package com.lizheblogs.android.animationcode.view.animation.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.lizheblogs.android.animationcode.view.animation.base.Molecular;
import com.lizheblogs.android.animationcode.view.animation.base.SupWidget;
import com.lizheblogs.android.animationcode.view.animation.scene.PaperScene;
import com.lizheblogs.android.animationcode.view.animation.scene.RainScene;

/**
 * All View
 * 可已通过TYPE来控制添加不同场景
 * Created by Norman.Li on 7/25/2016.
 */
public class AllView extends SupWidget {

    public static final int PAPER = 0;
    public static final int RAIN = PAPER + 1;

    private int type;

    public AllView(Context context) {
        super(context);
    }

    public AllView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AllView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected Molecular initScene(int itemNum, int itemColor, boolean randColor) {
        int width = getWidth();
        int height = getHeight();
        switch (type) {
            case PAPER:
                return new PaperScene(width, height, itemNum, itemColor, randColor);
            case RAIN:
                return new RainScene(width, height, itemNum, itemColor, randColor);
            default:
                return new PaperScene(width, height, itemNum, itemColor, randColor);
        }
    }

    public void setViewType(int type) {
        this.type = type;
    }
}
