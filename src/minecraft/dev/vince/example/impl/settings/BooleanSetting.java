package dev.vince.example.impl.settings;

import dev.vince.example.api.settings.Setting;

public final class BooleanSetting extends Setting {
    private boolean enabled;

    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.enabled = defaultValue;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getValue() {
        return enabled;
    }

    @Override
    public Object getValueObject() {
        return enabled;
    }
}
