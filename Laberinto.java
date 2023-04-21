package LaberintoSol;

import java.util.Stack;


public class Laberinto {
    // Representación del laberinto en una matriz 
    private char[][] laberinto;
     // Tamaño de la matriz
    private int filas;
    private int columnas;
    // Coordenadas del punto de partida
    private int xInicio;
    private int yInicio;
    // Pila para almacenar los puntos de decisión
    private final Stack<int[]> pila;
   

    public Laberinto(char[][] laberinto) {
       this.laberinto = laberinto;
        filas = laberinto.length;
        columnas = laberinto[0].length;
        // Encontrar las coordenadas del punto de partida o sea "E"
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j] == 'E') {
                    xInicio = i;
                    yInicio = j;
                    break;
                }
            }
        }
        pila = new Stack<>();
        
    }
    
    public  boolean resolverLaberinto() {
        // Coordenadas del punto actual
        int xActual = xInicio;
        int yActual = yInicio;
        // Direcciones de movimiento posibles
        int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // Mientras no lleguemos a un borde del laberinto
        while (xActual != 0 && xActual != filas - 1 && yActual != 0 && yActual != columnas - 1) {
            // Marcar el punto actual como visitado
            laberinto[xActual][yActual] = '*';
            
            // Buscar una dirección de movimiento válida
            boolean movEncontrado = false;
            for (int[] direccion : direcciones) {
                int xSiguiente = xActual + direccion[0];
                int ySiguiente = yActual + direccion[1];
                if (xSiguiente >= 0 && xSiguiente < filas && ySiguiente >= 0 && ySiguiente < columnas
                        && laberinto[xSiguiente][ySiguiente] == '0') {
                    // Encontramos una dirección válida, avanzamos
                    pila.push(new int[]{xActual, yActual});
                    xActual = xSiguiente;
                    yActual = ySiguiente;
                    movEncontrado = true;
                    break;
                }
            }
            // Si no encontramos ninguna dirección válida, retrocedemos
            if (!movEncontrado) {
                if (pila.isEmpty()) {
                    System.out.print("No se puede resolver el laberinto");
                    return false;
                }
                int[] puntoDecision =  pila.pop();
                xActual = puntoDecision[0];
                yActual = puntoDecision[1];
            }
        }
        
       // Llegamos a un borde del laberinto, hemos encontrado una salida
        return true;
        }
   private void imprimirRuta() {
        System.out.println("Ruta del laberinto:");
        System.out.println(laberinto);
        }
    
     public static void main(String[] args) {
         char[][] laberinto ={
                {'1', '1', '1', '1', '1', '1', 'E', '1', '1', '1'},
                {'1', '0', '1', '0', '0', '0','0', '0', '0', '1'},
                {'1', '0', '0', '0', '1', '0','0', '1', '0', '1'},
                {'1', '1', '1', '1', '0', '0','1', '1', '1', '1'},
                {'1', '0', '0', '0', '0', '1','0', '0', '0', '1'},
                {'1', '0', '1', '1', '1', '1','0', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '1','0', '1', '0', '1'},
                {'1', '1', '0', '1', '1', '1','0', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '0','0', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '1','0', '1', '0', '1'},
                {'1', '1', '1', '1', '1', '1','1', '1', '0', '1'},
        };
     Laberinto e = new Laberinto (laberinto);
     e.resolverLaberinto();
     e.imprimirRuta();
}

   
}
        
        

