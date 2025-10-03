package Retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class GlobalRetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount;

    public GlobalRetryAnalyzer() {
        this.maxRetryCount = RetryConfig.getMaxRetries();
    }

    @Override
    public boolean retry(ITestResult result) {
        if (RetryConfig.isApplyGlobally() && retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
