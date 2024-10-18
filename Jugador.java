import java.util.*;
/**
 * CLASE QUE CONTIENE LOS DATOS DELJUGADOR
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */
public class Jugador
{
    private String nombreDelJugador;
    private int puntuacion;
    private ArrayList<Ficha> fichasEnManoActualDelJugador;
    
    public Jugador(String nombreDelJugador){
        this.nombreDelJugador = nombreDelJugador;
        this.fichasEnManoActualDelJugador = new ArrayList();
        puntuacion = 0;
    }
    //CUENTA LAS FICHAS QUE TIENE EL JUGADOR
    public int contarFichasEnMano(){
        return fichasEnManoActualDelJugador.size();
    }
    //METODO QUE AÑADE LA FICHA A LA MANO DEL JUGADOR.
    public void tomarFicha(Ficha ficha){
        fichasEnManoActualDelJugador.add(ficha);
    }
    //METODO QUE QUITA LA FICHA EN LA MANO DEL JUGADOR PARA COLOCARLA EN EL TABLERO.
    public boolean ponerFicha(Ficha ficha){
        if(fichasEnManoActualDelJugador.contains(ficha)){
            fichasEnManoActualDelJugador.remove(ficha);
            return true;
        }
        else{
            return false;
        }
    }
    //MOSTRAR LAS FICHAS QUE EL JUGADOR TIENE EN LA MANO
    public void mostrarMano(){
        System.out.println("["+nombreDelJugador+"] TIENES LAS SIGUIENTES FICHAS.");
        for(Ficha ficha : fichasEnManoActualDelJugador){
           System.out.println(ficha);
        }
    }
    //OBTIENE UNA FICHA ESPECIFICA DE LA MANO DEL JUGADOR
    public Ficha obtenerFicha(int indice){
        if (indice >= 0 && indice < fichasEnManoActualDelJugador.size()) {
            return fichasEnManoActualDelJugador.get(indice);
        } else {
            return null;
        }        
    }
    //VERIFICAR SI EL JUGADOR TIENE UNA FICHA VALIDA PARA JUGAR.
    public Ficha verificarFichaValida(int extremoIzquierdo, int extremoDerecho){
        for(Ficha ficha : fichasEnManoActualDelJugador){
            if(ficha.esCompatible(extremoIzquierdo, extremoDerecho)){
                return ficha;
            }
        }
        System.out.println("NO ES VALIDA PARA COLOCAR.");
        return null;
    }
    //ROBAR UNA FICHA DEL POSO.
    public void robarFicha(Pozo pozo) {
        Ficha fichaRobada = pozo.tomarFichaDelPozo(); 
        if (fichaRobada != null) {
            fichasEnManoActualDelJugador.add(fichaRobada);
        }
    }
    public void pasarTurno() {
        System.out.println(nombreDelJugador + " PASÓ EL TURNO.");
    }
    //VERIFICA SI EL JUGADOR TIENE FICHAS
    public boolean sinFichas(){
        return fichasEnManoActualDelJugador.isEmpty();
    }
    //ACTUALIZA LA PUNTUACION DEL JUGADOR
    public void puntuacionDelJugador(int puntos){
        puntuacion = puntuacion + puntos;
    } 
    public boolean esMula(){
        for (Ficha ficha : fichasEnManoActualDelJugador) {
            if (ficha.esMula()) {
                return true;
            }
        }
        return false;
    }
    
    
    // GETTERS Y SETTERS
    // METODO QUE OBTIENE LA MANO DEL JUGADOR.
    public ArrayList<Ficha> getFichasEnManoActualDelJugador() {
        return fichasEnManoActualDelJugador;
    }
    public String getNombreDelJugador(){
       return nombreDelJugador;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    
}
