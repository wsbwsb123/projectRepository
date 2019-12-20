package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

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
                    FileDownloader downloaded;
                    try {
                        downloaded = new FileDownloader(new URL("https://s3.zylowski.net/public/input/2.txt"));
                        downloaded.save("2.txt");
                        System.out.println("plik 2.txt został pobrany.");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Loop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        break;
                    case COUNT_LETTERS:
                        System.out.println("Liczba liter w dokumencie: " + countLetters();
                        break;
                    case COUNT_WORDS:
                        System.out.println("Liczba wyrazów w dokumencie: " + countWords());
                        break;
                    case COUNT_PUNCTUATION_MARKS:
                        System.out.println("Liczba znaków interpunkcyjnych w dokumencie: " + countPunctuations());
                        break;
                    case COUNT_SENTENCES:
                        System.out.println("Liczba zdań w dokumencie: " + countSentences());
                        break;
                    case GENERATE_RAPORT:
                        generateReport();
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

    private void SaveStats() {
        try (PrintWriter out = new PrintWriter("statystyki.txt")) {
          out.println("Liczba liter w dokumencie: " + countLetters() + "\n");
          out.println("Liczba wyrazów w dokumencie: " + countWords() + "\n");
          out.println("Liczba znaków interpunkcyjnych w dokumencie: " + countPunctuations() + "\n");
          out.println("Liczba zdań w dokumencie: " + countSentences() + "\n");
        }
        
    }
    private String countLetters() {
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

            return numberOfLetters;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateReport() {
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
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            for (int j = 0; j < alphabet.length(); j++) {
                for (int i = 0; i < text.length(); i++) {
                    if (Character.toString(alphabet.charAt(j)).equalsIgnoreCase(Character.toString(text.charAt(i)))) {
                        numberOfLetters++;
                    }
                }
                System.out.println(Character.toString(alphabet.charAt(j)).toUpperCase() + ": " + numberOfLetters);
                numberOfLetters = 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String countWords() {
        String file = "2.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String s = "";
        int wordCount = 0;

        try {
            s = reader.readLine();
            while (s != null) {
                String[] arr = s.trim().split(" ");
                wordCount += arr.length;
                s = reader.readLine();
            }
        reader.close();

        return wordCount;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String countPunctuations() {
    	String file = "2.txt";
    	BufferedReader reader = null;
    	try {
    		reader = new BufferedReader(new FileReader(file));
    	}
    	catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	StringBuilder builder = new StringBuilder();
    	String currentLine = "";
    	try {
    		currentLine = reader.readLine();
    		while(currentLine!=null) {
    			builder.append(currentLine);
    			currentLine = reader.readLine();
    		}
    		reader.close();
    		String text = builder.toString();
    		int numberOfPunctuations = 0;

    		for(int i = 0; i < text.length(); i++) {
    			if(".;,:-?!\"()".contains(Character.toString(text.charAt(i)))) {
    				numberOfPunctuations++;
    			}
    		}

        return numberOfPunctuations;
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    private String countSentences() {
      String file = "2.txt";
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(new FileReader(file));
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      StringBuilder builder = new StringBuilder();
      String currentLine = "";
      try {
        currentLine = reader.readLine();
        while(currentLine !=null) {
          builder.append(currentLine);
          currentLine = reader.readLine();
        }
        reader.close();
        String text = builder.toString();
        int numberOfSentences = 0;

        String[] sentences = text.trim().split("\\.");
        numberOfSentences = sentences.length;

        return numberOfSentences;
      }
      catch (IOException e) {
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
            System.out.println("*************************** Wprowadzona liczba musi mieÅ›ciÄ‡ byÄ‡ miÄ™dzy 1 a 8: ***************************");
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
        COUNT_LETTERS(2, "Zlicz liczbÄ™ liter w pobranym pliku"),
        COUNT_WORDS(3, "Zlicz liczbÄ™ wyrazÃ³w w pliku"),
        COUNT_PUNCTUATION_MARKS(4, "Zlicz liczbÄ™ znakÃ³w interpunkcyjnych w pliku"),
        COUNT_SENTENCES(5, "Zlicz liczbÄ™ zdaÅ„ w pliku"),
        //DODANE
        GENERATE_RAPORT(6, "Wygeneruj raport o uÅ¼yciu liter (A-Z)"),
        SAVE_STATS(7, "Zapisz statystyki z punktÃ³w 2-5 do pliku statystyki.txt"),
        EXIT(8, "WyjÅ›cie z programu");

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
