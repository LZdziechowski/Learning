package com.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class CornDecorator extends AbstractPizzaOrderDecorator {

    public CornDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal("2"));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\n+ corn";
    }
}
