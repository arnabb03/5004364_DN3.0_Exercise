import java.util.Arrays;

public class ECommerceSearch {

    static class Product {
        private int productId;
        private String productName;
        private String category;

        public Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public String toString() {
            return "Product(id=" + productId + ", name=" + productName + ", category=" + category + ")";
        }
    }

    public static Product linearSearch(Product[] products, int targetId) {
        for (Product product : products) {
            if (product.getProductId() == targetId) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, int targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].getProductId() == targetId) {
                return products[mid];
            } else if (products[mid].getProductId() < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Smartphone", "Electronics"),
            new Product(3, "Coffee Maker", "Home Appliances"),
            new Product(4, "Washing Machine", "Home Appliances"),
            new Product(5, "Headphones", "Electronics")
        };

        Arrays.sort(products, (a, b) -> Integer.compare(a.getProductId(), b.getProductId()));

        int targetIdLinear = 3;
        Product resultLinear = linearSearch(products, targetIdLinear);
        System.out.println("Linear Search Result: " + resultLinear);

        int targetIdBinary = 4;
        Product resultBinary = binarySearch(products, targetIdBinary);
        System.out.println("Binary Search Result: " + resultBinary);
    }
}
