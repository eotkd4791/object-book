class Cart {
    private val items = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        items.add(item)
    }

    fun totalPrice(): Int = items.sumOf { it.totalPrice() + it.product.deliveryCharge }

    fun isEmpty(): Boolean = items.isEmpty()

    fun validateNotEmpty() {
        if (isEmpty()) throw CustomError.EmptyCart
    }
    fun getItems(): List<CartItem> = items
}

data class CartItem(
    val product: Product, 
    var quantity: Int
) {
    fun totalPrice(): Int = product.price * quantity
}
