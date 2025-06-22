package org.example;
import java.time.LocalDateTime;

public abstract class MedidorDecorator implements MedidorObservable {
    protected MedidorObservable decoratedMedidor;

    public MedidorDecorator(MedidorObservable decoratedMedidor) {
        this.decoratedMedidor = decoratedMedidor;
    }

    @Override
    public String leerTemperatura() {
        return decoratedMedidor.leerTemperatura();
    }
 }
