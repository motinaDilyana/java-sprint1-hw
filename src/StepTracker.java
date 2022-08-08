public class StepTracker {
    int dailyStepsGoal = 10000;
    MonthData[] monthToData;

    public StepTracker() {
        monthToData = new MonthData[12];
        //инициализируем дни каждого месяца
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    class MonthData {
        int[] dailyStepsCount = new int[30];
    }

    public void changeDailyStepGoal(int newStepGoal) {
        if(newStepGoal < 0) {
            throw new IllegalArgumentException("Значение не может быть отрицательным");
        }

        this.dailyStepsGoal = newStepGoal;
    }

    void saveStepsCount(int month, int day, int steps) {
        if(steps < 0) {
            throw new IllegalArgumentException("Значение не может быть отрицательным");
        }
        if(month < 0 || month > 11) {
            throw new IllegalArgumentException("Месяц не может быть меньше нуля или больше 11");
        }
        if(day < 0 || day > 29) {
            throw new IllegalArgumentException("День не может быть меньше нуля или больше 29");
        }
        monthToData[month].dailyStepsCount[day] = steps;
    }

    void printStatByMonth(int month) {
        if(month < 0 || month > 11 ) {
            throw new IllegalArgumentException("Месяц не может быть меньше нуля или больше 11");
        }
        int stepsSum = calculateStepsSum(monthToData[month].dailyStepsCount);
        int[] monthData = monthToData[month].dailyStepsCount;
        //выводим статистику
        printDailyStat(monthData);
        System.out.println();
        System.out.println("Пройдено шагов за месяц: " + stepsSum);
        System.out.println("Среднее кол-во шагов в этом месяце " + stepsSum/monthData.length);
        System.out.println("Максимальное пройденное количество шагов в месяце " + calculateMaxSteps(monthData));
        //конвертируем при помощи класса Converter шаги в пройденную дистанцию
        System.out.println("Пройденная дистанция (в км) " + Converter.convertDistance(stepsSum));
        //конвертируем при помощи класса Converter шаги в килокалории
        System.out.println("Количество сожжённых килокалорий " + Converter.convertToCall(stepsSum));
        System.out.println("Лучшая серия " + calculateBestDaysSeries(monthData));
    }

    void printDailyStat(int[] monthData) {
        for (int i = 0; i<monthData.length; i++) {
            System.out.print(i + 1 + " день: " + monthData[i] + ", ");
        }
    }

    public int calculateStepsSum(int[] monthData) {
        int stepsSum = 0;
        for (int monthDatum : monthData) {
            stepsSum = stepsSum + monthDatum;
        }

        return stepsSum;
    }

    public int calculateMaxSteps(int[] monthData) {
        int max = 0;
        for (int i = 1; i<monthData.length; i++) {
            if(monthData[i-1] > max) {
                max = monthData[i-1];
            }
        }

        return max;
    }

    public int calculateBestDaysSeries(int[] monthData) {
        int daysCount = 0;
        int maxDaysCount = 0;
        for (int monthDatum : monthData) {
            if (monthDatum >= dailyStepsGoal) {
                daysCount++;
            } else {
                if (daysCount > maxDaysCount) {
                    maxDaysCount = daysCount;
                }
                daysCount = 0;
            }
        }

        if(daysCount > maxDaysCount) {
            maxDaysCount = daysCount;
        }

        return maxDaysCount;
    }
}
