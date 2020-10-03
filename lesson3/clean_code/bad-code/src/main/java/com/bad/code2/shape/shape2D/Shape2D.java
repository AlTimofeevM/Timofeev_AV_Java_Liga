package com.bad.code2.shape.shape2D;

/**
 * Интерфейс двухмерной фигуры
 */
public interface Shape2D {

    /**
     * Возвращает координату X центра фигуры
     *
     * @return координата X центра фиргуры
     */
    Double getCenterX();

    /**
     * Возвращает координату Y центра фигуры
     *
     * @return коордната Y центра фигуры
     */
    Double getCenterY();

    /**
     * Возвращет периметр фигуры
     *
     * @return периметр фигуры
     */
    Double getPerimeter();
}
