import java.util.concurrent.*;

/**
* Clase consumidor que representa el hilo de un consumidor
*/
public class Consumidor extends Thread {

  // identificador del hilo
  private int id;
  // buffer compartido
  private final BlockingQueue<Integer> buffer;

  /**
  * Crea un nuevo hilo consumidor con su id y el buffer compartido
  */
  public Consumidor(int id, BlockingQueue<Integer> buffer) {
    this.buffer = buffer;
    this.id = id;
  }

  @Override
  public void run() {
    for(int i = 0; i < ProductoresConsumidores.ITERACIONES; i++) {
      try{
        // obtiene el elemento del buffer usando sincronizacion
        int num = buffer.take();
        // imprime el elemento obtenido (sección crítica)
        System.out.printf("Consumidor: C%d consume elemento: %d\n", id, num);
      } catch(InterruptedException e) {
        System.err.println(e);
      }
    }
    System.out.printf("******* Hilo C%d termino su ejecucion *******\n", id);
  }
}
