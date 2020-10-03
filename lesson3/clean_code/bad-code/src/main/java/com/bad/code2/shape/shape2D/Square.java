package com.bad.code2.shape.shape2D;

public class Square implements Shape2D{
    /**
     * Координата X центра квадрата
     */
    private Double centerX;
    /**
     * Координата Y центра квадрата
     */
    private Double centerY;
    /**
     * Длинна стороны квадрата
     */
    private Double edgeSize;

    /**
     * @param centerX координата X центра квадрата
     * @param centerY координата Y центра квадрата
     * @param edgeSize Длинна стороны квадрата
     */
    public Square(Double centerX, Double centerY, Double edgeSize) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.edgeSize = edgeSize;
    }


    /**
     * Возвращет координату X центра квадрата
     *
     * @return координата X центра квадрата
     */
    @Override
    public Double getCenterX() {
        return centerX;
    }

    /**
     * Возвращает координату Y центра квадрата
     *
     * @return координат Y центра квадрата
     */
    @Override
    public Double getCenterY() {
        return centerY;
    }

    /**
     * Возвращает площадь квадрата
     *
     * @return плоадь квадрата
     */
    @Override
    public Double getPerimeter() {
        return 4*edgeSize;
    }
}
