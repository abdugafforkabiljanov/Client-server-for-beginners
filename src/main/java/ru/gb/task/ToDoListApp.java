package ru.gb.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<String[]> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Yangi topshiriq qo‘shish\n2. Topshiriqni o‘chirish\n3. Topshiriqlarni ko‘rish\n4. Topshiriqni yangilash\n5. Chiqish");
            System.out.print("Tanlang: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTask(tasks);
                case 2 -> deleteTask(tasks);
                case 3 -> listTasks(tasks);
                case 4 -> updateTask(tasks);
                case 5 -> {
                    System.out.println("Dasturdan chiqildi.");
                    return;
                }
                default -> System.out.println("Noto‘g‘ri tanlov, qaytadan kiriting.");
            }
        }
    }

    public static void addTask(List<String[]> tasks) {
        System.out.print("Topshiriq nomi: ");
        String name = scanner.nextLine();

        String date;
        while (true) {
            System.out.print("Sana (YYYY-MM-DD): ");
            date = scanner.nextLine();
            try {
                LocalDate.parse(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Xato! Sanani YYYY-MM-DD formatida kiriting.");
            }
        }

        String time;
        while (true) {
            System.out.print("Soat (HH:MM): ");
            time = scanner.nextLine();
            try {
                LocalTime.parse(time);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Xato! Vaqtni HH:MM formatida kiriting.");
            }
        }

        String status;
        while (true) {
            System.out.print("Holat (bajarildi/bajarilmadi): ");
            status = scanner.nextLine().toLowerCase();
            if (status.equals("bajarildi") || status.equals("bajarilmadi")) {
                break;
            }
            System.out.println("Xato! Faqat 'bajarildi' yoki 'bajarilmadi' kiriting.");
        }

        tasks.add(new String[]{name, date, time, status});
        System.out.println("Topshiriq qo‘shildi!");
    }

    public static void deleteTask(List<String[]> tasks) {
        listTasks(tasks);
        if (tasks.isEmpty()) return;

        int index;
        while (true) {
            System.out.print("O‘chirish uchun indeksni kiriting: ");
            index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 1 && index <= tasks.size()) {
                break;
            }
            System.out.println("Xato! Indeks 1 dan " + tasks.size() + " gacha bo‘lishi kerak.");
        }

        tasks.remove(index - 1);
        System.out.println("Topshiriq o‘chirildi!");
    }

    public static void listTasks(List<String[]> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Topshiriqlar mavjud emas!");
            return;
        }
        System.out.println("\nTopshiriqlar ro‘yxati:");
        for (int i = 0; i < tasks.size(); i++) {
            String[] task = tasks.get(i);
            System.out.printf("%d. %s - %s %s - %s\n", i + 1, task[0], task[1], task[2], task[3]);
        }
    }

    public static void updateTask(List<String[]> tasks) {
        listTasks(tasks);
        if (tasks.isEmpty()) return;

        int index;
        while (true) {
            System.out.print("Yangilash uchun indeksni kiriting: ");
            index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 1 && index <= tasks.size()) {
                break;
            }
            System.out.println("Xato! Indeks 1 dan " + tasks.size() + " gacha bo‘lishi kerak.");
        }

        String[] task = tasks.get(index - 1);

        System.out.print("Yangi nom (" + task[0] + "): ");
        String newName = scanner.nextLine();

        String newDate = task[1];
        while (true) {
            System.out.print("Yangi sana (" + task[1] + "): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) break;
            try {
                LocalDate.parse(input);
                newDate = input;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Xato! Sanani YYYY-MM-DD formatida kiriting.");
            }
        }

        String newTime = task[2];
        while (true) {
            System.out.print("Yangi soat (" + task[2] + "): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) break;
            try {
                LocalTime.parse(input);
                newTime = input;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Xato! Vaqtni HH:MM formatida kiriting.");
            }
        }

        String newStatus = task[3];
        while (true) {
            System.out.print("Yangi holat (" + task[3] + "): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.isEmpty()) break;
            if (input.equals("bajarildi") || input.equals("bajarilmadi")) {
                newStatus = input;
                break;
            }
            System.out.println("Xato! Faqat 'bajarildi' yoki 'bajarilmadi' kiriting.");
        }

        tasks.set(index - 1, new String[]{
                newName.isEmpty() ? task[0] : newName,
                newDate,
                newTime,
                newStatus
        });

        System.out.println("Topshiriq yangilandi!");
    }
}
