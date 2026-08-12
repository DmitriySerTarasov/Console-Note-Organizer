package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Note> notes = loadNotesFromFile("notes.txt");


        while (true) {
            System.out.println("======");
            System.out.println("1. Добавить заметку: ");
            System.out.println("2. Показать все заметки");
            System.out.println("3. Выйти");
            System.out.println("4. Удалить заметку");
            System.out.println("Выберите действие");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index == 1) {
                System.out.println("Текст заметки: ");
                String text = scanner.nextLine();

                String date = LocalDate.now().toString();
                if (text.isEmpty()) {
                    System.out.println("Текст не может быть пустым");
                } else {
                    notes.add(new Note(text, date));
                }
                System.out.println("Заметка добавлена");


            } else if (index == 2) {
                printNotes(notes);


            } else if (index == 3) {
                saveNotesToFile(notes, "notes.txt");
                break;

            } else if (index == 4) {
                if (notes.isEmpty()) {
                    System.out.println("Заметок нет для удаления");
                } else {
                    printNotes(notes);
                    System.out.println("Введите номер заметки: ");
                    int number = scanner.nextInt();
                    scanner.nextLine();
                    if (number >= 1 && number <= notes.size()) {
                        notes.remove(number - 1);
                        System.out.println("Заметка удалена");
                    } else {
                        System.out.println("Не верный номер");
                    }
                }
            } else {
                System.out.println("Неверный ввод");
            }

            System.out.println("========");
        }


    }


    public static void printNotes(ArrayList<Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("Заметок нет");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }

    }

    public static void saveNotesToFile(ArrayList<Note> notes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Note note :notes) {
                writer.write(note.getDate() + " | " + note.getContent() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка сохранения " +e.getMessage());
        }
    }
    public static ArrayList<Note> loadNotesFromFile(String filename) {
        ArrayList<Note> notes = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return notes;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 2) {
                    String date = parts[0];
                    String content = parts[1];
                    notes.add(new Note(content, date));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения " + e.getMessage());
        }
        return notes;

    }
}