import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeleccionNivel extends JFrame {
    private JTextField nombreTextField;

    public SeleccionNivel() {
        setTitle("Seleccionar Nivel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        nombreTextField = new JTextField(20); 
        panel.add(new JLabel("Ingrese su nombre:"));
        panel.add(nombreTextField);

        JButton primerNivel = new JButton("Primer Nivel");
        primerNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarNivel("Primer Nivel");
            }
        });

        JButton segundoNivel = new JButton("Segundo Nivel");
        segundoNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarNivel("Segundo Nivel");
            }
        });

        JButton tercerNivel = new JButton("Tercer Nivel");
        tercerNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarNivel("Tercer Nivel");
            }
        });

        panel.add(primerNivel);
        panel.add(segundoNivel);
        panel.add(tercerNivel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        //pack();
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void seleccionarNivel(String nivel) {
        String nombreJugador = nombreTextField.getText();
        if (!nombreJugador.isEmpty()) {
            iniciarJuego(nivel);
            //seleccionarNivelDialog(nivel);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarJuego(String nivel) {
        Laberinto matriz = new Laberinto();
        LaberintoGUI laberinto = new LaberintoGUI(matriz, nivel, nombreTextField.getText());
        dispose();
    }
}
