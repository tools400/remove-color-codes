package de.tools400.removecolorcodes;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "de.tools400.removecolorcodes.messages";

    public static String LpexPopupMenue_RemoveColorCodes;
    public static String Title_Remove_Color_Codes_A;
    public static String Label_Enable_menu_option;
    public static String Label_Show_error_log;
    public static String Title_Error;
    public static String Error_Message_A;
    public static String Error_Could_not_query_source_file_name_Error_A;
    public static String Error_File_name_not_available;
    public static String Message_Replaced_A_color_codes;

    static {
        NLS.initializeMessages("de.tools400.removecolorcodes.messages", Messages.class);
    }
}