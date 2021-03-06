
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Algoritmo de Kessels para dos hilos. Donde en cada hilo se iteran 20 veces,
 * compitiendo en cada iteración para entrar a su sección critica y utilizando
 * el algoritmo de Kessels para manejar la exclusión mutua.
 *
 * @author Cova Pacheco Felipe de Jesús.
 */
public class Kessels {

    //1 es true, 0 es false;
    private final AtomicIntegerArray turn = new AtomicIntegerArray(2);
    private final AtomicIntegerArray b = new AtomicIntegerArray(2);

    /**
     * Inicializa los valores requeridos para el funcionamiento del algoritmo de
     * Kessels.
     */
    public Kessels() {
        //(indice, valor)
        this.b.set(0, 0);
        this.b.set(1, 0);
    }

    /**
     * Comienza la ejecución del algoritmo de Kessels.
     *
     * @param id Identificador del hilo con el que se ejecutara el algoritmo,
     * este solo puede ser 0 o 1.
     * @param seccionCritica Sección critica.
     */
    public void comenzar(int id, SeccionCritica seccionCritica) {
        AtomicIntegerArray local = new AtomicIntegerArray(2);
        int otro = (id + 1) % 2; //Identificador del otro hilo.
        b.set(id, 1);
        if (id == 0) {
            local.set(id, turn.get(1));
        }
        if (id == 1) {
            local.set(id, 1 - turn.get(0));
        }
        turn.set(id, local.get(id));
        while (!(b.get(otro) == 0 || local.get(id) != this.turn.get(otro))) {
            //Esperar            
        }
        //Inicio sección critica.
        System.out.println("Comenzo sección critica del algoritmo de Kessels "
                + "del hilo: " + id);
        for (int i = 0; i < 20; i++) {
            seccionCritica.ingresar();
        }
        System.out.println("Finalizo sección critica del algoritmo de Kessels "
                + "del hilo: " + id);
        //Fin sección critica.      
        b.set(id, 0);
    }

}
