package com.patterns2.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ShopService {

    public final List<Order> orders = new ArrayList<>();
    public final Authenticator authenticator;
    public final ProductService productService;

    @Autowired
    public ShopService(Authenticator authenticator, ProductService productService) {
        this.authenticator = authenticator;
        this.productService = productService;
    }

    public Long openOrder(Long userId) {
        if(authenticator.isAuthenticated(userId)) {
            long maxOrder = orders.stream()
                    .mapToInt(o -> o.getOrderId().intValue())
                    .max().orElse(0);
            orders.add(new Order(maxOrder + 1, userId, productService));
            return maxOrder + 1;
        } else {
            return -1L;
        }
    }

    public void addItem(Long orderId, Long productId, double qty) {
        orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .forEach(o -> o.getItems().add(new Item(productId, qty)));
    }

    public boolean removeItem(Long orderId, Long productId) {
        Iterator<Order> orderIterator = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .iterator();
        while (orderIterator.hasNext()) {
            Order theOrder = orderIterator.next();
            for (int n = 0; n < theOrder.getItems().size(); n++) {
                if (theOrder.getItems().get(n).getProductId().equals(productId)) {
                    theOrder.getItems().remove(n);
                    return true;
                }
            }
        }
        return false;
    }

    public BigDecimal calculateValue(Long orderId) {
        Optional<Order> theOrder = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findAny();
        if (theOrder.isPresent()) {
            return theOrder.get().calculateValue();
        }
        return BigDecimal.ZERO;
    }

    public boolean doPayment(Long orderId) {
        Optional<Order> theOrder = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findAny();
        if (theOrder.isPresent()) {
            if (theOrder.get().isPaid()) {
                return true;
            } else {
                Random generator = new Random();
                theOrder.get().setPaid(generator.nextBoolean());
                return theOrder.get().isPaid();
            }
        } else {
            return false;
        }
    }

    public boolean verifyOrder(Long orderId) {
        Iterator<Order> orderIterator = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .iterator();
        while (orderIterator.hasNext()) {
            Order theOrder = orderIterator.next();
            boolean result = theOrder.isPaid();
            Random generator = new Random();
            if (!theOrder.isVerified()) {
                theOrder.setVerified(result && generator.nextBoolean());
            }
            return theOrder.isVerified();
        }
        return false;
    }

    public boolean submitOrder(Long orderId) {
        Iterator<Order> orderIterator = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .iterator();
        while (orderIterator.hasNext()) {
            Order theOrder = orderIterator.next();
            if (theOrder.isVerified()) {
                theOrder.setSubmitted(true);
            }
            return theOrder.isSubmitted();
        }
        return false;
    }

    public void cancelOrder(Long orderId) {
        Iterator<Order> orderIterator = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .iterator();
        while (orderIterator.hasNext()) {
            Order theOrder = orderIterator.next();
            orders.remove(theOrder);
        }
    }

}
