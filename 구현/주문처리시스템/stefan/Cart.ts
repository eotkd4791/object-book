import { Product } from "./Product";
import { ArrayList } from "./utils";

// 장바구니
export class Cart {
  private readonly items = new ArrayList<Product>();

  public isEmpty() {
    return this.items.isEmpty();
  }
  public add(product: Product) {
    if (product.getQuantity() > 0) {
      this.items.add(product);
    }
    throw new Error("수량이 부족합니다.");
  }
  public remove(product: Product) {
    this.items.remove(product);
  }
  public getTotalPrice() {
    let totalPrice = 0;
    for (const item of this.items) {
      totalPrice += item.getPrice() * item.getQuantity();
    }
    return totalPrice;
  }
}
