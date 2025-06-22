package org.example;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MedidorObservableDecorator extends MedidorDecorator {
    private List<Observer> observadores = new ArrayList<>();

    public MedidorObservableDecorator(MedidorObservable decoratedMedidor) {
        super(decoratedMedidor);
    }

    @Override
    public String leerTemperatura() {
        String temperaturaLeida = decoratedMedidor.leerTemperatura();
        notificarObservadores(temperaturaLeida, LocalDateTime.now());
        return temperaturaLeida;
    }

    @Override
    public void agregarObservador(Observer obs) {
        this.observadores.add(obs);
        System.out.println("[MedidorObservableDecorator] Observador agregado: " + obs.getClass().getSimpleName());
    }

    @Override
    public void removerObservador(Observer obs) {
        this.observadores.remove(obs);
        System.out.println("[MedidorObservableDecorator] Observador removido: " + obs.getClass().getSimpleName());
    }

    private void notificarObservadores(String temperatura, LocalDateTime fecha) {
        for (Observer obs : observadores) {
            obs.actualizar(temperatura, fecha);
        }
    }
}