import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocsKeyPressed extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Editor editor = FileEditorManager.getInstance(e.getProject()).getSelectedTextEditor();
        VirtualFile file = FileDocumentManager.getInstance().getFile(editor.getDocument());
        int offset = editor.getCaretModel().getOffset();

        PsiElement element = PsiManager.getInstance(e.getProject()).findFile(file).findElementAt(offset);

        Pattern p = Pattern.compile("^v-");
        Matcher m = p.matcher(element.getText());
        if(m.find()){
            BrowserUtil.browse("https://vuetifyjs.com/en/api/" + element.getText());
        }
    }
}
