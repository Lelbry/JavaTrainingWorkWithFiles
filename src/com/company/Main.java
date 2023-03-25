package com.company;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "file1.txt";
        String outputFileName = "file2.txt";

        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = inputFileReader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    if (token.matches("\\d+")) {
                        outputFileWriter.write(token + " ");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
                // получаем URL от пользователя
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Введите URL: ");
                String urlString = "";
                try {
                    urlString = reader.readLine();
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении ввода пользователя: " + e.getMessage());
                    System.exit(1);
                }
                try {
                    // подключаемся к URL и считываем содержимое страницы
                    URL url = new URL(urlString);
                    BufferedReader pageReader = new BufferedReader(new InputStreamReader(url.openStream()));

                    StringBuilder pageContents = new StringBuilder();
                    String line;
                    while ((line = pageReader.readLine()) != null) {
                        pageContents.append(line);
                    }

                    // считаем количество тегов <p>
                    String page = pageContents.toString();
                    int numPTags = countPTags(page);

                    // записываем количество тегов <p> в файл
                    FileWriter writer = new FileWriter("ptags.txt");
                    writer.write("Количество тегов <p>: " + numPTags);
                    writer.close();

                    System.out.println("Количество тегов <p> записано в файл ptags.txt.");
                } catch (MalformedURLException e) {
                    System.err.println("Неверный URL: " + e.getMessage());
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Ошибка при чтении содержимого страницы или записи в файл: " + e.getMessage());
                    System.exit(1);
                }
            }

            private static int countPTags(String page) {
                int numPTags = 0;
                int startIndex = 0;
                while ((startIndex = page.indexOf("<p>", startIndex)) != -1) {
                    numPTags++;
                    startIndex += 3;
                }
                return numPTags;
    }
}

