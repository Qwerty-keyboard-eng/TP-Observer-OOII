package org.example;

public interface Medidor {
    String leerTemperatura();
    void agregarObservador(Observer obs);
    void removerObservador(Observer obs);
}