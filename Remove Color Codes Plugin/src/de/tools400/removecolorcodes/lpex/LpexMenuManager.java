/*******************************************************************************
 * Copyright (c) project_year-2026 Tools/400
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package de.tools400.removecolorcodes.lpex;

import java.util.LinkedList;
import java.util.List;

import de.tools400.removecolorcodes.action.AbstractLpexCustomAction;
import de.tools400.removecolorcodes.action.RemoveColorCodesAction;
import de.tools400.removecolorcodes.utils.LpexUtils;
import de.tools400.removecolorcodes.utils.StringUtils;

public final class LpexMenuManager {

    private static final String MENU_MARKER_START = "MARK-de.tools400.removecolorcodes.start";
    private static final String MENU_MARKER_END = "MARK-de.tools400.removecolorcodes.end";
    private static final String SEPARATOR = " ";

    /**
     * The instance of this Singleton class.
     */
    private static LpexMenuManager instance;

    /**
     * Private constructor to ensure the Singleton pattern.
     */
    private LpexMenuManager() {
    }

    /**
     * Thread-safe method that returns the instance of this Singleton class.
     */
    public synchronized static LpexMenuManager getInstance() {
        if (instance == null) {
            instance = new LpexMenuManager();
        }
        return instance;
    }

    public void installPluginExtensions() {

        List<AbstractLpexCustomAction> userActions = getUserActions();

        uninstallPluginExtensions();

        installUserActions(userActions);
        installPopupMenuItems(userActions);
    }

    public void uninstallPluginExtensions() {

        List<AbstractLpexCustomAction> userActions = getUserActions();

        unistallUserActions(userActions);
        uninstallPopupMenuItems();
    }

    private void installUserActions(List<AbstractLpexCustomAction> userActions) {

        String existingUserActions = LpexUtils.getUserActions();

        StringBuilder newUserActions = new StringBuilder(existingUserActions);
        newUserActions.append(buildUserActions(userActions));

        LpexUtils.setUserActions(newUserActions.toString());
    }

    private void unistallUserActions(List<AbstractLpexCustomAction> userActions) {

        String existingUserActions = LpexUtils.getUserActions();

        StringBuilder newUserActions = new StringBuilder(existingUserActions);
        for (AbstractLpexCustomAction lpexAction : userActions) {
            String actionToRemove = lpexAction.getActionString();
            int startIndex = newUserActions.indexOf(actionToRemove);
            if (startIndex >= 0) {
                newUserActions.replace(startIndex, startIndex + actionToRemove.length(), "");
            }
        }

        LpexUtils.setUserActions(newUserActions.toString());
    }

    private void installPopupMenuItems(List<AbstractLpexCustomAction> userActions) {

        String existingPopupMenu = LpexUtils.getPopMenu();

        StringBuilder newPopupMenu = new StringBuilder(existingPopupMenu);
        newPopupMenu.append(buildPopupMenuItems(userActions));

        LpexUtils.setPopupMenu(newPopupMenu.toString());
    }

    private void uninstallPopupMenuItems() {

        String newPopupMenu = LpexUtils.getPopMenu();

        int startIndex = newPopupMenu.indexOf(MENU_MARKER_START);
        int endIndex = newPopupMenu.indexOf(MENU_MARKER_END);

        if (startIndex >= 0 && endIndex >= 0) {
            endIndex = endIndex + MENU_MARKER_END.length();
            String popupMenuLeft = StringUtils.trimR(newPopupMenu.substring(0, startIndex));
            String popupMenuRight = StringUtils.trimL(newPopupMenu.substring(endIndex));
            newPopupMenu = popupMenuLeft + SEPARATOR + popupMenuRight;
        }

        LpexUtils.setPopupMenu(newPopupMenu);
    }

    private String buildUserActions(List<AbstractLpexCustomAction> userActions) {

        StringBuilder newUserActions = new StringBuilder();

        for (AbstractLpexCustomAction userAction : userActions) {
            newUserActions.append(SEPARATOR);
            newUserActions.append(userAction.getActionString());
        }

        return newUserActions.toString();
    }

    private String buildPopupMenuItems(List<AbstractLpexCustomAction> userActions) {

        StringBuilder popupMenuItems = new StringBuilder();

        popupMenuItems.append(SEPARATOR);
        popupMenuItems.append(MENU_MARKER_START);

        for (AbstractLpexCustomAction userAction : userActions) {
            popupMenuItems.append(SEPARATOR);
            popupMenuItems.append(userAction.getMenuString());
        }

        popupMenuItems.append(SEPARATOR);
        popupMenuItems.append(MENU_MARKER_END);

        return popupMenuItems.toString();
    }

    private List<AbstractLpexCustomAction> getUserActions() {

        List<AbstractLpexCustomAction> lpexActions = new LinkedList<AbstractLpexCustomAction>();
        lpexActions.add(new RemoveColorCodesAction());

        return lpexActions;
    }
}
