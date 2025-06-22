package org.example;

public class MedidorBase implements MedidorObservable {
    private String temperatura;
    private ClimaOnline clima;

    public MedidorBase(ClimaOnline clima) {
        this.clima = clima;
    }

    @Override
    public String leerTemperatura() {
        this.temperatura = this.clima.temperatura();
        System.out.println("[MedidorBase] Temperatura leída: " + this.temperatura);
        return this.temperatura;
    }

    @Override
    public void removerObservador(Observer obs) {

    }

    @Override
    public void agregarObservador(Observer obs) {

    }
}