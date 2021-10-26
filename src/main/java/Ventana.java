import Functions.Controller;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Ventana extends JFrame {


    //panel principal
    private JPanel mainPanel;
    //UndoManager
    private UndoManager undoer;
    //portapapeles del sistema
    private Clipboard clipboard;
    //controlador
    Controller cont = new Controller();
    //componentes de la barra de menú
    private JMenuBar menu;
    private JMenu menuFile;
    private JMenuItem menuFileNew;
    private JMenuItem menuFileOpen;
    private JMenuItem menuFileSave;
    private JMenuItem menuFileSaveAs;
    private JMenuItem menuFilePrint;
    private JMenu menuEdit;
    private JMenuItem menuEditUndo;
    private JMenuItem menuEditRedo;
    private JMenuItem menuEditCopy;
    private JMenuItem menuEditPaste;
    private JMenuItem menuEditCut;
    private JMenuItem menuEditDelete;
    private JMenu menuHelp;
    private JMenuItem menuHelpInfo;
    private JMenuItem menuHelpHelp;
    private JMenu menuTools;
    private JMenuItem menuToolsRun;
    private JMenuItem menuToolsCompile;

    private JPanel panel1;
    private JPanel toolbar;
    private JButton runButton;
    private JButton stopButton;
    private JPanel center_area;
    private JPanel terminal;
    private JPanel output;
    private JTextArea outputTerminal;
    private JPanel TreePanel;
    private JPanel TextAreaPanel;
    private JTree tree1;
    private JTextArea textArea1;
    private JButton refactorButton;
    private JPanel rightToolbar;
    private JPanel leftToolbar;
    private JButton openFileButton;
    private JButton saveFileButton;

    String text = textArea1.getText();
    //constructor de la Ventana
    public Ventana(){
        undoer = new UndoManager();
        undoer.setLimit(2000);
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        initComponents();
    }
    public void initComponents(){
        //instanciación de componentes
        menu = new JMenuBar();
            menuFile = new JMenu("File");
                menuFileNew = new JMenuItem("New");
                menuFileOpen = new JMenuItem("Open");
                menuFileSave = new JMenuItem("Save");
                menuFileSaveAs = new JMenuItem("Save As");
                menuFilePrint = new JMenuItem("Print");
            menuEdit = new JMenu("Edit");
                menuEditUndo = new JMenuItem("Undo");
                menuEditRedo = new JMenuItem("Redo");
                menuEditCopy = new JMenuItem("Copy");
                menuEditPaste = new JMenuItem("Paste");
                menuEditCut = new JMenuItem("Cut");
                menuEditDelete = new JMenuItem("Delete");
            menuTools = new JMenu("Tools");
                menuToolsRun = new JMenuItem("Run");
                menuToolsCompile = new JMenuItem("Compile");
            menuHelp = new JMenu("Help");
                menuHelpInfo = new JMenuItem("Information");
                menuHelpHelp = new JMenuItem("Help");



        //creación del menú
        menu.setOpaque(true);
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.BLACK);
        menu.add(menuFile);
            menuFile.setOpaque(true);
            menuFile.setBackground(Color.BLACK);
            menuFile.setForeground(Color.WHITE);
            menuFile.add(menuFileNew);
                menuFileNew.setOpaque(true);
                menuFileNew.setBackground(Color.BLACK);
                menuFileNew.setForeground(Color.WHITE);
            menuFile.add(menuFileOpen);
                menuFileOpen.setOpaque(true);
                menuFileOpen.setBackground(Color.BLACK);
                menuFileOpen.setForeground(Color.WHITE);
            menuFile.add(menuFileSave);
                menuFileSave.setOpaque(true);
                menuFileSave.setBackground(Color.BLACK);
                menuFileSave.setForeground(Color.WHITE);
            menuFile.add(menuFileSaveAs);
                menuFileSaveAs.setOpaque(true);
                menuFileSaveAs.setBackground(Color.BLACK);
                menuFileSaveAs.setForeground(Color.WHITE);
            menuFile.add(menuFilePrint);
                menuFilePrint.setOpaque(true);
                menuFilePrint.setBackground(Color.BLACK);
                menuFilePrint.setForeground(Color.WHITE);
        menu.add(menuEdit);
            menuEdit.setOpaque(true);
            menuEdit.setBackground(Color.BLACK);
            menuEdit.setForeground(Color.WHITE);
            menuEdit.add(menuEditUndo);
                menuEditUndo.setOpaque(true);
                menuEditUndo.setBackground(Color.BLACK);
                menuEditUndo.setForeground(Color.WHITE);
            menuEdit.add(menuEditRedo);
                menuEditRedo.setOpaque(true);
                menuEditRedo.setBackground(Color.BLACK);
                menuEditRedo.setForeground(Color.WHITE);
            menuEdit.add(menuEditCopy);
                menuEditCopy.setOpaque(true);
                menuEditCopy.setBackground(Color.BLACK);
                menuEditCopy.setForeground(Color.WHITE);
            menuEdit.add(menuEditPaste);
                menuEditPaste.setOpaque(true);
                menuEditPaste.setBackground(Color.BLACK);
                menuEditPaste.setForeground(Color.WHITE);
            menuEdit.add(menuEditCut);
                menuEditCut.setOpaque(true);
                menuEditCut.setBackground(Color.BLACK);
                menuEditCut.setForeground(Color.WHITE);
            menuEdit.add(menuEditDelete);
                menuEditDelete.setOpaque(true);
                menuEditDelete.setBackground(Color.BLACK);
                menuEditDelete.setForeground(Color.WHITE);
        menu.add(menuTools);
            menuTools.setOpaque(true);
            menuTools.setBackground(Color.BLACK);
            menuTools.setForeground(Color.WHITE);
            menuTools.add(menuToolsRun);
                menuToolsRun.setOpaque(true);
                menuToolsRun.setBackground(Color.BLACK);
                menuToolsRun.setForeground(Color.WHITE);
            menuTools.add(menuToolsCompile);
                menuToolsCompile.setOpaque(true);
                menuToolsCompile.setBackground(Color.BLACK);
                menuToolsCompile.setForeground(Color.WHITE);
        menu.add(menuHelp);
            menuHelp.setOpaque(true);
            menuHelp.setBackground(Color.BLACK);
            menuHelp.setForeground(Color.WHITE);
            menuHelp.add(menuHelpInfo);
                menuHelpInfo.setOpaque(true);
                menuHelpInfo.setBackground(Color.BLACK);
                menuHelpInfo.setForeground(Color.WHITE);
            menuHelp.add(menuHelpHelp);
                menuHelpHelp.setOpaque(true);
                menuHelpHelp.setBackground(Color.BLACK);
                menuHelpHelp.setForeground(Color.WHITE);

        //action listeners
        //action listener para crear un nuevo archivo
        menuFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea1.getText();
                cont.newFile(text);
                textArea1.setText(null);
                setTitle(null);
            }
        });

        menuFileSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea1.getText();
                cont.saveAs(text);
                setTitle(cont.setTitle());

            }
        });

        //action listener para guardar un archivo
        menuFileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea1.getText();
                cont.save(text);
                setTitle(cont.setTitle());
            }
        });

        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea1.getText();
                cont.save(text);
                setTitle(cont.setTitle());
            }
        });

        //action listener para abrir un archivo
        menuFileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = cont.openFile();
                textArea1.setText(text);
                setTitle(cont.setTitle());
            }
        });
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = cont.openFile();
                textArea1.setText(text);
                setTitle(cont.setTitle());
            }
        });

        //conjunto de métodos para añadir la funcionalidad hacer/deshacer
        //añadimos el EditListener al textArea
        textArea1.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoer.addEdit(e.getEdit());
            }
        });
        //action listener para deshacer cambios
        menuEditUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoer.canUndo()){
                    undoer.undo();
                }
            }
        });
        //action listener para rehacer cambios
        menuEditRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoer.canRedo()){
                    undoer.redo();
                }
            }
        });

        //action listener para copiar
        menuEditCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea1.getSelectedText() != null){
                    StringSelection selection = new StringSelection(""+textArea1.getSelectedText());
                    clipboard.setContents(selection, selection);
                }
            }
        });

        //action listener para pegar
        menuEditPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transferable datos = clipboard.getContents(null);
                if (datos != null && datos.isDataFlavorSupported(DataFlavor.stringFlavor)){
                    try {
                        textArea1.replaceSelection(""+datos.getTransferData(DataFlavor.stringFlavor));
                    } catch (UnsupportedFlavorException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //action listener para cortar
        menuEditCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea1.getSelectedText() != null){
                    StringSelection selection = new StringSelection(""+textArea1.getSelectedText());
                    clipboard.setContents(selection, selection);
                    textArea1.replaceSelection("");
                }
            }
        });

        //action listener para eliminar
        menuEditDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea1.getSelectedText() != null){
                    int option = JOptionPane.showConfirmDialog(null, "¿Deseas Eliminar?",
                            "Confirmar eliminar contenido",JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        textArea1.replaceSelection("");
                    }
                }
            }
        });

        /*textArea1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }


            @Override
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == 9){

                }
            }
        });*/

        menuFilePrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont.printFile();
            }
        });

        //action listener para ver página de información
        menuHelpInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont.infoPage();
            }
        });

        //action listener para ver página de ayuda
        menuHelpHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont.helpPage();
            }
        });

        //action listener para ejecución de .java
        menuToolsRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont.runJavaFile();
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont.runJavaFile();
            }
        });

        //añadir componentes a la ventana
        this.setJMenuBar(menu);
        this.add(panel1);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(getMaximumSize()));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(getToolkit().getImage(getClass().getResource("/icon3.png")));
        this.pack();

    }

}

