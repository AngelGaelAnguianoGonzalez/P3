import java.util.ArrayList;
/**
 * REPRESENTA EL AREA DONDE LOS JUGADORES COLOCAN LAS FICHAS.
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */
public class Tablero
{
    private ArrayList<Ficha> fichasJugadasEnTablero;
    private int extremoIzquierdo;
    private int extremoDerecho;
    public Tablero() {
        fichasJugadasEnTablero = new ArrayList<>();
        extremoIzquierdo = -1; // Indicar que aún no hay fichas en el tablero
        extremoDerecho = -1;
    }
    //MUESTRA LAS FICHAS EN EL TABLERO()
    public void mostrarTablero(){
        System.out.println("FICHAS EN EL TABLERO: ");
        for(Ficha ficha : fichasJugadasEnTablero){
            System.out.println(ficha);
        }
        System.out.println();
    }
    //AGREGAR FICHA EN EL TABLERO EN UNO DE LOS EXTREMOS
    public void colocarFichaEnTablero(Ficha ficha, String extremo){
        if (extremo.equalsIgnoreCase("Izquierdo")) {
            if (ficha.getLadoB() != extremoIzquierdo) {
                ficha.voltearLados(); // VOLTEAR SI NO COINCIDE CON EL LADO
            }
            fichasJugadasEnTablero.add(0, ficha); // AÑADIR AL INICIO DE LA FILA
            extremoIzquierdo = ficha.getLadoA(); // ACTUALIZAR extremoIzquierdo
        } else if (extremo.equalsIgnoreCase("Derecho")) {
            if (ficha.getLadoA() != extremoDerecho) {
                ficha.voltearLados(); // VOLTEAR SI NO COINCIDE CON EL LADO
            }
            fichasJugadasEnTablero.add(ficha); // AÑADIR AL FINAL DE LA FILA
            extremoDerecho = ficha.getLadoB(); // ACTUALIZAR extremoDerecho
        }    
    }
    //SETTERS Y GETTERS
    public int getExtremoIzquierdo() {
        return extremoIzquierdo;
    }
    public int getExtremoDerecho() {
        return extremoDerecho;
    }
    public boolean estaVacio(){
        return fichasJugadasEnTablero.isEmpty();
    }
    
    public boolean esFichaValida(Ficha ficha) {
        if (fichasJugadasEnTablero.isEmpty()) {
            // Si el tablero está vacío, cualquier ficha es válida
            return true;
        } else {
            Ficha extremoIzquierdo = fichasJugadasEnTablero.get(0); // Primera ficha del tablero
            Ficha extremoDerecho = fichasJugadasEnTablero.get(fichasJugadasEnTablero.size() - 1); // Última ficha del tablero
            
            // Verificar si la ficha es compatible con alguno de los extremos del tablero
            return ficha.esCompatible(extremoIzquierdo.getLadoA(), extremoDerecho.getLadoB());
        }
    }
    // METODO PARA OBTENER LA SUMA DE LOS LADOS DE LOS EXTREMOS DEL TABLERO.
    public int obtenerSumaExtremos() {
        if (fichasJugadasEnTablero.isEmpty()) {
            return 0; // SI NO HAY FICHAS LA SUMA ES 0
        }
        Ficha extremoIzquierdo = fichasJugadasEnTablero.get(0);
        Ficha extremoDerecho = fichasJugadasEnTablero.get(fichasJugadasEnTablero.size() - 1);
        //SUMAR EL LADOA DEL EXTREMOIZQUIERDO Y EL LADOB DEL EXTREMO DERECHO.
        return extremoIzquierdo.getLadoA() + extremoDerecho.getLadoB();
    }

}
