package ui.base;

import ui.enums.WindowMode;

public class DriverOptions {

    private final boolean headless;
    private final boolean disableNotifications;
    private final WindowMode windowMode;
    private final Integer width;
    private final Integer height;

    private DriverOptions(Builder builder) {
        this.headless = builder.headless;
        this.disableNotifications = builder.disableNotifications;
        this.windowMode = builder.windowMode;
        this.width = builder.width;
        this.height = builder.height;
    }

    public static Builder builder(){
        return new Builder();
    }

    // getters
    public boolean isHeadless() { return headless; }
    public boolean isDisableNotifications() { return disableNotifications; }
    public WindowMode getWindowMode() { return windowMode; }
    public Integer getWidth() { return width; }
    public Integer getHeight() { return height; }

    // methods
    public boolean hasCustomSize() { return windowMode == WindowMode.CUSTOM; }


            // --- BUILDER --- //

    public static class Builder {
        private boolean headless = false;
        private boolean disableNotifications = true;
        private WindowMode windowMode = WindowMode.DEFAULT;
        private Integer width;
        private Integer height;

        public Builder headless(boolean value) {
            this.headless = value;
            return this;
        }

        public Builder disableNotifications(boolean value) {
            this.disableNotifications = value;
            return this;
        }

        public Builder maximize() {
            this.windowMode = WindowMode.MAXIMIZED;
            return this;
        }

        public Builder windowSize(int width, int height) {
            this.windowMode = WindowMode.CUSTOM;
            this.width = width;
            this.height = height;
            return this;
        }

        public DriverOptions build() {
            if (windowMode == WindowMode.CUSTOM &&
                    (width == null || height == null)) {
                throw new IllegalStateException(
                        "CUSTOM window mode requires width and height!");
            }
            return new DriverOptions(this);
        }
    }
}