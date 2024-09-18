package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Gib eine Zahl ein: ");
        int userInput = scanner.nextInt();
//________________________________________________________________________________________________________________________
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(String.valueOf(userInput));
            System.out.println("Die Zahl wurde in output.txt geschrieben.");

        }
        catch (Exception e) {
            System.err.println("Fehler beim Schreiben der Datei: " + e.getMessage());
        }
//________________________________________________________________________________________________________________________
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))){
            String line;
            System.out.println("Die Zahl aus der Datei: ");
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
            }

            }catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
//________________________________________________________________________________________________________________________
        System.out.println("Bitte geben Sie den Pfad zu dem zu überprüfenden Verzeichnis an: ");
        String directoryPath = scanner.next();

        Path startPath = Paths.get(directoryPath);

        try {
            Files.walk(startPath)
                    .forEach(path -> {
                        if (Files.isRegularFile(path)) {
                            System.out.println(path.toString());
                        }
                        else if (Files.isDirectory(path)) {
                            System.out.println(path.toString());
                        }
                        else {
                            System.out.println(path.toString() + " ist ein unbekanntes Objekt.");
                        }
                    });
        }catch (IOException e) {
            System.err.println("Fehler beim durchsuchen des Verzeichnisses: " + e.getMessage());
        }
//________________________________________________________________________________________________________________________
        scanner.close();

    }
}