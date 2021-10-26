package Functions;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Controller extends Component implements ActionListener {

    public void actionPerformed(ActionEvent e){

    }

    //método para guardar archivo
    public void saveAs(String text){
        JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Java files (*.java)", "java"));
            chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showSaveDialog(this);
        File file = chooser.getSelectedFile();

        if(option == JFileChooser.APPROVE_OPTION){
            if(!file.getName().endsWith(".java")&&!file.getName().endsWith(".txt")){
                File javaFile = new File(file.getPath()+".java");
                file = javaFile;
            }
            if(file != null){
                if(!file.exists()){
                    try(FileWriter writer = new FileWriter(file)){
                        writer.write(text);
                        JOptionPane.showMessageDialog(null, "archivo guardado exitosamente");

                    }
                    catch (IOException ex){
                        JOptionPane.showMessageDialog(null, "error al guardar el archivo");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(null, "el archivo "+file.getName()+
                            " ya existe. Seleccione otro nombre");
                }
            }
        }
    }
    //metodo para abrir un archivo
    public String openFile(){
        String result = null;
        JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Java files (*.java)", "java"));
            chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();

        if(option == JFileChooser.APPROVE_OPTION){
            StringBuilder sb = new StringBuilder();
            try {
                FileReader freader = new FileReader(file);
                BufferedReader breader = new BufferedReader(freader);
                String line = breader.readLine();
                while(line != null){
                    sb.append(line + "\n");
                    line = breader.readLine();
                }
                result = sb.toString();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "error al abrir el archivo");
            }
        }
        return result;
    }

    //metodo para "imprimir" el archivo
    public void printFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Java files (*.java)", "java"));
        chooser.setAcceptAllFileFilterUsed(false);
        int choose = chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if(choose == JFileChooser.APPROVE_OPTION){
            if(file != null){
                int option = JOptionPane.showConfirmDialog(null,"¿Desea imprimir el archivo "+file.getName()+"?", "Imprimir archivo", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "el archivo "+file.getName()+" se imprimirá en breve");
                }else{
                    JOptionPane.showMessageDialog(null, "Cancelando operación");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Cancelando operación");
        }

    }
    //metodo para deshacer cambios
    //metodo para ver información sobre esta aplicación
    public void infoShow(){
        JOptionPane.showMessageDialog(null, "Esta aplicación fue creada por Jaime Salcedo Vallejo"
                +"\n"+"Lenguaje usado: Java");
    }
    //metodo para ver una página web para ayuda
    public void helpPage(){
        JOptionPane.showMessageDialog(null, "<html><a href=\"http://google.com/\">Página web de ayuda</a></html>");
    }
}
