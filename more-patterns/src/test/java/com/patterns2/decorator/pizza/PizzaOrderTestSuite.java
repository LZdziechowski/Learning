package com.patterns2.decorator.pizza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PizzaOrderTestSuite {

    @Test
    void testPizzaWithCornAndHamCost() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new CornDecorator(theOrder);
        theOrder = new HamDecorator(theOrder);
        System.out.println(theOrder.getCost());
        //When
        BigDecimal theCost = theOrder.getCost();
        //Then
        assertEquals(new BigDecimal("20"), theCost);
    }

    @Test
    void testPizzaWithCornAndHamDescription() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new CornDecorator(theOrder);
        theOrder = new HamDecorator(theOrder);
        System.out.println(theOrder.getDescription());
        //When
        String theDescription = theOrder.getDescription();
        //Then
        assertEquals("Dough and tomato sauce with cheese\n+ corn\n+ ham", theDescription);
    }

    @Test
    void testPizzaWithExtraCheeseAndMushroomsCost() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new ExtraCheeseDecorator(theOrder);
        theOrder = new MushroomsDecorator(theOrder);
        System.out.println(theOrder.getCost());
        //When
        BigDecimal theCost = theOrder.getCost();
        //Then
        assertEquals(new BigDecimal("21"), theCost);
    }

    @Test
    void testPizzaWithExtraCheeseAndMushroomsDescription() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new ExtraCheeseDecorator(theOrder);
        theOrder = new MushroomsDecorator(theOrder);
        System.out.println(theOrder.getDescription());
        //When
        String theDescription = theOrder.getDescription();
        //Then
        assertEquals("Dough and tomato sauce with cheese\n+ extra cheese\n+ mushrooms", theDescription);
    }
}
