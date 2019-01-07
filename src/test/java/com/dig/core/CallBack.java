package com.dig.core;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/* Some Junit extensions to execute BeforeAll/BeforeEach/AfterEach callbacks */
public class CallBack implements
        BeforeAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Object testInstance = extensionContext.getRequiredTestInstance();
        if (testInstance instanceof TestBase) {
            Class<?> testClass = extensionContext.getTestClass().isPresent() ? extensionContext.getTestClass().get() : getClass();
            ((TestBase) testInstance).initialize(LogManager.getLogger(testClass));
        }

    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Object testInstance = extensionContext.getRequiredTestInstance();

        if (testInstance instanceof TestBase) {
            TestBase instance = ((TestBase) testInstance);
            instance.getLogger().info(
                    () -> String.format("Executing test [%s]", extensionContext.getDisplayName())
            );
        }

    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Object testInstance = extensionContext.getRequiredTestInstance();
        if (testInstance instanceof TestBase) {
            TestBase instance = ((TestBase) testInstance);
            if (extensionContext.getExecutionException().isPresent()) {
                instance.getLogger().warn(
                        () -> String.format("Test Failure found for [%s]", extensionContext.getDisplayName())
                );
            }
        }
    }
}
