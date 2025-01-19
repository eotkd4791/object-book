data class Product(
    val name: String,
    val price: Int,
    val quantity: Int,
    val deliveryCharge: Int // 상품별 배송비 (0, 3000, 5000)
) {
    init {
        // 상품 수량이 0 이하일 경우 예외를 발생시킴
        if (quantity <= 0) throw CustomError.InvalidQuantity
    }
}
