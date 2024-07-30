public class InventoryManagementSystemTest {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        Product product1 = new Product("P001", "Laptop", 10, 999.99);
        Product product2 = new Product("P002", "Smartphone", 20, 499.99);

        manager.addProduct(product1);
        manager.addProduct(product2);

        System.out.println("All products:");
        manager.displayAllProducts();

        Product updatedProduct = new Product("P001", "Laptop", 8, 899.99);
        manager.updateProduct("P001", updatedProduct);

        System.out.println("Updated product:");
        System.out.println(manager.getProduct("P001"));

        manager.deleteProduct("P002");

        System.out.println("All products after deletion:");
        manager.displayAllProducts();
    }
}
