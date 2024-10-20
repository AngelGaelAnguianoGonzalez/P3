
/**
 * ESTA CLASE MODELA UNA FICHA.
 * 
 * @author (Angel Gael Anguiano Gonzalez) 
 * @version (3.3)
 */
public class Ficha
{
    private int ladoA;
    private int ladoB;
    private boolean esVisible;
    public Ficha(int ladoA, int ladoB){
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        esVisible = true;
    }
    //INVIERTE LOS VALORES DE LA FICHA
    public void voltearLados(){
        int nube = ladoA;
        ladoA = ladoB;
        ladoB = nube;
    }
    //VERIFICA SI ES DOBLE
    public boolean esMula(){
        return ladoA == ladoB;
    }
    //IMPRIME LA FICHA
    @Override
    public String toString(){
        return "[" + ladoA + "|" + ladoB + "]";
    }
    //GETTERS Y GETTERS
    public int getLadoA(){
        return ladoA;
    }
    public int getLadoB(){
        return ladoB;
    }
    public void setLadoA(int ladoA){
        this.ladoA = ladoA;
    }
    public void setLadoB(int ladoB){
        this.ladoB = ladoB;
    }
}
