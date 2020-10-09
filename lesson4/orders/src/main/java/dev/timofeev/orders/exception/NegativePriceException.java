package dev.timofeev.orders.exception;


/**
 * Исключение для заказов с отрицательной ценой
 */
public class NegativePriceException extends Exception{

    public NegativePriceException() {
        super("Цена заказа меньше нуля");
    }

}
