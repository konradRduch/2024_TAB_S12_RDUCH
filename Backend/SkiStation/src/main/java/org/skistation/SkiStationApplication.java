package org.skistation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Ski Station application.
 */
@SpringBootApplication
public class SkiStationApplication
{
    /**
     * The main method that starts the Ski Station application.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(SkiStationApplication.class, args);
    }
}