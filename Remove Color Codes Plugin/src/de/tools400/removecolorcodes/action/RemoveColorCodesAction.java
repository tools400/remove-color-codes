package de.tools400.removecolorcodes.action;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;

import com.ibm.lpex.core.LpexView;

import de.tools400.removecolorcodes.Messages;
import de.tools400.removecolorcodes.RemoveColorCodesPlugin;
import de.tools400.removecolorcodes.utils.ExceptionUtils;
import de.tools400.removecolorcodes.utils.LpexUtils;
import de.tools400.removecolorcodes.utils.StringUtils;

public class RemoveColorCodesAction extends AbstractLpexCustomAction {

    private static final int[] COLOR_CODES = { 128, 129, 130, 31, 132, 4, 5, 6, 7, 136, 137, 9226, 138, 139, 140, 144, 145, 147, 148, 20, 21, 22, 150,
        23, 152, 153, 154, 26, 27, 155, 158 };

    private static final String ID = "de.tools400.removecolorcodes.action.RemoveColorCodesAction";
    private static final String NAME = "RemoveColorCodesAction";

    // EBCDIC color codes
    // Hex, Dec
    // x20, 32 = green
    // x21, 33 = green/inverse
    // x22, 34 = white
    // x23, 35 = white/inverse
    // x24, 36 = green/underline
    // x25, 37 = green/inverse/underline
    // x26, 38 = white/underline
    // x27, 39 = non-display 1
    // x28, 40 = red
    // x29, 41 = red/underline
    // x2A, 42 = red/blink
    // x2B, 43 = red/inverse/blink
    // x2C, 44 = red/underline
    // x2D, 45 = red/inverse/underline
    // x2E, 46 = red/blink/underline
    // x2F, 47 = non-display 2
    // x30, 48 = turquoise
    // x31, 49 = turquoise/inverse/colum separator
    // x32, 50 = yellow
    // x33, 51 = yellow/inverse/colum separator
    // x34, 52 = turquoise/underline
    // x35, 53 = turquoise/inverse/underline
    // x36, 54 = yellow/underline
    // x37, 54 = non-display 3
    // x38, 56 = pink
    // x39, 57 = pink/inverse
    // x3A, 58 = blue
    // x3B, 59 = blue/inverse
    // x3C, 60 = pink/underline
    // x3D, 61 = pink/inverse/underline
    // x3E, 62 = blue/underline
    // x3F, 63 = non-display 4

    private static final String SPACE = " ";

    private int countReplaced;
    private Set<Integer> colorCodes;

    public RemoveColorCodesAction() {
        super(ID, NAME, Messages.LpexPopupMenue_RemoveColorCodes);
    }

    /**
     * Indicates whether or not the action is available (= active). The action
     * considered to be available, if the view is not in read-only mode.
     * 
     * @param aLpexView - View the action is executed for.
     */
    public boolean available(LpexView aLpexView) {
        return !LpexUtils.isReadOnly(aLpexView);
    }

    /**
     * Executor method of the action. This method is executed, when the user
     * clicks the menu option "Remove Color Codes".
     */
    public void doAction(LpexView aLpexView) {

        if (aLpexView != null) {
            try {
                doRemoveColorCodes(aLpexView);
            } catch (Exception e) {
                String localizedMessage = ExceptionUtils.getLocalizedMessage(e);
                LpexUtils.displayMessage(aLpexView, localizedMessage);
                MessageDialog.openError(LpexUtils.getShell(aLpexView), Messages.Title_Error, localizedMessage);
            }
        } else {
            String tErrorMsg = "*** Lpex view is null. Can not remove color codes from file: " + LpexUtils.getFileName(aLpexView) + " ***";
            RemoveColorCodesPlugin.logError(tErrorMsg, null);
        }
    }

    /**
     * Internal executor of the action.
     * 
     * @param aLpexView - View the action is executed for.
     */
    private void doRemoveColorCodes(LpexView aLpexView) {
        int originalLineNumber = LpexUtils.getCursorLineNumber(aLpexView);
        int originalLinePosition = LpexUtils.getCursorPosition(aLpexView);

        countReplaced = 0;

        colorCodes = new HashSet<>();
        for (int i : COLOR_CODES) {
            colorCodes.add(i);
        }

        boolean hasChanged = false;
        int tLines = LpexUtils.getLinesCount(aLpexView);
        for (int tCurrentLineNumber = 1; tCurrentLineNumber <= tLines; tCurrentLineNumber++) {
            if (LpexUtils.isSourceLine(aLpexView, tCurrentLineNumber)) {
                if (removeColorCodes(aLpexView, tCurrentLineNumber)) {
                    hasChanged = true;
                }
            }
        }

        if (hasChanged
            && (LpexUtils.getCursorLineNumber(aLpexView) != originalLineNumber || LpexUtils.getCursorPosition(aLpexView) != originalLinePosition)) {
            LpexUtils.setCursorPosition(aLpexView, originalLineNumber, originalLinePosition);
        }

        LpexUtils.displayMessage(aLpexView, Messages.bind(Messages.Message_Replaced_A_color_codes, countReplaced));
    }

    /**
     * Removes the color codes of a given source line.
     * 
     * @param aLpexView - View the action is executed for.
     * @param aLineNumber - Line number of the source from which the color codes
     *        are removed.
     * @return
     */
    private boolean removeColorCodes(LpexView aLpexView, int aLineNumber) {

        String tSourceLineText = LpexUtils.getSourceLineText(aLpexView, aLineNumber);
        if (StringUtils.isNullOrEmpty(tSourceLineText) || tSourceLineText.trim().length() == 0) {
            return false;
        }

        boolean isChanged = false;
        StringBuilder tLine = null;

        for (int i = 0; i < tSourceLineText.length(); i++) {
            if (isColorCode(tSourceLineText, i)) {
                if (tLine == null) {
                    tLine = new StringBuilder(tSourceLineText);
                }
                tLine.replace(i, i + 1, SPACE);
                isChanged = true;
                countReplaced++;
            }
        }

        if (isChanged) {
            LpexUtils.setSourceLineText(aLpexView, aLineNumber, tLine.toString());
        }

        return isChanged;
    }

    /**
     * Returns <code>true</code>, if the specified byte value is a color code,
     * otherwise returns <code>false</code>.
     * 
     * @param aSourceLineText - Source line text from which the color codes are
     *        removed.
     * @param i - Byte offset of the current byte.
     * @return <code>true</code>, if the byte is a color code, otherwise
     *         <code>false</code>.
     */
    private boolean isColorCode(String aSourceLineText, int i) {

        int charAt = aSourceLineText.charAt(i);

        int type = Character.getType(aSourceLineText.charAt(i));

        if (type == Character.CONTROL || type == Character.OTHER_SYMBOL) {
            // if (colorCodes.contains(charAt)) {
            return true;
        } else {
            if (type != Character.SPACE_SEPARATOR) {
                // System.out.println("No control character: " + charAt + " at
                // index: " + i + ", type: " + type);
            }
        }

        return false;
    }
}