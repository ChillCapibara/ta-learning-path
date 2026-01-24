package framework.listeners;

import framework.retry.RetryAnalyzer;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {


    private static final String DISABLED_RETRY_ANALYZER =
            "org.testng.internal.annotations.DisabledRetryAnalyzer";

    @Override
    public void transform(
            ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod) {
        Class<? extends IRetryAnalyzer> current = annotation.getRetryAnalyzerClass();

        boolean hasNoCustomRetry =
                current == null
                || current == IRetryAnalyzer.class
                || current.getName().equals(DISABLED_RETRY_ANALYZER);

        if (hasNoCustomRetry) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}

