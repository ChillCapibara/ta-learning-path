package framework.retry;

import framework.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int attempt = 0;
    private final int maxRetries = Config.getInt("test.retry.max");
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < maxRetries){
            attempt++;
            log.warn(
                    "Retrying test {} (attempt {}/{})",
                    result.getMethod().getMethodName(),
                    attempt,
                    maxRetries
            );
            return true; // run again
        }
        return false; // final decision, test failed
    }
}
