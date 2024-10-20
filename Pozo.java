import java.util.*;
/**
 * REPRESENTA EL CONJUNTO DE FICHAS 
 * QUE NO SE REPARTIERON ENTRE LOS JUGADORES.
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */
public class Pozo
{
    private ArrayList<Ficha> fichas;

    public Pozo(){
        fichas = new ArrayList<>();
    }

    //VERIFICA SI HAY FICHAS DISPONIBLES EN EL POZO.
    public boolean verificarPozo(){
        return fichas.isEmpty();
    }

    //ROBA UNA FICHA DEL POZO.
    public Ficha tomarFichaDelPozo(){
        if(!verificarPozo()){
            return fichas.remove(0);
        }
        System.out.println("EL POSO ESTÁ VACIO");
        return null;
    }

    //ESTE METODO RECIBE LAS FICHAS DE DOMINO QUE SOBRARON
    public void agregarFichaAlPozo(Ficha ficha) {
        fichas.add(ficha);
    }

    //MUESTRA LAS FICHAS QUE ESTAN EN EL POZO.
    public void mostrarFichasDelPozo() {
        if (fichas.isEmpty()) {
            System.out.println("NO HAY FICHAS EN EL POZO.");
        } else {
            System.out.println("FICHAS EN POZO:");
            for (Ficha ficha : fichas) {
                System.out.println(ficha);  
            }
        }
    }
}
