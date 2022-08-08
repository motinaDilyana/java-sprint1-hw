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
                if (userInput == 1) {
                    System.out.println("Введите номер месяца с 0");
                    int month = scanner.nextInt();
                    System.out.println("Введите день");
                    int day = scanner.nextInt();
                    System.out.println("Введите количество пройденных шагов");
                    int stepsCount = scanner.nextInt();
                    //Сохраняем количество пройденных шагов
                    stepTracker.saveStepsCount(month, day, stepsCount);
                } else if (userInput == 2) {
                    System.out.println("Введите номер месяца с 0");
                    int month = scanner.nextInt();
                    //выводим статистику
                    stepTracker.printStatByMonth(month);
                } else if (userInput == 3) {
                    System.out.println("Введите новую цель шагов");
                    //изменяем дневную цель шагов
                    stepTracker.changeDailyStepGoal(scanner.nextInt());
                } else {
                    System.out.println("Такой команды не существует. Выберите что-то другое");
                }
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
}
