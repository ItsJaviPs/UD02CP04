public class Pistas {
    private final boolean[] disponibles;

    public Pistas(int numeroPistas) {
        disponibles = new boolean[numeroPistas];
        for (int i = 0; i < disponibles.length; i++) {
            disponibles[i] = true;          // al principio todas libres
        }
    }

    // Ocupa una pista libre y devuelve su índice (0 o 1).
    // Si no hay ninguna libre, la hebra espera.
    public synchronized int ocupar() {
        int pista = buscarLibre();
        while (pista == -1) {               // todas ocupadas: esperar
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return -1;
            }
            pista = buscarLibre();          // al despertar, volver a mirar
        }
        disponibles[pista] = false;         // la marco como ocupada
        return pista;
    }

    // Libera la pista y avisa a los vehículos que esperan.
    public synchronized void liberar(int pista) {
        disponibles[pista] = true;
        notifyAll();
    }

    // Devuelve el índice de una pista libre, o -1 si no hay ninguna.
    private int buscarLibre() {
        for (int i = 0; i < disponibles.length; i++) {
            if (disponibles[i]) {
                return i;
            }
        }
        return -1;
    }
}
