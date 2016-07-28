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
import com.lizheblogs.android.animationcode.view.animation.scene.RainScene;

/**
 * 下雨场景
 * Created by Norman.Li on 7/25/2016.
 */
public class RainView extends SupWidget {

    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected Molecular initScene(int itemNum, int itemColor, boolean randColor) {
        int width = getWidth();
        int height = getHeight();
        return new RainScene(width, height, itemNum, itemColor, randColor);
    }
}
