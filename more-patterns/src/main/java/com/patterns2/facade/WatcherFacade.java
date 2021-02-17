package com.patterns2.facade;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WatcherFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherFacade.class);
    private static int counter = 1;

    @Before("execution(public * com.patterns2.facade.api.OrderFacade.processOrder(..))")
    public void logEvent() {
        LOGGER.info("processOrder() method call number: " + counter);
        counter++;
    }
}
