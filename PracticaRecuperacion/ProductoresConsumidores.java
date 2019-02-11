import java.util.concurrent.*;

public class ProductoresConsumidores {

  // numero  de veces que itera cada hilo
  public static int ITERACIONES = 10;

  public static void main(String[] args){

    // el numero de productores
    int n = 0;
    // el numero de consumidores
    int m = 0;
    // el tamaño del buffer
    int l = 0;

    // verifica que los argumentos sean válidos (enteros mayores a 0)
    try{
      n = Integer.parseInt(args[0]);
      m = Integer.parseInt(args[1]);
      l = Integer.parseInt(args[2]);
      if(n < 1 || m < 1 || l < 1)
        throw(new RuntimeException());
    } catch(NumberFormatException e){
      System.err.println("Algun argumento es invalido. Los argumentos deben ser "
      + "numeros enteros.");
    }catch(RuntimeException e){
      System.err.println("Algun argumento es invalido. Los argumentos deben ser "
      + "mayores a 0.");
    }

    // inicializar la lista o buffer compartido con tamaño máximo l
    BlockingQueue<Integer> buffer = new LinkedBlockingQueue<Integer>(l);
    Productor[] productores = new Productor[n];
    Consumidor[] consumidores = new Consumidor[m];

    // ejecución de los n productores
    for(int i = 0; i < n; i++){
      // inicializar el productor con su id y el buffer
      productores[i] = new Productor(i + 1, buffer);
      productores[i].start();
    }

    // ejecución de los m productores
    for(int i = 0; i < m; i++){
      // inicializar el consumidor con su id y el buffer
      consumidores[i] = new Consumidor(i + 1, buffer);
      consumidores[i].start();
    }

    // espera a que los hilos terminen su ejecucion
    try{
      for(int i = 0; i < n; i++){
        productores[i].join();
      }

      for(int i = 0; i < m; i++){
        consumidores[i].join();
      }
    }catch(InterruptedException e) {
      System.err.println(e);
    }
    System.out.println("\nTodos los hilos han terminado su ejecución");
  }
}
