package org.jabref.gui.exporter;

import org.jabref.gui.DialogService;
import org.jabref.gui.Globals;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.LibraryTab;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.logic.exporter.GlobalSaveManager;
import org.jabref.logic.l10n.Localization;

public class SaveAllAction extends SimpleCommand {

    private final JabRefFrame frame;
    private final DialogService dialogService;

    public SaveAllAction(JabRefFrame frame) {
        this.frame = frame;
        this.dialogService = frame.getDialogService();
    }

    @Override
    public void execute() {
        dialogService.notify(Localization.lang("Saving all libraries..."));

        for (LibraryTab libraryTab : frame.getLibraryTabs()) {
            SaveDatabaseAction saveDatabaseAction = new SaveDatabaseAction(libraryTab, Globals.prefs, Globals.entryTypesManager, GlobalSaveManager.INSTANCE);
            boolean saveResult = saveDatabaseAction.save();
            if (!saveResult) {
                dialogService.notify(Localization.lang("Could not save file."));
            }
        }

        dialogService.notify(Localization.lang("Save all finished."));
    }
}
