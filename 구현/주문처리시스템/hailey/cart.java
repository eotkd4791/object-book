import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        if (product.getQuantity() > 0) {
            items.add(product);
        } else {
            // 상품 수량이 0인 경우 예외 처리
        }
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : items) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }
}
