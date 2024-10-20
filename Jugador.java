import java.util.*;
/**
 * CLASE QUE CONTIENE LOS DATOS DELJUGADOR
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */
public class Jugador
{
    private ArrayList<Ficha> fichasEnMano;
    private String nombre;
    private int puntuacion;
    
    public Jugador(String nombre){
        this.nombre = nombre;
        this.fichasEnMano = new ArrayList();
        puntuacion = 0;
    }
    
    //METODO QUE AÑADE LA FICHA A LA MANO DEL JUGADOR.
    public void agarrarFicha(Ficha ficha){
        fichasEnMano.add(ficha);
    }
    
    //METODO QUE QUITA LA FICHA EN LA MANO DEL JUGADOR PARA COLOCARLA EN EL TABLERO.
    public boolean ponerFicha(Ficha ficha){
        if(fichasEnMano.contains(ficha)){
            fichasEnMano.remove(ficha);
            return true;
        }
        else{
            return false;
        }
    }
    
    //VERIFICA SI EL JUGADOR TIENE FICHAS
    public boolean sinFichas(){
        return fichasEnMano.isEmpty();
    }

    //CUENTA LAS FICHAS QUE TIENE EL JUGADOR
    public int contarFichasEnMano(){
        return fichasEnMano.size();
    }
    
    //OBTIENE UNA FICHA ESPECIFICA DE LA MANO DEL JUGADOR
    public Ficha obtenerFicha(int indice){
        if (indice >= 0 && indice < fichasEnMano.size()) {
            return fichasEnMano.get(indice);
        } else {
            return null;
        }        
    }
    
    //MOSTRAR LAS FICHAS QUE EL JUGADOR TIENE EN LA MANO
    public void mostrarMano(){
        System.out.println("["+nombre+"] TIENES LAS SIGUIENTES FICHAS.\n"
        +fichasEnMano);
    }
    
    //ROBAR UNA FICHA DEL POSO.
    public void robarFicha(Pozo pozo) {
        Ficha fichaRobada = pozo.tomarFichaDelPozo(); 
        if (fichaRobada != null) {
            fichasEnMano.add(fichaRobada);
        }
    }
        
    //ACTUALIZA LA PUNTUACION DEL JUGADOR
    public void puntuacionDelJugador(int puntos){
        puntuacion = puntuacion + puntos;
    } 

    // GETTERS Y SETTERS
    public ArrayList<Ficha> getFichasEnMano() {
        return fichasEnMano;
    }
    public String getNombre(){
       return nombre;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    public void setFichasEnMano(ArrayList<Ficha> fichasEnMano) {
        this.fichasEnMano = fichasEnMano;
    }

}
