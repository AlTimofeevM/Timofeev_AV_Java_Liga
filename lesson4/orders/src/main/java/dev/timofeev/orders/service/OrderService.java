package dev.timofeev.orders.service;

import dev.timofeev.orders.dao.OrderDao;
import dev.timofeev.orders.domain.Order;
import dev.timofeev.orders.exception.NegativePriceException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с заказами
 */
@Service
public class OrderService {

    /**
     * DAO для работы с заказами
     */
    @Autowired
    private OrderDao orderDao;

    /**
     * Сохраняет сделанный заказ
     *
     * @param order сделанный заказ
     * @return индификатор сделанного заказа
     * @throws NegativePriceException У заказа отрицательная цена
     */
    public Long createOrder(Order order) throws NegativePriceException {
        if(order.getPrice() >= 0) {
            Long id = orderDao.createOrder(order);
            return id;
        } else {
            throw new NegativePriceException();
        }
    }

}
