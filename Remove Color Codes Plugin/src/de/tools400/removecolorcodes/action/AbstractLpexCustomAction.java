/*******************************************************************************
 * Copyright (c) project_year-2026 Tools/400
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package de.tools400.removecolorcodes.action;

import com.ibm.lpex.core.LpexAction;

import de.tools400.removecolorcodes.utils.StringUtils;

public abstract class AbstractLpexCustomAction implements LpexAction {

    private static final String SEPARATOR = " ";

    private String id;
    private String name;
    private String label;

    public AbstractLpexCustomAction(String id, String name, String label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getActionString() {

        String actionString = getName() + SEPARATOR + getID();

        return actionString;
    }

    public String getMenuString() {

        String menuString = StringUtils.doubleQuotes(getLabel()) + SEPARATOR + getName();

        return menuString;
    }
}
