import java.util.ArrayList;
import java.util.Scanner;
/**
 * ESTA CLASE ORGANIZA EL FLUJO DEL JUEGO, DESDE LA CREACION 
 * DE LOS JUGADORES HASTA LA INTERACCION CON EL TABLERO.
 * 
 * @author (ANGEL GAEL ANGUIANO GONZALEZ) 
 * @version (3.3)
 */

public class Juego {
    private Domino domino;
    private Tablero tablero;
    private Pozo pozo;
    private ArrayList<Jugador> jugadores;

    public Juego(int mulaMaxima) {
        domino = new Domino(mulaMaxima);
        tablero = new Tablero();
        jugadores = new ArrayList<>();
        pozo = new Pozo();
    }

    public void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);
        pozo = new Pozo();
        int numJugadores = 0;
        while (numJugadores < 1 || numJugadores > 5) {
            System.out.println("INGRESA LA CANTIDAD DE JUGADORES (2 a 4): ");
            if (scanner.hasNextInt()) {
                numJugadores = scanner.nextInt();
                scanner.nextLine(); 
                if (numJugadores < 1 || numJugadores > 5) {
                    System.out.println("NÚMERO INVÁLIDO. INTENTA NUEVAMENTE.");
                }
            } else {
                System.out.println("ENTRADA INVÁLIDA. POR FAVOR INGRESA UN NÚMERO.");
                scanner.nextLine(); 
            }
        }
        for (int i = 0; i < numJugadores; i++) {
            System.out.println("INTRODUCE EL NOMBRE DEL JUGADOR " + (i + 1) + ":");
            String nombre = scanner.nextLine();
            jugadores.add(new Jugador(nombre));
        }
        int mulaMaxima = 0;
        while (mulaMaxima != 6 && mulaMaxima != 9 && mulaMaxima != 12) {
            System.out.println("¿QUÉ TIPO DE MULA DESEAS JUGAR? (6, 9, 12): ");
            if (scanner.hasNextInt()) {
                mulaMaxima = scanner.nextInt();
                scanner.nextLine();
                if (mulaMaxima != 6 && mulaMaxima != 9 && mulaMaxima != 12) {
                    System.out.println("MULA INVÁLIDA. INTENTA NUEVAMENTE.");
                }
            } else {
                System.out.println("ENTRADA INVÁLIDA. POR FAVOR INGRESA UN NÚMERO.");
                scanner.nextLine(); 
            }
        }
        domino = new Domino(mulaMaxima);
        domino.repartirFichas(jugadores, mulaMaxima, pozo);
        System.out.println("MENSAJE:");
        pozo.mostrarFichasDelPozo();
        jugarRondas();
    }
    
    private void jugarRondas() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            for (Jugador jugador : jugadores) {
                System.out.println("\n"+ jugador.getNombre() + ", ES TU TURNO.");
                tablero.mostrarTablero();
                jugador.mostrarMano();
                //CORREGIR LO SIGUIENTE.
                ArrayList<Ficha> fichasJugables = tablero.obtenerFichasJugables(jugador.getFichasEnMano());
                if (fichasJugables.isEmpty()) {
                    // Si no tiene fichas jugables, pasa su turno 
                    System.out.println(jugador.getNombre() + " PASAS TURNO.\n");
                    continue; // Saltar al siguiente jugador
                }
                // Mostrar solo las fichas jugables
                System.out.println("FICHAS QUE PUEDES SELECCIONAR:");
                for (int i = 0; i < fichasJugables.size(); i++) {
                    System.out.println((i + 1) + ": " + fichasJugables.get(i));
                }

                // PEDIRLE AL JUGADOR QUE COLOQUE UNA FICHA.
                System.out.println("SELECCIONA UNA FICHA SEGUN SU INDICE: ");
                int indice = scanner.nextInt()-1;
                Ficha fichaSeleccionada = fichasJugables.get(indice);
                jugador.getFichasEnMano().remove(fichaSeleccionada);
                tablero.colocarFicha(fichaSeleccionada);

                if(jugador.sinFichas()){
                    System.out.println(jugador.getNombre()+" ha gritado ¡DOMINÓ!\nSE ACABÓ EL JUEGO.");
                    juegoTerminado = true;
                    break;
                }// Puedes agregar más lógica para verificar si alguien gana o si se termina el juego.
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Juego juego = new Juego(12);
        juego.iniciarJuego();
    }
}
