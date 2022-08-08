import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        printMenu();
        int userInput = scanner.nextInt();
        //пока пользователь не вышел из приложения, исполняем команды и печатаем меню
        while (userInput != 0) {
            try {
                performInputInstructions(userInput, scanner, stepTracker);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }

    public static void printMenu() {
        System.out.println("1-Ввести количество шагов за определённый день");
        System.out.println("2-Напечатать статистику за определённый месяц");
        System.out.println("3-Изменить цель по количеству шагов в день");
        System.out.println("0-Выйти из приложения");
    }

    public static void performInputInstructions(int userInput, Scanner scanner, StepTracker stepTracker) {
        if (userInput == 1) {
            int month = readUserInput("Введите номер месяца с 0", scanner);
            int day = readUserInput("Введите день начиная с 0", scanner);
            int stepsCount = readUserInput("Введите количество пройденных шагов", scanner);
            //Сохраняем количество пройденных шагов
            stepTracker.saveStepsCount(month, day, stepsCount);
        } else if (userInput == 2) {
            int month = readUserInput("Введите номер месяца с 0", scanner);
            //выводим статистику
            stepTracker.printStatByMonth(month);
        } else if (userInput == 3) {
            //изменяем дневную цель шагов
            stepTracker.changeDailyStepGoal(readUserInput("Введите новую цель шагов", scanner));
        } else {
            System.out.println("Такой команды не существует. Выберите что-то другое");
        }
    }

    public static int readUserInput(String message, Scanner scanner) {
        System.out.println(message);
        return scanner.nextInt();
    }
}
