package com.patterns2.decorator.taxiportal;

import java.math.BigDecimal;

public class BasicTaxiOrder implements TaxiOrder {

    @Override
    public BigDecimal getCost() {
        return BigDecimal.valueOf(5.00);
    }

    @Override
    public String getDescription() {
        return "Drive a course";
    }
}
