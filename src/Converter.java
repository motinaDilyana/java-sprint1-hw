public class Converter {
    final static int ONE_STEP_CENTIMETERS = 75;
    final static int ONE_STEP_CALORIES = 50;

    public static int convertDistance(int steps) {
        return (steps * ONE_STEP_CENTIMETERS)/1000;
    }

    public static int convertToCall(int steps) {
        return (steps * ONE_STEP_CALORIES)/1000;
    }
}
