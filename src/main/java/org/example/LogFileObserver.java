package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFileObserver implements Observer {
    private final String logFilePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogFileObserver(String logFilePath) {
        this.logFilePath = logFilePath;
        File logFile = new File(logFilePath);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear el log: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(String temperatura, LocalDateTime fecha) {
        String logEntry = String.format("%s - Temperatura: %s%n", fecha.format(FORMATTER), temperatura);
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(logEntry);
            System.out.println("Log: Guardado en " + logFilePath + ": " + logEntry.trim());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}