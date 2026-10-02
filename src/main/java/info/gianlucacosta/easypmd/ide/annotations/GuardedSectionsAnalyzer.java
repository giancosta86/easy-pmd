package info.gianlucacosta.easypmd.ide.annotations;

import org.netbeans.api.editor.guards.GuardedSection;
import org.netbeans.api.editor.guards.GuardedSectionManager;
import org.openide.cookies.EditorCookie;
import org.openide.loaders.DataObject;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Analyzes the guarded sections in a document
 */
public class GuardedSectionsAnalyzer {

    private final Set<Integer> guardedLineNumbers = new HashSet<>();

    public GuardedSectionsAnalyzer(DataObject dataObject) {
        try {
            EditorCookie documentCookie = dataObject.getLookup().lookup(EditorCookie.class);
            StyledDocument document = documentCookie.openDocument();
            initialize(document);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public GuardedSectionsAnalyzer(StyledDocument document) {
        initialize(document);
    }

    private void initialize(StyledDocument document) {
        try {
            String fileContents = document.getText(0, document.getLength());
            if (fileContents.isEmpty()) {
                return;
            }

            GuardedSectionManager guardedSectionManager = GuardedSectionManager.getInstance(document);
            if (guardedSectionManager == null) {
                return;
            }

            Iterable<GuardedSection> guardedSections = guardedSectionManager.getGuardedSections();
            if (!guardedSections.iterator().hasNext()) {
                return;
            }

            int lastLineSeparatorIndex = -1;

            for (int lineNumber = 1;; lineNumber++) {
                int lineStartIndex = lastLineSeparatorIndex + 1;
                Position lineStartPosition = document.createPosition(lineStartIndex);

                for (GuardedSection guardedSection : guardedSections) {
                    if (guardedSection.contains(lineStartPosition, true)) {
                        guardedLineNumbers.add(lineNumber);
                        break;
                    }
                }

                lastLineSeparatorIndex = fileContents.indexOf("\n", lastLineSeparatorIndex + 1);
                if (lastLineSeparatorIndex == -1) {
                    break;
                }
            }

        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return the list of guarded line numbers. Line numbers are 1-based.
     */
    public Set<Integer> getGuardedLineNumbers() {
        return Collections.unmodifiableSet(guardedLineNumbers);
    }
}
