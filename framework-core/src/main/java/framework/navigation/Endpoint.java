package framework.navigation;

import framework.config.Config;

public enum Endpoint {

    MY_ACCOUNT("/my-account"),
    CART("/basket/");

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }

    public String url(Object... args) {
        String relative = args == null || args.length == 0 ? path : String.format(path, args);
        return Urls.join(Config.get("base.url"), relative);
    }
}
