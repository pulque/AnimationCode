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
 * 帮助类，获取一个int值范围内的震荡数据
 * Created by Norman.Li on 7/28/2016.
 */
public class ChangeInLaw {

    private final int max;
    private final boolean isNegativeNumber;
    private final Random rand;

    private int variable;

    public ChangeInLaw(int max, boolean isNegativeNumber) {
        this.max = max;
        this.isNegativeNumber = isNegativeNumber;
        rand = new Random();
        rand.setSeed(rand.nextLong());
        variable = rand.nextInt(360);
    }

    public int getNum() {
        variable += 10;
        if (variable > Integer.MAX_VALUE - 1000) {
            variable = rand.nextInt(360);
        }
        int result = (int) (Math.sin(Math.toRadians(variable)) * max);
        if (isNegativeNumber)
            return result;
        else
            return Math.abs(result);
    }

    public int getNum(float d) {
        variable += (int) (d + 10);
        if (variable > Integer.MAX_VALUE - 1000) {
            variable = rand.nextInt(360);
        }
        int result = (int) (Math.sin(Math.toRadians(variable)) * max);
        if (isNegativeNumber)
            return result;
        else
            return Math.abs(result);
    }
}
