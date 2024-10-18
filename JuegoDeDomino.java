import java.util.*;
import java.util.Scanner;
/**
 * CONTROLA EL FLUJO DEL JUEGO.
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */
public class JuegoDeDomino
{
    private ArrayList<Jugador> jugadoresActuales;
    private Domino domino;
    private Tablero tableroDelJuego;
    private Pozo pozo;
    private Map<String, List<Ficha>> fichasJugadas;
    private int mulaMaxima;
    private int turno;
    public JuegoDeDomino(int mulaMaxima) {
        ArrayList<String> nombresDeLosJugadores = new ArrayList<>();
        jugadoresActuales = new ArrayList<>();
        tableroDelJuego = new Tablero();
        pozo = new Pozo();
        domino = new Domino();
        domino.generarFichas(mulaMaxima);
        domino.revolverFichas();
        this.turno = 0;
        configurarJuego();
        for(String nombre : nombresDeLosJugadores){
            jugadoresActuales.add(new Jugador(nombre));
        }

        repartirFichas();
        pozo = domino.restoDelDomino();
        jugar();
    }
    //REPARTE LAS FICHAS ENTRE LOS JUGADORES QUE HAY
    private void repartirFichas(){
        int numeroDeJugadores = jugadoresActuales.size();
        for(Jugador jugador : jugadoresActuales){
            jugador.getFichasEnManoActualDelJugador().
            addAll(domino.entregarFichasAlosJugadores(numeroDeJugadores, mulaMaxima));
            /*ArrayList<Ficha> fichasJugador = domino.entregarFichasAlosJugadores(jugadoresActuales.size(), tipoDeDomino);
            for(Ficha ficha : fichasJugador){
            jugador.tomarFicha(ficha);
            }*/
        }
        //pozo = domino.restoDelDomino();
    }
    //CONFIGURA LA PARTIDA CON EL NUMERO DE JUGADORES Y TIPO DE DOMINO
    private void configurarJuego(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("INGRESA EL NUMERO DE JUGADORES DE 2 A 4 (MIN-MAX): ");
        int numeroDeJugadores = scanner.nextInt();
        while (numeroDeJugadores < 1 || numeroDeJugadores > 5) {
            System.out.println("INTENTA DE NUEVO");
            numeroDeJugadores = scanner.nextInt();
        }
        System.out.println("INGRESA TIPO DE JUEGO DE DOMINO: (6, 9, 12)");
        mulaMaxima = scanner.nextInt();
        while (mulaMaxima != 6 && mulaMaxima != 9 && mulaMaxima != 12) {
            System.out.println("Elija 6, 9, o 12.");
            mulaMaxima = scanner.nextInt();
        }
        domino.generarFichas(mulaMaxima);
        domino.revolverFichas();
        for (int i = 1; i <= numeroDeJugadores; i++) {
            System.out.println("NOMBRE DEL JUGADOR NUMERO " + i + ":");
            String nombreJugador = scanner.next();
            Jugador jugador = new Jugador(nombreJugador);
            jugadoresActuales.add(jugador);
        }
        repartirFichas();
    }
    //JUGAR UNA RONDA
    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;
        int turno = 0;
        while (!juegoTerminado) {
            if(!pozo.verificarPozo() && tableroDelJuego.estaVacio()){//AÑADIR SI EL TABLERO TAMBIEN ESTA VACIO
                Ficha fichaInicial = pozo.tomarFichaDelPozo();   
                System.out.println("SE TOMÓ UNA FICHA DEL POZO PARA INICIAR: "+fichaInicial);
                tableroDelJuego.colocarFichaEnTablero(fichaInicial, "Derecho"); 
                tableroDelJuego.mostrarTablero();
                Jugador jugadorActual = jugadoresActuales.get(turno);
                System.out.println("\n\t\t\t\tTURNO DE: " + jugadorActual.getNombreDelJugador());
                jugadorActual.mostrarMano();

                List<Ficha> jugadasPosibles = jugadorActual.obtenerPosiblesJugadas(fichasJugadas);
                if(!jugadasPosibles.isEmpty()){
                    System.out.println("FICHAS QUE PUEDES JUGAR:");
                    for (int i = 0; i < jugadasPosibles.size(); i++) {
                        System.out.println((i + 1) + ". " + jugadasPosibles.get(i));
                    }
                    System.out.println("SELECCIONA UNA CARTA PARA JUGAR (1-" + jugadasPosibles.size() + "): ");
                    int eleccion = scanner.nextInt() - 1;
                    Ficha fichaJugada = jugadasPosibles.get(eleccion);

                    if (jugadorActual.ponerFicha(fichaJugada)) {
                        tableroDelJuego.colocarFichaEnTablero(fichaJugada, "DERECHO");  // Colocar la ficha en el extremo derecho
                        System.out.println(jugadorActual.getNombreDelJugador() + " HA COLOCADO LA FICHA: " + fichaJugada);
                        tableroDelJuego.mostrarTablero();
                    }
                    if (jugadorActual.sinFichas()) {
                        System.out.println(jugadorActual.getNombreDelJugador() + " HA GANADO EL JUEGO");
                        juegoTerminado = true;
                    }
                }
            }
            else{
                Jugador jugadorActual = jugadoresActuales.get(turno);
                System.out.println("\n\t\t\t\tTURNO DE: " + jugadorActual.getNombreDelJugador());
                jugadorActual.mostrarMano();
                List<Ficha> jugadasPosibles = jugadorActual.obtenerPosiblesJugadas(fichasJugadas);
                if (!jugadasPosibles.isEmpty()) {
                    System.out.println("FICHAS QUE PUEDES JUGAR:");
                    for (int i = 0; i < jugadasPosibles.size(); i++) {
                        System.out.println((i + 1) + ". " + jugadasPosibles.get(i));
                    }
                    int eleccion = -1;
                    while (eleccion < 0 || eleccion >= jugadasPosibles.size()) {
                        System.out.println("SELECCIONA UNA FICHA PARA JUGAR (1-" + jugadasPosibles.size() + "): ");
                        eleccion = scanner.nextInt() - 1;
                    }
                    Ficha fichaJugada = jugadasPosibles.get(eleccion);
                    // Decidir dónde colocar la ficha: izquierda o derecha
                    System.out.println("INDICA COLOCACION (1: Izquierda, 2: Derecha): ");
                    int posicion = scanner.nextInt();

                    if (posicion == 1) {
                        // Colocar la ficha en el extremo izquierdo
                        tableroDelJuego.colocarFichaEnTablero(fichaJugada, "Izquierdo");
                    } else {
                        // Colocar la ficha en el extremo derecho
                        tableroDelJuego.colocarFichaEnTablero(fichaJugada, "Derecho");
                    }
                    System.out.println(jugadorActual.getNombreDelJugador() + " HA COLOCADO LA FICHA: " + fichaJugada);
                    tableroDelJuego.mostrarTablero();
                    if (jugadorActual.sinFichas()) {
                        System.out.println("¡" + jugadorActual.getNombreDelJugador() + " HA GANADO EL JUEGO!");
                        juegoTerminado = true;
                    }
                } else {
                    // Si no hay jugadas posibles
                    System.out.println(jugadorActual.getNombreDelJugador() + " NO TIENES FICHAS PARA JUGAR.");
                    if (!pozo.verificarPozo()) {
                        Ficha fichaDelPozo = pozo.tomarFichaDelPozo();
                        System.out.println(jugadorActual.getNombreDelJugador() + " HAS TOMADO UNA FICHA DEL POZO: " + fichaDelPozo);
                        jugadorActual.robarFicha(pozo);
                    } else {
                        System.out.println("POZO VACIO. PASAS TURNO.");
                    }
                }
            }
            
            
            /*for (Jugador jugador : jugadoresActuales) {
            tableroDelJuego.mostrarTablero();
            jugador.mostrarMano();

            Ficha fichaValida = jugador.verificarFichaValida(tableroDelJuego.getExtremoIzquierdo(), tableroDelJuego.getExtremoDerecho());
            if (fichaValida != null) {
            System.out.println(jugador.getNombreDelJugador() + ", JUEGA LA FICHA: " + fichaValida);
            jugador.ponerFicha(fichaValida);
            tableroDelJuego.colocarFichaEnTablero(fichaValida, "Derecho"); // Puedes cambiar la lógica para permitir al jugador elegir el extremo
            } else {
            System.out.println(jugador.getNombreDelJugador() + " NO TIENES FICHAS, PASAS DE TURNO.\n");
            }

            if (jugador.sinFichas()) {
            System.out.println(jugador.getNombreDelJugador() + " HA GANADO LA RONDA.");
            juegoTerminado = true;
            break;
            }
            }*/
        }
    }
    //INICIAR JUEGO DE DOMINO
    public void iniciarJuego() {
        configurarJuego();
        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            jugar();
            juegoTerminado = true;
        }
    }

}
