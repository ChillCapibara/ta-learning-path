package framework.listeners;

import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MdcTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // method name is usually enough; you can also include class
        String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        MDC.put("test", testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        MDC.clear();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        MDC.clear();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        MDC.clear();
    }

    @Override
    public void onFinish(ITestContext context) {
        MDC.clear();
    }
}
