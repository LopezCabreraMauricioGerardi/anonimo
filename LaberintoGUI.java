
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LaberintoGUI extends JFrame implements KeyListener {

    private Laberinto matriz; // Campo para almacenar la referencia a MatrizLab
    private String[][] matrizLaberinto;
    private JPanel panelLaberinto;// Panel para el laberinto
    private JPanel panelPuntaje; // Nuevo panel para mostrar el puntaje
    private JLabel puntajeLabel; // Etiqueta para mostrar el puntaje
    private int puntaje = 0;
    private String nombreJugador; // Variable para almacenar el nombre del jugador

    public LaberintoGUI(Laberinto matriz) {
        this.matriz = matriz;
        matrizLaberinto = matriz.PrimerNivel();
        // Solicitar el nombre del jugador
        nombreJugador = JOptionPane.showInputDialog(null, "Ingrese su nombre:");
        setTitle("Laberinto GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panelLaberinto = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int cellSize = 30; // Tamaño de cada celda en píxeles

                for (int i = 0; i < matrizLaberinto.length; i++) {
                    for (int j = 0; j < matrizLaberinto[i].length; j++) {
                        String elemento = matrizLaberinto[i][j];

                        if (elemento.equals("a")) {
                            g.setColor(Color.WHITE);
                            g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        } else if (elemento.equals(" ")) {
                            g.setColor(Color.BLUE);
                            g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        } else if (elemento.equals("*")) {
                            g.setColor(Color.GREEN);
                            g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        } else if (elemento.equals("o")) {
                            g.setColor(Color.YELLOW);
                            g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        } else if (elemento.equals("x")) {
                            g.setColor(Color.RED);
                            g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        }
                    }
                }
            }

            @Override
            public Dimension getPreferredSize() {
                int cellSize = 30;
                int width = matrizLaberinto[0].length * cellSize;
                int height = matrizLaberinto.length * cellSize;
                return new Dimension(width, height);
            }
        };
        // Panel del puntaje
        panelPuntaje = new JPanel();
        puntajeLabel = new JLabel("Puntaje: " + puntaje);
        panelPuntaje.add(puntajeLabel);

        // Agregar paneles a la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelLaberinto, BorderLayout.CENTER);
        getContentPane().add(panelPuntaje, BorderLayout.NORTH);

        // Configuración de la ventana
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panelLaberinto.setFocusable(true);
        panelLaberinto.addKeyListener(this);

        add(panelLaberinto);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setMatrizLaberinto() {
        matrizLaberinto = matriz.PrimerNivel();
        panelLaberinto.repaint();
    }

    private void moverJugador(int posX, int posY) {
        // Encuentra la posición actual del jugador
        int filaJugador = -1;
        int columnaJugador = -1;

        for (int i = 0; i < matrizLaberinto.length; i++) {
            for (int j = 0; j < matrizLaberinto[i].length; j++) {
                if (matrizLaberinto[i][j].equals("*")) {
                    filaJugador = i;
                    columnaJugador = j;
                    break;
                }
            }
        }

        // Calcula la nueva posición del jugador
        int nuevaFila = filaJugador + posY;
        int nuevaColumna = columnaJugador + posX;

        // Verifica si la nueva posición es válida y está dentro del laberinto
        if (nuevaFila >= 0 && nuevaFila < matrizLaberinto.length && nuevaColumna >= 0 && nuevaColumna < matrizLaberinto[0].length
                && matrizLaberinto[nuevaFila][nuevaColumna].equals(" ") || matrizLaberinto[nuevaFila][nuevaColumna].equals("o") || matrizLaberinto[nuevaFila][nuevaColumna].equals("x")) {
            // Mueve al jugador a la nueva posición
            if (!matrizLaberinto[nuevaFila][nuevaColumna].equals("x")) {
            if (matrizLaberinto[nuevaFila][nuevaColumna].equals("o")) {
                puntaje = puntaje + 500;
                puntajeLabel.setText("Puntaje: " + puntaje);
            }
            matrizLaberinto[filaJugador][columnaJugador] = " ";
            matrizLaberinto[nuevaFila][nuevaColumna] = "*";

            // Actualiza la representación gráfica del laberinto
            panelLaberinto.repaint();
            }else{
                JOptionPane.showMessageDialog(null, "¡Felicidades, has ganado!\n" + " " + nombreJugador + " tu puntaje es " + puntaje);
                guardarDatos(); // Guarda los datos del jugador
                System.exit(0); // Terminar el juego
            }
        }
        panelLaberinto.repaint();
    }

    private void guardarDatos() {
        try {
            FileWriter fw = new FileWriter("C:/Users/LUIS-PC/Documents/Nueva carpeta/anonimo/puntajes.txt", true); // Abre el archivo en modo de escritura, true indica que se agregará al final del archivo
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nombreJugador + ": " + puntaje); // Escribe el nombre y el puntaje en una línea
            bw.newLine(); // Nueva línea
            bw.close(); // Cierra el BufferedWriter
        } catch (IOException e) {
            e.printStackTrace(); //ayuda a proporcionar informacion sobre la causa del error
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            moverJugador(0, -1); // Mover hacia arriba
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moverJugador(0, 1); // Mover hacia abajo
        } else if (keyCode == KeyEvent.VK_LEFT) {
            moverJugador(-1, 0); // Mover hacia la izquierda
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moverJugador(1, 0); // Mover hacia la derecha
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        Laberinto matriz = new Laberinto(); // Crea una instancia de MatrizLab
        LaberintoGUI laberinto = new LaberintoGUI(matriz);
    }
}
