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
        Optional<Order> theOrder = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findAny();
        if (theOrder.isPresent()) {
            Optional<Item> theItem = theOrder.get().getItems().stream()
                    .filter(o -> o.getProductId().equals(productId))
                    .findAny();
            if (theItem.isPresent()) {
                theOrder.get().getItems().remove(theItem.get());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
        Optional<Order> theOrder = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findAny();
        if (theOrder.isPresent()) {
            boolean result = theOrder.get().isPaid();
            Random generator = new Random();
            if (!theOrder.get().isVerified()) {
                theOrder.get().setVerified(result && generator.nextBoolean());
            }
            return theOrder.get().isVerified();
        } else {
            return false;
        }
    }

    public boolean submitOrder(Long orderId) {
        Optional<Order> theOrder = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findAny();
        if (theOrder.isPresent() && theOrder.get().isVerified()) {
            theOrder.get().setSubmitted(true);
            return theOrder.get().isSubmitted();
        } else {
            return false;
        }
    }

    public void cancelOrder(Long orderId) {
        Optional<Order> theOrder = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findAny();
        theOrder.ifPresent(orders::remove);
    }
}
