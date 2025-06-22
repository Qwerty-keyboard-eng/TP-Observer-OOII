package org.example;

public interface MedidorObservable {
    String leerTemperatura();
    void agregarObservador(Observer obs);
    void removerObservador(Observer obs);
}