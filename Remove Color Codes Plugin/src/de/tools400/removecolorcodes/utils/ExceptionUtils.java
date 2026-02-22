/*******************************************************************************
 * Copyright (c) project_year-2026 Tools/400
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package de.tools400.removecolorcodes.utils;

public class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static String getLocalizedMessage(Throwable throwable) {

        String message = throwable.getLocalizedMessage();
        if (message == null || message.trim().length() == 0) {
            return throwable.getClass().getName();
        } else {
            return throwable.getLocalizedMessage();
        }
    }
}
