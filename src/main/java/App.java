import javax.swing.*;
import java.util.logging.Level;

/**
 * Clase App que contiene el main y el método necesario para iniciar el IDE
 * @author Jaime Salcedo Vallejo
 */
public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana();
            }
        });
    }
}
