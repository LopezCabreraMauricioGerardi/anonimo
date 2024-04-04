import java.util.Scanner;
public class Jugar {

    Scanner lector = new Scanner(System.in);
    int puntaje = 0;

    public void Avanzar(String[][] matriz, int filas, int colum) {
        int golpes = 0;
        Laberinto x = new Laberinto();
        int[] arreglo = x.posX(matriz);

        while (golpes < 5) {
            if (matriz[filas][colum] == matriz[arreglo[0]][arreglo[1]]) {
                System.out.println("FELICIDADES HAS GANADO !!");
                golpes += 5;
            } else {
                imprimirMatriz(matriz);
                System.out.print("Mover -> ");
                String tecla = lector.next();
                //Derecha
                if (tecla.equalsIgnoreCase("d")) {
                    if (!matriz[filas][colum + 1].equals("a")) {
                        if (matriz[filas][colum + 1].equals("o")) {
                            puntaje = puntaje + 500;
                        }
                        matriz[filas][colum + 1] = "*";
                        matriz[filas][colum] = " ";
                        colum++;

                    } else {
                        golpes++;
                        System.out.println("Has golpeado " + golpes + " de 5 con la pared ");

                    }
                }

                //izquierda
                if (tecla.equalsIgnoreCase("a")) {
                    if (!matriz[filas][colum - 1].equals("a")) {
                        if (matriz[filas][colum - 1].equals("o")) {
                            puntaje = puntaje + 500;
                        }
                        matriz[filas][colum - 1] = "*";
                        matriz[filas][colum] = " ";
                        colum--;

                    } else {
                        golpes++;
                        System.out.println("Has golpeado " + golpes + " de 5 con la pared ");

                    }
                }

                //arriba
                if (tecla.equalsIgnoreCase("w")) {
                    if (!matriz[filas - 1][colum].equals("a")) {
                        if (matriz[filas - 1][colum].equals("o")) {
                            puntaje = puntaje + 500;
                        }
                        matriz[filas - 1][colum] = "*";
                        matriz[filas][colum] = " ";
                        filas--;

                    } else {
                        golpes++;
                        System.out.println("Has golpeado " + golpes + " de 5 con la pared ");

                    }
                }

                //abajo
                if (tecla.equalsIgnoreCase("s")) {
                    if (!matriz[filas + 1][colum].equals("a")) {
                        if (matriz[filas + 1][colum].equals("o")) {
                            puntaje = puntaje + 500;
                        }
                        matriz[filas + 1][colum] = "*";
                        matriz[filas][colum] = " ";
                        filas++;

                    } else {
                        golpes++;
                        System.out.println("Has golpeado " + golpes + " de 5 con la pared ");

                    }
                }
            }
        }
    }

    public void imprimirMatriz(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public int getPuntaje() {
        return puntaje;
    }

    public static void main(String[] args) {
        Laberinto mat = new Laberinto();
        Jugar jugar = new Jugar();
        String nombre;
        Scanner lector = new Scanner(System.in);
        System.out.println("Ingrese su nombre");
        nombre = lector.nextLine();
        jugar.Avanzar(mat.PrimerNivel(), mat.filaasterico(mat.PrimerNivel()), mat.columasterico(mat.PrimerNivel()));
        System.out.println(" ");
        System.out.println(nombre + " tu puntaje total es: " + jugar.getPuntaje());

    }
}
