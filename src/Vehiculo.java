import java.util.Random;

public class Vehiculo extends Thread {
    private final String nombre;
    private final Pistas pistas;

    public Vehiculo(String nombre, Pistas pistas) {
        this.nombre = nombre;
        this.pistas = pistas;
    }

    @Override
    public void run() {
        // 1. Pedir una pista (si no hay libre, espera dentro de ocupar())
        int pista = pistas.ocupar();
        if (pista == -1) {
            return;                                   // hebra interrumpida
        }
        System.out.println(nombre + " ha entrado en la pista " + (pista + 1) + ".");

        // 2. Hacer la prueba durante un tiempo aleatorio (2 a 5 segundos)
        long inicio = System.currentTimeMillis();
        try {
            int duracion = 2000 + new Random().nextInt(3001);
            Thread.sleep(duracion);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long fin = System.currentTimeMillis();

        // 3. Mostrar el tiempo, salir y liberar la pista
        System.out.println(nombre + " completó la prueba en " + (fin - inicio) / 1000.0 + " segundos.");
        System.out.println(nombre + " ha salido de la pista " + (pista + 1) + ".");
        pistas.liberar(pista);
    }
}