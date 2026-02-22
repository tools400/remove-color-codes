package de.tools400.removecolorcodes.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

public class PreferenceInitializer extends AbstractPreferenceInitializer {
    public void initializeDefaultPreferences() {
        Preferences.getInstance().initializeDefaultPreferences();
    }
}