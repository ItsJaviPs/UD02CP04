public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pistas pistas = new Pistas(2);              // una sola instancia, 2 pistas

        Vehiculo[] vehiculos = new Vehiculo[4];
        for (int i = 0; i < vehiculos.length; i++) {
            vehiculos[i] = new Vehiculo("Vehículo " + (i + 1), pistas);
        }

        for (Vehiculo v : vehiculos) {
            v.start();                              // lanzar los 4 a la vez
        }

        for (Vehiculo v : vehiculos) {
            v.join();                               // esperar a que terminen
        }
    }
}
