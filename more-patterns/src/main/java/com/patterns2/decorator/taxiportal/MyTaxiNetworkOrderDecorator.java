package com.patterns2.decorator.taxiportal;

import java.math.BigDecimal;

public class MyTaxiNetworkOrderDecorator extends AbstractTaxiOrderDecorator{

    public MyTaxiNetworkOrderDecorator(TaxiOrder taxiOrder) {
        super(taxiOrder);
    }

    @Override
    public BigDecimal getCost() {
        //hardcoded stub cost = 30
        return super.getCost().add(BigDecimal.valueOf(30.00));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " by MyTaxiNetwork";
    }
}
