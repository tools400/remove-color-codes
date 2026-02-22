package de.tools400.removecolorcodes.lpex;

import com.ibm.lpex.alef.LpexPreload;

public class Preload implements LpexPreload {

    public void preload() {

        LpexMenuManager.getInstance().installPluginExtensions();
    }
}