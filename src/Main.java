import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваш текущий вес в кг: ");
        double weight = scanner.nextDouble();

        System.out.print("Введите ваш рост в метрах: ");
        double height = scanner.nextDouble();

        System.out.print("Введите ваш возраст: ");
        int age = scanner.nextInt();

        double imt = calculateImt(weight, height);

        double idealWeight = calculateIdealWeight(height);

        double dayCal = calculateDayNormCal(weight, height, age, imt);

        switch (recommendation(imt)) {
            case "bigWeight":
                double lostWeight = weight - idealWeight;
                System.out.println("Рекомендуется снизить вес для достижения идеального веса.");
                System.out.println("Идеальный вес: " + idealWeight + " кг.");
                System.out.println("Необходимо сбросить: " + lostWeight + " кг.");
                System.out.println("Рекомендуемый дневной прием калорий: " + dayCal + " калорий.");
                break;

            case "smallWeight":
                double weightGain = idealWeight - weight;
                System.out.println("Рекомендуется увеличить потребление пищи для набора веса.");
                System.out.println("Идеальный вес: " + idealWeight + " кг.");
                System.out.println("Необходимо набрать: " + weightGain + " кг.");
                System.out.println("Рекомендуемый дневной прием калорий: " + dayCal + " калорий.");
                break;

            default:
                System.out.println("Ваш вес находится в пределах нормы.");
                System.out.println("Идеальный вес: " + idealWeight + " кг.");
                System.out.println("Рекомендуемый дневной прием калорий: " + dayCal + " калорий.");
                break;
        }
    }

    private static double calculateImt(double weight, double height) {
        return weight / Math.pow(height, 2);
    }

    private static double calculateIdealWeight(double height) {
        return 24.9 * Math.pow(height, 2);
    }

    private static double calculateDayNormCal(double weight, double height, int age, double imt) {
        double dayNormCal = 655 + (9.6 * weight) + (1.8 * height * 100) - (4.7 * age);
        
        if (imt > 24.9) {
            return dayNormCal - 500;
        } else if (imt < 18.5) {
            return dayNormCal + 500;
        } else {
            return dayNormCal;
        }
    }

    private static String recommendation(double imt) {
        if (imt > 24.9) {
            return "bigWeight";
        } else if (imt < 18.5) {
            return "smallWeight";
        } else {
            return "normWeight";
        }
    }
}
