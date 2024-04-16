import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LaberintoGUI extends JFrame implements KeyListener {
    private Laberinto matriz;
    private String[][] matrizLaberinto;
    private JPanel panelLaberinto;
    private JPanel panelPuntaje;
    private JLabel puntajeLabel;
    private JLabel tiempoLabel; 
    private int puntaje = 0;
    private long startTime; 
    private Timer timer; 
    private String nombreJugador;

    public LaberintoGUI(Laberinto matriz, String nombreNivel, String nombreJugador) {
        this.matriz = matriz;
        this.nombreJugador = nombreJugador;

        setTitle("Laberinto GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panelLaberinto = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int cellSize = 30;

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

        panelPuntaje = new JPanel();
        puntajeLabel = new JLabel("Puntaje: " + puntaje);
        tiempoLabel = new JLabel("Tiempo: 00:00"); 
        panelPuntaje.add(puntajeLabel);
        panelPuntaje.add(tiempoLabel); 

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelLaberinto, BorderLayout.CENTER);
        getContentPane().add(panelPuntaje, BorderLayout.NORTH);

        panelLaberinto.setFocusable(true);
        panelLaberinto.addKeyListener(this);

        cargarNivel(nombreNivel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        startTime = System.currentTimeMillis(); 
        iniciarTemporizador(); 
    }

    private void iniciarTemporizador() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                tiempoLabel.setText("Tiempo: " + sdf.format(new Date(elapsedTime)));
            }
        });
        timer.start();
    }

    public void cargarNivel(String nombreNivel) {
        switch (nombreNivel) {
            case "Primer Nivel":
                matrizLaberinto = matriz.PrimerNivel();
                break;
            case "Segundo Nivel":
                matrizLaberinto = matriz.SegundoNivel();
                break;
            case "Tercer Nivel":
                matrizLaberinto = matriz.TercerNivel();
                break;
            default:
                throw new IllegalArgumentException("Nivel no válido: " + nombreNivel);
        }
        panelLaberinto.repaint();
    }

    private void moverJugador(int posX, int posY) {
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

        int nuevaFila = filaJugador + posY;
        int nuevaColumna = columnaJugador + posX;

        if (nuevaFila >= 0 && nuevaFila < matrizLaberinto.length && nuevaColumna >= 0 && nuevaColumna < matrizLaberinto[0].length
                && (matrizLaberinto[nuevaFila][nuevaColumna].equals(" ") || matrizLaberinto[nuevaFila][nuevaColumna].equals("o") || matrizLaberinto[nuevaFila][nuevaColumna].equals("x"))) {

            if (!matrizLaberinto[nuevaFila][nuevaColumna].equals("x")) {
                if (matrizLaberinto[nuevaFila][nuevaColumna].equals("o")) {
                    puntaje += 500;
                    puntajeLabel.setText("Puntaje: " + puntaje);
                }
                matrizLaberinto[filaJugador][columnaJugador] = " ";
                matrizLaberinto[nuevaFila][nuevaColumna] = "*";

                panelLaberinto.repaint();
            } else {
                timer.stop(); 
                long tiempoTranscurrido = (System.currentTimeMillis() - startTime) / 1000; 
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss"); 
                JOptionPane.showMessageDialog(null, "¡Felicidades, has ganado!\n" + nombreJugador + ", tu puntaje es " + puntaje + "\nTiempo: " + sdf.format(new Date(tiempoTranscurrido * 1000))); 
                guardarDatos(tiempoTranscurrido); 
                System.exit(0);
            }
        }
        panelLaberinto.repaint();
    }

    private void guardarDatos(long tiempoTranscurrido) { 
        try {
            FileWriter fw = new FileWriter("puntajes.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nombreJugador + ": " + puntaje + " Puntos, " + " Tiempo "+tiempoTranscurrido / 60 + ":" + tiempoTranscurrido % 60 + " "); 
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            moverJugador(0, -1);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moverJugador(0, 1);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            moverJugador(-1, 0);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moverJugador(1, 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
