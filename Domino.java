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

    public Domino(){
        fichas = new ArrayList<>();
    }
    //CREAR LAS FICHAS DE DOMINO SEGUN LA MULA MAS GRANDE.
    public void generarFichas(int mulaMaxima){
        Ficha fichasNuevas;
        for(int i = 0; i <= mulaMaxima; i++){
            for(int j = 0; j <= i; j++){
                fichasNuevas = new Ficha(i,j);
                fichas.add(fichasNuevas);
            }
        }
    }
    //METODO PARA REVOLVER LAS FICHAS DEL DOMINO.
    public void revolverFichas(){
        Collections.shuffle(fichas);
    }
    //METODO PARA MOSTRAR LAS FICHAS DE DOMINO.
    public void mostrarDomino(){
        System.out.println(fichas.toString());
    }
    //METODO PARA REPARTIR LAS FICHAS DE DOMINO.
    public ArrayList<Ficha> entregarFichasAlosJugadores(int jugadores, int mulaMaxima){
        ArrayList<Ficha> fichasEnManoParaElJugador = new ArrayList<>();
        int fichasPorJugador = 0;
        switch(jugadores){
            case 2:
                if(mulaMaxima == 6){
                    fichasPorJugador = 14;//28 FICHAS, NO SOBRA.
                }
                else{
                    if(mulaMaxima == 9){
                        fichasPorJugador = 27;//55 FICHAS, SOBRA UNA.
                    }
                    else{
                        if(mulaMaxima == 12){
                            fichasPorJugador = 45;//91 FICHAS, SOBRA UNA. 
                        }
                    }
                }
                break;
            case 3:
                if(mulaMaxima == 6){
                    fichasPorJugador = 9;//27 FICHAS, SOBRA UNA.
                }
                else{
                    if(mulaMaxima == 9){
                        fichasPorJugador = 18;//55 FICHAS, SOBRA UNA.
                    }
                    else
                    if(mulaMaxima == 12){
                        fichasPorJugador = 30;//91 FICHAS, SOBRA UNA. 
                    }
                }
                break;
            case 4:
                if(mulaMaxima == 6){
                    fichasPorJugador = 7;//28 FICHAS, NO SOBRA.
                }
                else{
                    if(mulaMaxima == 9){
                        fichasPorJugador = 13;//55 FICHAS, SOBRA TRES.
                    }
                    else{
                        if(mulaMaxima == 12){
                            fichasPorJugador = 22;//91 FICHAS, SOBRA TRES. 
                        }
                    }
                }
                break;
            default:
                System.out.println("SOLO PUEDEN JUGAR 2 A 4 PERSONAS");
                return fichasEnManoParaElJugador;
        }
        for(int i = 0; i < fichasPorJugador && !fichas.isEmpty(); i++){
            //AÑADIMOS LAS FICHAS A LA MANO DEL JUGADOR
            //ASIMISMO LA REMOVEMOS DEL DOMINO
            fichasEnManoParaElJugador.add(fichas.remove(0));

        }
        System.out.println("FICHAS DEL DOMINO");
        System.out.println(fichas);
        return fichasEnManoParaElJugador;
    }
    //ESTE METODO MANDA LAS FICHAS SOBRANTES AL POZO
    public Pozo restoDelDomino(){
        Pozo pozo = new Pozo();
        pozo.agregarFichasAlPozo(fichas);
        fichas.clear();
        pozo.mostrarPozo();
        return pozo;
    }
}
