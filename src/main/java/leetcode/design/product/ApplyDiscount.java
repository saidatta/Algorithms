package leetcode.design.product;

import java.util.Map;
import java.util.HashMap;

// https://leetcode.com/problems/apply-discount-every-n-orders/description/?source=submission-ac
class ApplyDiscount {
    private final int n;
    private final int discount;
    private final Map<Integer, Integer> priceMap;
    private int customerCount;

    public ApplyDiscount(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        this.priceMap = new HashMap<>();
        for (int i = 0; i < products.length; i++) {
            this.priceMap.put(products[i], prices[i]);
        }
        this.customerCount = 0;
    }

    public double getBill(int[] product, int[] amount) {
        customerCount++;
        double total = 0.0;
        for (int i = 0; i < product.length; i++) {
            total += priceMap.get(product[i]) * amount[i];
        }

        if (customerCount % n == 0) {
            total = total - (total * discount / 100.0);
        }

        return total;
    }

    public static void main(String[] args) {
        ApplyDiscount cashier =
                new ApplyDiscount(
                        3, 50, new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{100, 200, 300, 400, 300, 200, 100});
        System.out.println(cashier.getBill(new int[]{1, 2}, new int[]{1, 2})); // 500.0
        System.out.println(cashier.getBill(new int[]{3, 7}, new int[]{10, 10})); // 4000.0
        System.out.println(cashier.getBill(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 1, 1, 1, 1, 1, 1})); // 800.0
    }
}
