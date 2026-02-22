/*******************************************************************************
 * Copyright (c) project_year-2026 Tools/400
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package de.tools400.removecolorcodes.utils;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public final class UIUtils {
    private UIUtils() {
    }

    public static Shell getActiveShell() {

        IWorkbenchWindow activeWorkbenchWindow = getActiveWorkbenchWindow();
        if (activeWorkbenchWindow == null) {
            return null;
        }

        Shell shell = activeWorkbenchWindow.getShell();

        return shell;
    }

    public static IWorkbenchPage getActivePage() {

        IWorkbenchWindow activeWorkbenchWindow = getActiveWorkbenchWindow();
        if (activeWorkbenchWindow == null) {
            return null;
        }

        IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();

        return activePage;
    }

    public static IWorkbenchWindow getActiveWorkbenchWindow() {

        IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench == null) {
            return null;
        }

        IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();

        return activeWorkbenchWindow;
    }
}
