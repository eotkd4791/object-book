// 상품
export class Product {
  constructor(
    private readonly id: number,
    private readonly name: string,
    private readonly price: number,
    private readonly quantity: number
  ) {}

  public getId() {
    return this.id;
  }
  public getName() {
    return this.name;
  }
  public getPrice() {
    return this.price;
  }
  public getQuantity() {
    return this.quantity;
  }
}
