package Functions;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Clase que contiene los atributos y métodos del controlador
 * @author Jaime Salcedo Vallejo
 */
public class Controller extends Component implements ActionListener {

    private File file;

    public void actionPerformed(ActionEvent e){

    }

    //método para establecer el título del archivo en el propio marco del IDE
    public String setTitle(){
        String title;
        if(file != null){
            title = file.getName();
        }else{
            title = null;
        }
        return title;
    }

    //método para crear un nuevo archivo
    public void newFile(String text){
        int option = JOptionPane.showConfirmDialog(null, "¿Desea Guardar su archivo actual antes de crear uno nuevo?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            save(text);
            file = null;
        }else{
            file = null;
        }
    }

    //método para guardar archivo como
    public void saveAs(String text){
        JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Java files (*.java)", "java"));
            chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showSaveDialog(this);
        file = chooser.getSelectedFile();

        if(option == JFileChooser.APPROVE_OPTION){
            if(!file.getName().endsWith(".java")&&!file.getName().endsWith(".txt")){
                File javaFile = new File(file.getPath()+".java");
                file = javaFile;
            }
            if(file != null){
                if(!file.exists()){
                    try(FileWriter writer = new FileWriter(file)){
                        writer.write(text);
                        writer.close();
                        JOptionPane.showMessageDialog(null, "archivo guardado exitosamente");
                        setTitle();

                    }
                    catch (IOException ex){
                        JOptionPane.showMessageDialog(null, "error al guardar el archivo");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(null, "el archivo "+file.getName()+
                            " ya existe. Seleccione otro nombre");
                    file = null;
                }
            }
        }
    }

    //método para guardar un archivo ya existente
    public void save(String text){
        if(file != null){
            try {
                FileWriter writer = new FileWriter(file, false);
                writer.write(text);
                writer.close();
                setTitle();
                //JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            saveAs(text);
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
        file = chooser.getSelectedFile();

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

            if(file != null){
                int option = JOptionPane.showConfirmDialog(null,"¿Desea imprimir el archivo "+file.getName()+"?", "Imprimir archivo", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "el archivo "+file.getName()+" se imprimirá en breve");
                }else{
                    JOptionPane.showMessageDialog(null, "Cancelando operación");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Abra algún archivo o Guarde su archivo antes de imprimir");
            }
    }

    //metodo para ver información sobre esta aplicación
    public void infoPage() {
        String wd = System.getProperty("user.dir");
        try {
            Runtime.getRuntime().exec("cmd /c start chrome "+ wd + File.separator + "src"
                    + File.separator + "main" + File.separator + "resources" + File.separator + "information.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //metodo para ver una página web para ayuda
    public void helpPage(){
        Desktop link = Desktop.getDesktop();
        try {
            link.browse(new URI("https://www.youtube.com/watch?v=BBJa32lCaaY&ab_channel=LegacyPNDA"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    //método para compilar un archivo .java
    public void compileJavaFile(String text){
        save(text);
        if(file.getName().endsWith(".java")){
            try{
                Runtime.getRuntime().exec("cmd /c javac "+file.getAbsolutePath());
            }catch(Exception ex){
                System.out.println("ex: "+ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "el archivo no se trata de un archivo java");
        }
    }

    //método para poder ejecutar un programa .java
    public String runJavaFile(String text){
        String line = null;
        compileJavaFile(text);
        if(file.getName().endsWith(".java")){
            try{
                String runCommand = "cmd /c java "+file.getAbsolutePath();
                Process process = Runtime.getRuntime().exec(runCommand);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );
                line = br.readLine();
            }catch(Exception ex){
                System.out.println("ex: "+ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "seleccione un archivo java");
        }
        return line;
    }
}
