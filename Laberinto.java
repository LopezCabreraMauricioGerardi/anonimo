/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LUIS-PC
 */
public class Laberinto {
    public String[][] PrimerNivel() {

        String matriz[][] ={
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", " ", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", " ", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", " ", " ", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "o", "a", "a", "a", "a", " ", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", " ", " ", " ", " ", "a", "o", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", " ", "a", "a", " ", "a", " ", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "o", "a", "a", " ", "a", " ", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "*", " ", " ", "a", "a", " ", " ", " ", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", " ", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", " ", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", " ", "a", "a", " ", " ", " ", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", " ", " ", " ", " ", "a", " ", " ", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", " ", "x", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}};

        return matriz;
    }

    public int filaasterico(String[][] matriz) {
        int fila = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j].equals("*")) {
                    fila = i;
                }
            }
        }
        return fila;
    }

    public int columasterico(String[][] matriz) {
        int fila = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j].equals("*")) {
                    fila = j;
                }
            }
        }
        return fila;
    }
    int [] arreglo= new int[2];
    
    public int [] posX(String [][]matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j].equals("x")) {
                    arreglo[0]=i;
                    arreglo[1]=j;
                }
            }
        }
        return arreglo;
    }
}
