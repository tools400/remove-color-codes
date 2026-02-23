package de.tools400.removecolorcodes;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import de.tools400.removecolorcodes.lpex.LpexMenuManager;
import de.tools400.removecolorcodes.preferences.Preferences;
import de.tools400.removecolorcodes.utils.UIUtils;

public class RemoveColorCodesPlugin extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "de.tools400.removecolorcodes";

    private static RemoveColorCodesPlugin plugin;

    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    public void stop(BundleContext context) throws Exception {

        LpexMenuManager.getInstance().uninstallPluginExtensions();

        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the instance of this plug-in.
     * 
     * @return plug-in instance
     */
    public static RemoveColorCodesPlugin getDefault() {
        return plugin;
    }

    /**
     * Convenience method to log error messages to the application log.
     * 
     * @param message Message
     * @param e The exception that has produced the error
     */
    public static void logError(String message, Throwable e) {

        String errorMessage = Messages.bind(Messages.Error_Message_A, message);
        if (plugin == null) {
            System.err.println(errorMessage);
            if (e != null) {
                e.printStackTrace();
            }
            return;
        }
        plugin.getLog().log(new Status(Status.ERROR, PLUGIN_ID, Status.ERROR, errorMessage, e));
        showErrorLog(false);
    }

    /**
     * Returns <code>true</code> if the error log is displayed on any kind of
     * errors, otherwise <code>false</code>.
     * 
     * @param Status, whether or not the error log is displayed on errors.
     */
    private static void showErrorLog(final boolean logError) {

        if (!Preferences.getInstance().isShowErrorLog()) {
            return;
        }

        UIJob job = new UIJob("") {

            @Override
            public IStatus runInUIThread(IProgressMonitor arg0) {

                try {

                    final String ERROR_LOG_VIEW = "org.eclipse.pde.runtime.LogView";

                    IWorkbenchPage activePage = UIUtils.getActivePage();
                    if (activePage != null) {
                        if (activePage.findView(ERROR_LOG_VIEW) == null) {
                            activePage.showView(ERROR_LOG_VIEW);
                        }
                    }

                } catch (Throwable e) {
                    MessageDialog.openError(UIUtils.getActiveShell(), Messages.Title_Error, e.getLocalizedMessage());
                    logError("Could not open error log view", e);
                }

                return Status.OK_STATUS;
            }
        };

        job.schedule();
    }

    /**
     * Returns the version of the plug-in, as assigned to "Bundle-Version" in
     * "MANIFEST.MF".
     * 
     * @return Version of the plug-in.
     */
    public String getVersion() {
        String version = (String)getBundle().getHeaders().get(Constants.BUNDLE_VERSION);
        if (version == null) {
            version = "0.0.0";
        }

        return version;
    }
}