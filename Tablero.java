import java.util.ArrayList;
/**
 * REPRESENTA EL AREA DONDE LOS JUGADORES COLOCAN LAS FICHAS.
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */
public class Tablero
{
    private ArrayList<Ficha> fichasEnTablero;
    public Tablero() {
        fichasEnTablero = new ArrayList<>();
    }

    //AGREGAR UNA FICHA EN EL TABLERO
    public void colocarFicha(Ficha ficha) {
        if(fichasEnTablero.isEmpty()){
            fichasEnTablero.add(ficha);
            System.out.println("SE COLOCÓ LA FICHA: " + ficha);
        } else {
            Ficha extremoIzquierdo = getExtremoIzquierdo();
            Ficha extremoDerecho = getExtremoDerecho();
            if (ficha.getLadoA() == extremoIzquierdo.getLadoA()) {
                // Si el ladoA coincide con el extremo izquierdo, invertimos la ficha
                ficha.voltearLados();
                fichasEnTablero.add(0, ficha); // Añadir al inicio
                System.out.println("FICHA COLOCADA EN EXTREMO IZQUIERDO: " + ficha);//INVERTIDA
            } else if (ficha.getLadoB() == extremoIzquierdo.getLadoA()) {
                // Si el ladoB coincide con el extremo izquierdo, no es necesario voltear
                fichasEnTablero.add(0, ficha); // Añadir al inicio
                System.out.println("FICHA COLOCADA EN EXTREMO IZQUIERDO: " + ficha);
            } 
            // Intentamos colocar la ficha en el extremo derecho
            else if (ficha.getLadoA() == extremoDerecho.getLadoB() || ficha.getLadoB() == extremoDerecho.getLadoB()) {
                // Si el ladoA no coincide con el extremo derecho, invertimos la ficha
                if (ficha.getLadoA() != extremoDerecho.getLadoB()) {
                    ficha.voltearLados();  // Invertir la ficha
                }
                fichasEnTablero.add(ficha); // Añadir al final
                System.out.println("FICHA COLOCADA EN EL EXTREMO DERECHO: " + ficha);
            } else {
                System.out.println("NO SE PUDO COLOCAR LA FICHA: " + ficha);
            }
        }
    }

    //MUESTRA LAS FICHAS EN EL TABLERO()
    public void mostrarTablero(){
        System.out.println("___________________________________________________________________________________________________________");
        System.out.println("TABLERO:\n"+fichasEnTablero);
        System.out.println("___________________________________________________________________________________________________________");
    }

    //VERIFICAR SI EL TABLERO ESTÁ VACIO    
    public boolean estaVacio(){
        return fichasEnTablero.isEmpty();
    }

    //SETTERS 
    public Ficha getExtremoIzquierdo() {
        return fichasEnTablero.get(0);
    }

    public Ficha getExtremoDerecho() {
        return fichasEnTablero.get(fichasEnTablero.size()-1);
    }

    // METODO QUE VERIFICA QUE FICHAS DEL JUGADOR SON JUGABLES
    public ArrayList<Ficha> obtenerFichasJugables(ArrayList<Ficha> fichasDelJugador) {
        ArrayList<Ficha> fichasJugables = new ArrayList<>();
        
        if (fichasEnTablero.isEmpty()) {
            //TODAS SON VALIDAS SI NO HAY FICHAS EN EL TABLERO
            return fichasDelJugador;
        }

        Ficha extremoIzquierdo = getExtremoIzquierdo();
        Ficha extremoDerecho = getExtremoDerecho();

        // VERIFICAMOS QUE FICHAS SE PUEDEN COLOCAR EN ALGUN EXTREMO
        for (Ficha ficha : fichasDelJugador) {
            // VERIFICAMOS COLOCACION EN LOS DOS EXTREMOS.
            if (ficha.getLadoA() == extremoIzquierdo.getLadoA() || ficha.getLadoB() == extremoIzquierdo.getLadoA() ||
                ficha.getLadoA() == extremoDerecho.getLadoB() || ficha.getLadoB() == extremoDerecho.getLadoB()) {
                fichasJugables.add(ficha); // AÑADIMOS LA FICHA A LA LISTA DE JUGABLES
            }
        }
        return fichasJugables;
    }

    // METODO PARA OBTENER LA SUMA DE LOS LADOS DE LOS EXTREMOS DEL TABLERO.
    public int obtenerSumaExtremos() {
        if (fichasEnTablero.isEmpty()) {
            return 0; // SI NO HAY FICHAS LA SUMA ES 0
        }
        Ficha extremoIzquierdo = fichasEnTablero.get(0);
        Ficha extremoDerecho = fichasEnTablero.get(fichasEnTablero.size() - 1);
        //SUMAR EL LADOA DEL EXTREMOIZQUIERDO Y EL LADOB DEL EXTREMO DERECHO.
        return extremoIzquierdo.getLadoA() + extremoDerecho.getLadoB();
    }

}
