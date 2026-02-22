package de.tools400.removecolorcodes.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import de.tools400.removecolorcodes.RemoveColorCodesPlugin;

public final class Preferences {

    private static Preferences instance;
    private static IPreferenceStore preferenceStore;

    private static final String ID = "de.tools400.removecolorcodes";
    public static final String IS_SHOW_ERROR_LOG = ID + ".isShowErrorLog";

    /**
     * Returns the one and only instance of the plug-in preferences.
     * 
     * @return singleton of the plug-in preferences
     */
    public static synchronized Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
            preferenceStore = RemoveColorCodesPlugin.getDefault().getPreferenceStore();
        }
        return instance;
    }

    /**
     * Returns <code>true</code> if the error log view is displayed on errors,
     * otherwise <code>false</code>.
     * 
     * @return Show error log enabled.
     */
    public boolean isShowErrorLog() {
        return preferenceStore.getBoolean(IS_SHOW_ERROR_LOG);
    }

    /**
     * Returns the default value of attribute
     * 'de.tools400.removecolorcodes.isShowErrorLog'.
     * 
     * @return show error log on error default value
     */
    private boolean getDefaultIsShowErrorLog() {
        return false;
    }

    /**
     * Initializes the preferences on startup of the plug-in.
     */
    public void initializeDefaultPreferences() {
        preferenceStore.setDefault(IS_SHOW_ERROR_LOG, getDefaultIsShowErrorLog());
    }
}