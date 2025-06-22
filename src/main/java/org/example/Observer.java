package org.example;

import java.time.LocalDateTime;

public interface Observer {
    void actualizar(String temperatura, LocalDateTime fecha);
}