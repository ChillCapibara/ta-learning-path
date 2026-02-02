package framework.stability;

public final class WebRetryPolicy {
    private WebRetryPolicy(){}

    public static boolean isRetryable(FailureReason reason){
        return reason == FailureReason.STALE_DOM
                || reason == FailureReason.CLICK_BLOCKED
                || reason == FailureReason.SPINNER_TIMEOUT;
    }
}
