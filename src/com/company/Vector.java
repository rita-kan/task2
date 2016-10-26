package com.company;

import java.io.Serializable;

/**
 * Created by 1 on 24.10.2016.
 */
public class Vector extends Matrix implements Serializable {

    public Vector(int[] data) {
        super(1, data.length);
        for (int i = 0; i < data.length; i++)
            this.data[0][i] = data[i];
    }

    public Vector(int length) {
        super(1, length);
    }


}
