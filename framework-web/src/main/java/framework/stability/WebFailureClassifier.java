package framework.stability;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.UnreachableBrowserException;

public final class WebFailureClassifier {
    private WebFailureClassifier() {}


    public static FailureReason classify(Throwable t) {
        if (t == null) return FailureReason.UNKNOWN;

        if (t instanceof NoSuchElementException) return FailureReason.LOCATOR_NOT_FOUND;
        if (t instanceof StaleElementReferenceException) return FailureReason.STALE_DOM;
        if (t instanceof ElementClickInterceptedException) return FailureReason.CLICK_BLOCKED;
        if (t instanceof AssertionError) return FailureReason.ASSERTION_FAILED;

        if (t instanceof TimeoutException) {
            String msg = t.getMessage();
            if (msg != null && msg.contains("[SPINNER_TIMEOUT]")) return FailureReason.SPINNER_TIMEOUT;
            return FailureReason.SYNC_TIMEOUT;
        }

        // common env/driver/session failures
        if (t instanceof SessionNotCreatedException) return FailureReason.ENV_DRIVER;
        if (t instanceof NoSuchSessionException) return FailureReason.ENV_DRIVER;
        if (t instanceof UnreachableBrowserException) return FailureReason.ENV_DRIVER;
        if (t instanceof WebDriverException) {
            String msg = t.getMessage();
            if (msg != null) {
                String m = msg.toLowerCase();
                if (m.contains("invalid session id")
                        || m.contains("disconnected")
                        || m.contains("chrome not reachable")
                        || m.contains("session deleted")
                        || m.contains("unable to establish websocket")) {
                    return FailureReason.ENV_DRIVER;
                }
            }
        }

        return FailureReason.UNKNOWN;
    }
}
