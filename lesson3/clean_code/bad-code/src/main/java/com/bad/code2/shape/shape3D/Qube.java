package com.bad.code2.shape.shape3D;

public class Qube implements Shape3D {
    /**
     * Координата X центра куба
     */
    private Double centerX;
    /**
     * Координата Y центра куба
     */
    private Double centerY;
    /**
     * Координата Z центра куба
     */
    private Double centerZ;
    /**
     * Длинна ребра куба
     */
    private Double edgeSize;

    /**
     * @param centerX координата X центра куба
     * @param centerY координата Y центра куба
     * @param centerZ координата Z центра куба
     * @param edgeSize длинна ребра куба
     */
    public Qube(Double centerX,  Double centerY, Double centerZ, Double edgeSize) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        this.edgeSize = edgeSize;
    }


    /**
     * Возвращает координату X центра куба
     *
     * @return координата X центра куба
     */
    @Override
    public Double getCenterX() {
        return centerX;
    }

    /**
     * Возвращает координату Y центра куба
     *
     * @return  координата Y центра куба
     */
    @Override
    public Double getCenterY() {
        return centerY;
    }

    /**
     * Возвращает координату Z центра куба
     *
     * @return координата Z центра куба
     */
    @Override
    public Double getCenterZ() {
        return centerZ;
    }

    /**
     * Возвращает объем куба
     *
     * @return объем куба
     */
    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}
