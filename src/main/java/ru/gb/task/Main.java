package ru.gb.task;

public class Main {
    public static void main(String[] args) {

    }
}
/*
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoListApp {
    public static void main(String[] args) {
        List<String[]> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Yangi topshiriq qo‘shish\n2. Topshiriqni o‘chirish\n3. Topshiriqlarni ko‘rish\n4. Topshiriqni yangilash\n5. Chiqish");
            System.out.print("Tanlang: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTask(tasks, scanner);
                case 2 -> deleteTask(tasks, scanner);
                case 3 -> listTasks(tasks);
                case 4 -> updateTask(tasks, scanner);
                case 5 -> {
                    System.out.println("Dasturdan chiqildi.");
                    return;
                }
                default -> System.out.println("Noto‘g‘ri tanlov, qaytadan kiriting.");
            }
        }
    }

    public static void addTask(List<String[]> tasks, Scanner scanner) {
        System.out.print("Topshiriq nomi: ");
        String name = scanner.nextLine();
        System.out.print("Sana (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Soat (HH:MM): ");
        String time = scanner.nextLine();
        System.out.print("Holat (bajarildi/bajarilmadi): ");
        String status = scanner.nextLine();

        tasks.add(new String[]{name, date, time, status});
        System.out.println("Topshiriq qo‘shildi!");
    }

    public static void deleteTask(List<String[]> tasks, Scanner scanner) {
        listTasks(tasks);
        if (tasks.isEmpty()) return;

        System.out.print("O‘chirish uchun indeksni kiriting: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Topshiriq o‘chirildi!");
        } else {
            System.out.println("Noto‘g‘ri indeks!");
        }
    }

    public static void listTasks(List<String[]> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Topshiriqlar mavjud emas!");
            return;
        }
        System.out.println("\nTopshiriqlar ro‘yxati:");
        for (int i = 0; i < tasks.size(); i++) {
            String[] task = tasks.get(i);
            System.out.printf("%d. %s - %s %s - %s\n", i, task[0], task[1], task[2], task[3]);
        }
    }

    public static void updateTask(List<String[]> tasks, Scanner scanner) {
        listTasks(tasks);
        if (tasks.isEmpty()) return;

        System.out.print("Yangilash uchun indeksni kiriting: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < tasks.size()) {
            String[] task = tasks.get(index);

            System.out.print("Yangi nom (" + task[0] + "): ");
            String newName = scanner.nextLine();
            System.out.print("Yangi sana (" + task[1] + "): ");
            String newDate = scanner.nextLine();
            System.out.print("Yangi soat (" + task[2] + "): ");
            String newTime = scanner.nextLine();
            System.out.print("Yangi holat (" + task[3] + "): ");
            String newStatus = scanner.nextLine();

            tasks.set(index, new String[]{
                newName.isEmpty() ? task[0] : newName,
                newDate.isEmpty() ? task[1] : newDate,
                newTime.isEmpty() ? task[2] : newTime,
                newStatus.isEmpty() ? task[3] : newStatus
            });

            System.out.println("Topshiriq yangilandi!");
        } else {
            System.out.println("Noto‘g‘ri indeks!");
        }
    }
}

 */