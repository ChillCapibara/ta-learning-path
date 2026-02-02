package framework.stability;

public enum FailureReason {
    LOCATOR_NOT_FOUND,
    STALE_DOM,
    CLICK_BLOCKED,
    SPINNER_TIMEOUT,
    SYNC_TIMEOUT,
    ASSERTION_FAILED,
    ENV_DRIVER,
    UNKNOWN
}
