import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Inicio extends JFrame {
    private JPanel pNombres;

    public Inicio(){
        setTitle("Juego del Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        pNombres = new JPanel();
        pNombres.setLayout(new BoxLayout(pNombres, BoxLayout.Y_AXIS));

        JButton iniciarJuegoB = new JButton("Iniciar Juego");
        iniciarJuegoB.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    SeleccionNivel seleccionar = new SeleccionNivel();
                    seleccionar.setVisible(true);
                    dispose();
                }
            });

        JButton borrarTodo = new JButton("Borrar Nombres");
        borrarTodo.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    pNombres.removeAll();
                    pNombres.revalidate();
                    pNombres.repaint();
                    guardarPuntajes("");
                }
            });

        JPanel pBotones = new JPanel();
        pBotones.add(iniciarJuegoB);
        pBotones.add(borrarTodo);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(pNombres), BorderLayout.CENTER);
        getContentPane().add(pBotones, BorderLayout.NORTH);

        cargarPuntajes();

        pack();
        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cargarPuntajes(){
        try{
            FileReader fr = new FileReader("puntajes.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null){
                borrarPuntajeJugador(linea);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void borrarPuntajeJugador(String nombre){
        JPanel pNombres2 = new JPanel();
        pNombres2.setLayout(new BorderLayout());
        pNombres2.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));

        JLabel lNombres = new JLabel(nombre);
        pNombres2.add(lNombres, BorderLayout.CENTER);

        JButton borrarJ = new JButton();
        ImageIcon icoBasurero = new ImageIcon("1.png");
        Image imagen = icoBasurero.getImage();
        Image nImagen = imagen.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        borrarJ.setIcon(new ImageIcon(nImagen));
        borrarJ.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    pNombres.remove(pNombres2);
                    pNombres.revalidate();
                    pNombres.repaint();
                    String nombres = obtenerNombresRestantes();
                    guardarPuntajes(nombres);
                }
            });
        pNombres2.add(borrarJ, BorderLayout.EAST);
        pNombres.add(pNombres2);
        pNombres.revalidate();
        pNombres.repaint(); 
    }

    private String obtenerNombresRestantes(){
        StringBuilder nombres = new StringBuilder();
        Component[] panelesNombres = pNombres.getComponents();
        for(Component componente : panelesNombres){
            if(componente instanceof JPanel){
                JPanel pNombres2 = (JPanel) componente;
                JLabel label = (JLabel) pNombres2.getComponent(0);
                nombres.append(label.getText()).append("\n");                
            }
        }
        return nombres.toString();
    }

    private void guardarPuntajes(String nombres){
        try{
            FileWriter fw = new FileWriter("puntajes.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nombres);
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Inicio listaPuntajes = new Inicio();
                }
            });
    }
}