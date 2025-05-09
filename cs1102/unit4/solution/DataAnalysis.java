import java.util.ArrayList;

public class DataAnalysis {

    static class StockPrice {

        static double calculateAveragePrice(double[] prices) {
            // Method to calculate average stock price
            double sum = 0;
            for (double price : prices) {
                sum += price;
            }
            return sum / prices.length;
        }

        static double findMaximumPrice(double[] prices) {
            // Method to find maximum stock price
            double maxPrice = prices[0];
            for (double price : prices) {
                if (price > maxPrice) {
                    maxPrice = price;
                }
            }
            return maxPrice;
        }

        static int countOccurrences(double[] prices, double targetPrice) {
            // Method to count occurrences of a specific stock price
            int count = 0;
            for (double price : prices) {
                if (price == targetPrice) {
                    count++;
                }
            }
            return count;
        }

        static ArrayList<Double> computeCumulativeSum(ArrayList<Double> prices) {
            // Method to compute cumulative sum of stock prices
            ArrayList<Double> cumulativeSum = new ArrayList<>();
            double sum = 0;
            for (double price : prices) {
                sum += price;
                cumulativeSum.add(sum);
            }
            return cumulativeSum;
        }

        public StockPrice() {
            // Constructor
        }
    }

    public static void main(String[] args) {

        // Example usage of the StockPrice class
        double[] prices = { 100.5, 101.0, 102.5, 100.0, 99.5, 98.0, 100.0, 101.5, 102.0, 103.0 };
        double averagePrice = StockPrice.calculateAveragePrice(prices);
        double maxPrice = StockPrice.findMaximumPrice(prices);
        int occurrences = StockPrice.countOccurrences(prices, 100.0);

        ArrayList<Double> priceList = new ArrayList<>();
        for (double price : prices) {
            priceList.add(price);
        }
        ArrayList<Double> cumulativeSum = StockPrice.computeCumulativeSum(priceList);

        System.out.println("Average Price: " + averagePrice);
        System.out.println("Maximum Price: " + maxPrice);
        System.out.println("Occurrences of 100.0: " + occurrences);
        System.out.println("Cumulative Sum: " + cumulativeSum);
    }
}