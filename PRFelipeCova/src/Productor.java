import java.util.concurrent.*;
import java.util.Random;

/**
* Clase productor que representa el hilo de un productor
*/
public class Productor extends Thread {

  // identificador del hilo
  private int id;
  // buffer compartido
  private final BlockingQueue<Integer> buffer;

  /**
  * Crea un nuevo hilo productor con su id y el buffer compartido
  */
  public Productor(int id, BlockingQueue<Integer> buffer) {
    this.buffer = buffer;
    this.id = id;
  }

  @Override
  public void run() {

    for(int i = 0; i < ProductoresConsumidores.ITERACIONES; i++){
      try {
        // crea un numero aleatorio para agregar al buffer
        int number = new Random().nextInt(100);
        // el metodo put del buffer permite agregar usando sincronización
        buffer.put(number);
        // imprime el elemento a agregar (sección crítica)
        System.out.printf("Productor: P%d produce elemento: %d\n", id, number);
      } catch(InterruptedException e) {
        System.err.println(e);
      }

    }
    System.out.printf("******* Hilo P%d termino su ejecucion *******\n", id);
  }
}
