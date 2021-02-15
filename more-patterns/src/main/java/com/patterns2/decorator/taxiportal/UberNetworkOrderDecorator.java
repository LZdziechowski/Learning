package com.patterns2.decorator.taxiportal;

import java.math.BigDecimal;

public class UberNetworkOrderDecorator extends AbstractTaxiOrderDecorator {

    public UberNetworkOrderDecorator(TaxiOrder taxiOrder) {
        super(taxiOrder);
    }

    @Override
    public BigDecimal getCost() {
        //hardcoded stub cost = 20
        return super.getCost().add(BigDecimal.valueOf(20.00));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " by UberNetwork";
    }
}
