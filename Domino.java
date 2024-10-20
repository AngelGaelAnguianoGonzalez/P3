import java.util.*;
/**
 * REPRESENTA LAS FICHAS DE DOMINO DE DOBLE 6, 9 O 12
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */
public class Domino
{
    private ArrayList<Ficha> fichas;
    private Pozo pozo;
    public Domino(int mulaMaxima){
        fichas = crearFichas(mulaMaxima);  
        pozo = new Pozo();  
    }
    
    //CREAR LAS FICHAS DE DOMINO SEGUN LA MULA MAS GRANDE.
    private ArrayList<Ficha> crearFichas(int mulaMaxima) {
        ArrayList<Ficha> todasLasFichas = new ArrayList<>();
        for (int i = 0; i <= mulaMaxima; i++) {
            for (int j = i; j <= mulaMaxima; j++) {
                todasLasFichas.add(new Ficha(i, j));
            }
        }
        return todasLasFichas;
    }
    
    //REPARTE LAS FICHAS A LOS JUGADORES, SEGUN LA CANTIDAD Y MULA MAXIMA
    public void repartirFichas(ArrayList<Jugador> jugadores, int mulaMaxima, Pozo pozo) {
        int numJugadores = jugadores.size();
        int fichasPorJugador = 0;
        switch (numJugadores) {
            case 2:
                if (mulaMaxima == 6) {
                    fichasPorJugador = 14;  // 28 fichas, no sobra
                } else if (mulaMaxima == 9) {
                    fichasPorJugador = 27;  // 55 fichas, sobra 1
                } else if (mulaMaxima == 12) {
                    fichasPorJugador = 45;  // 91 fichas, sobra 1
                }
                break;
            case 3:
                if (mulaMaxima == 6) {
                    fichasPorJugador = 9;   // 27 fichas, sobra 1
                } else if (mulaMaxima == 9) {
                    fichasPorJugador = 18;  // 55 fichas, sobra 1
                } else if (mulaMaxima == 12) {
                    fichasPorJugador = 30;  // 91 fichas, sobra 1
                }
                break;
            case 4:
                if (mulaMaxima == 6) {
                    fichasPorJugador = 7;   // 28 fichas, no sobra
                } else if (mulaMaxima == 9) {
                    fichasPorJugador = 13;  // 55 fichas, sobran 3
                } else if (mulaMaxima == 12) {
                    fichasPorJugador = 22;  // 91 fichas, sobran 3
                }
                break;
            default:
                System.out.println("SOLO PUEDEN JUGAR 2 A 4 PERSONAS");
                return;
        }
        for (Jugador jugador : jugadores) {
            ArrayList<Ficha> fichasEnManoParaElJugador = new ArrayList<>();
            for (int i = 0; i < fichasPorJugador && !fichas.isEmpty(); i++) {
                fichasEnManoParaElJugador.add(fichas.remove(0));
            }
            jugador.setFichasEnMano(fichasEnManoParaElJugador);  // Asignar las fichas al jugador
        }
        // Agregar las fichas restantes al pozo
        while (!fichas.isEmpty()) {
            pozo.agregarFichaAlPozo(fichas.remove(0));  // Añadir ficha al pozo una por una
        }

    // (Opcional) Mostrar las fichas en el pozo
    pozo.mostrarFichasDelPozo();
    }

    //METODO PARA REVOLVER LAS FICHAS DEL DOMINO.
    public void revolverFichas(){
        Collections.shuffle(fichas);
    }
    
    //REPARTE LAS FICHAS A LOS JUGADORES
    public Ficha repartirFicha() {
        return fichas.remove(fichas.size() - 1);
    }
    
    //METODO PARA MOSTRAR LAS FICHAS DE DOMINO.
    public void mostrarDomino(){
        System.out.println(fichas.toString());
    }
    
    // //MANDA LAS FICHAS SOBRANTES AL POZO
    // public Pozo restoDelDomino(){
        // Pozo pozo = new Pozo();
        // pozo.agregarFichasAlPozo(fichas);  
        // fichas.clear();  
        // pozo.mostrarPozo();
        // return pozo;
    // }
}
