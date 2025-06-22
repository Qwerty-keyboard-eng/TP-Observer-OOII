package org.example;

import java.time.LocalDateTime;

public class ConsoleObserver implements Observer {
    @Override
    public void actualizar(String temperaturaStr, LocalDateTime fecha) {
        System.out.println("Consola: Nueva lectura de temperatura: " + temperaturaStr);

        try {
            String tempValue = temperaturaStr.split(" ")[0];
            double temperatura = Double.parseDouble(tempValue);

            if (temperatura < 12) {
                System.out.println("Consola: Hace frio, se encenderá la caldera.");
            } else if (temperatura > 17) {
                System.out.println("Consola: Hace calor, se encenderá el aire acondicionado.");
            } else {
                System.out.println("Consola: Temperatura agradable.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Consola: No se pudo parsear la temperatura: " + temperaturaStr);
        }
    }
}