package dev.timofeev.orders.service;

import dev.timofeev.orders.dao.OrderDao;
import dev.timofeev.orders.domain.Order;
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
     */
    public Long createOrder(Order order) {
            return orderDao.createOrder(order).getId();
    }

}
