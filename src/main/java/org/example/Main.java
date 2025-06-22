package org.example;

public class Main {
    public static void main(String[] args) {
        ClimaOnline weatherService = new WeatherChannelService();
        MedidorObservable medidorBase = new MedidorBase(weatherService);
        MedidorObservable medidorConObservadores = new MedidorObservableDecorator(medidorBase);

        Observer logObserver = new LogFileObserver("temperatura_log_decorator.txt");
        Observer consoleObserver = new ConsoleObserver();

        medidorConObservadores.agregarObservador(logObserver);
        medidorConObservadores.agregarObservador(consoleObserver);

        System.out.println("Iniciando lecturas de temperatura con Decorator + Observer...");
        for (int i = 0; i < 5; i++) {
            System.out.println("\n--- Lectura #" + (i + 1) + " ---");
            medidorConObservadores.leerTemperatura();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Hilo interrumpido.");
            }
        }
        System.out.println("\nLecturas finalizadas.");
    }
}