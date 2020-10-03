package com.bad.code2.shape.shape3D;

/**
 * Интерфейс трехмерной фигуры
 */
public interface Shape3D {
    /**
     * Возвращает координату X центра фигуры
     *
     * @return координата X центра фиргуры
     */
    Double getCenterX();

    /**
     * Возвращает координату Y центра фигуры
     *
     * @return координата Y центра фигуры
     */
    Double getCenterY();

    /**
     * Возвращает координату Z центра фигуры
     *
     * @return координата Z центра фигуры
     */
    Double getCenterZ();

    /**
     * Возвращает объем фигуры
     *
     * @return объем фигуры
     */
    Double getVolume();
}
