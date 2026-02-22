package de.tools400.removecolorcodes.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.tools400.removecolorcodes.Messages;
import de.tools400.removecolorcodes.RemoveColorCodesPlugin;
import de.tools400.removecolorcodes.preferences.Preferences;

public class PreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
    public PreferencesPage() {
        super();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(Messages.bind(Messages.Title_Remove_Color_Codes_A, RemoveColorCodesPlugin.getDefault().getVersion()));
    }

    public void createFieldEditors() {
        addField((FieldEditor)new BooleanFieldEditor(Preferences.IS_SHOW_ERROR_LOG, Messages.Label_Show_error_log, getFieldEditorParent()));
    }

    public void init(IWorkbench workbench) {
        setPreferenceStore(RemoveColorCodesPlugin.getDefault().getPreferenceStore());
    }
}