package com.dig.core;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(CallBack.class)

/* Base test class to initialize tests with Logger dynamically */
public class TestBase {

    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void initialize(Logger logger) {
        this.logger = logger;
    }
}
