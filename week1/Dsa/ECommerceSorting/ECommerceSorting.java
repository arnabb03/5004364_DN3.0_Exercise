import java.util.Arrays;

public class ECommerceSorting {

    static class Order {
        private int orderId;
        private String customerName;
        private double totalPrice;

        public Order(int orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        @Override
        public String toString() {
            return "Order(id=" + orderId + ", name=" + customerName + ", price=" + totalPrice + ")";
        }
    }

    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "Alice", 250.75),
            new Order(2, "Bob", 450.50),
            new Order(3, "Charlie", 200.00),
            new Order(4, "David", 300.00),
            new Order(5, "Eve", 150.00)
        };

        Order[] ordersForBubbleSort = orders.clone();
        bubbleSort(ordersForBubbleSort);
        System.out.println("Bubble Sort Results:");
        for (Order order : ordersForBubbleSort) {
            System.out.println(order);
        }

        Order[] ordersForQuickSort = orders.clone();
        quickSort(ordersForQuickSort, 0, ordersForQuickSort.length - 1);
        System.out.println("\nQuick Sort Results:");
        for (Order order : ordersForQuickSort) {
            System.out.println(order);
        }
    }
}
