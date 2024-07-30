public class FinancialForecasting {

    public static double calculateFutureValueRecursive(double initialAmount, double growthRate, int years) {
        if (years <= 0) {
            return initialAmount;
        }
        return calculateFutureValueRecursive(initialAmount * (1 + growthRate), growthRate, years - 1);
    }

    public static double calculateFutureValueIterative(double initialAmount, double growthRate, int years) {
        double futureValue = initialAmount;
        for (int i = 0; i < years; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double initialAmount = 1000.0;
        double growthRate = 0.05; // 5% growth rate
        int years = 10;

        double futureValueRecursive = calculateFutureValueRecursive(initialAmount, growthRate, years);
        double futureValueIterative = calculateFutureValueIterative(initialAmount, growthRate, years);

        System.out.println("Future Value after " + years + " years (Recursive): " + futureValueRecursive);
        System.out.println("Future Value after " + years + " years (Iterative): " + futureValueIterative);
    }
}
