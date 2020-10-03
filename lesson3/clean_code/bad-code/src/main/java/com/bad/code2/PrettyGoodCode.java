package com.bad.code2;

import com.bad.code2.shape.shape3D.Qube;
import com.bad.code2.shape.shape2D.Square;

public class PrettyGoodCode {
    public static void main(String... args) {
        Qube qube = new Qube(1d, 1d, 1d, 10d);
        System.out.println("Qube volume: " + qube.getVolume());

        Square square = new Square(1d, 1d, 5d);
        System.out.println("Square perimeter: " + square.getPerimeter());
    }
}
