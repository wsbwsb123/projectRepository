package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Loop {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Choice usersChoice = null;
        while (usersChoice != Choice.EXIT) {
            showMenu();
            usersChoice = getChoice(scanner);

            if (usersChoice != null) {
                switch (usersChoice) {
                    case DOWNLOAD:
                        //doSth
                        break;
                    case COUNT_LETTERS:
                        countLetters();
                        break;
                    case COUNT_WORDS:
                        //doSth
                        break;
                    case COUNT_PUNCTUATION_MARKS:
                        //doSth
                        break;
                    case COUNT_SENTENCES:
                        //doSth
                        break;
                    case GENERATE_RAPORT:
                        //doSth
                        break;
                    case SAVE_STATS:
                        //doSth
                        break;
                    case EXIT:
                        //doSth
                        break;
                }
            }

        }
        scanner.close();

    }

    private void countLetters() {
        String file = "2.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        String currentLine = "";
        try {
            currentLine = reader.readLine();
            while (currentLine != null) {
                builder.append(currentLine);
                currentLine = reader.readLine();
            }
            reader.close();
            String text = builder.toString();
            int numberOfLetters = 0;

            for (int i = 0; i < text.length(); i++) {
                if ("abcdefghijklmnopqrstuvwxyz".contains(Character.toString(text.charAt(i)))) {
                    numberOfLetters++;
                }
            }

            System.out.println("Liczba liter w dokumencie: " + numberOfLetters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Choice getChoice(Scanner scanner) {
        Choice usersChoice = null;
        int[] possibleChoices = {1, 2, 3, 4, 5, 6, 7, 8};
        while (!scanner.hasNextInt()) {
            System.out.println("*************************** Wprowadzona wartość musi być liczbą calkowitą ***************************");
            scanner.next();
        }
        int nr = scanner.nextInt();
        scanner.nextLine();

        boolean contains = IntStream.of(possibleChoices).anyMatch(x -> x == nr);
        if (contains) {
            usersChoice = Choice.intToChoice(nr);
        } else {
            System.out.println("*************************** Wprowadzona liczba musi mieścić być między 1 a 8: ***************************");
            System.out.println();

        }

        return usersChoice;
    }

    private void showMenu() {
        for (Choice choice : Choice.values()) {
            System.out.println(choice);
        }
    }

    private enum Choice {
        DOWNLOAD(1, "Pobierz plik z internetu"),
        COUNT_LETTERS(2, "Zlicz liczbę liter w pobranym pliku"),
        COUNT_WORDS(3, "Zlicz liczbę wyrazów w pliku"),
        COUNT_PUNCTUATION_MARKS(4, "Zlicz liczbę znaków interpunkcyjnych w pliku"),
        COUNT_SENTENCES(5, "Zlicz liczbę zdań w pliku"),
        //DODANE
        GENERATE_RAPORT(6, "Wygeneruj raport o użyciu liter (A-Z)"),
        SAVE_STATS(7, "Zapisz statystyki z punktów 2-5 do pliku statystyki.txt"),
        EXIT(8, "Wyjście z programu");

        private int nr;
        private String description;

        Choice(int nr, String description) {
            this.nr = nr;
            this.description = description;
        }

        static Choice intToChoice(int nr) {
            Choice choice = Choice.values()[nr - 1];
            return choice;
        }

        @Override
        public String toString() {
            return nr + ") " + description;
        }
    }


}