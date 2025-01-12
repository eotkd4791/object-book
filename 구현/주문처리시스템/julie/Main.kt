// 사용 예시 파일

fun main() {
    try {
        // 수량이 0인 상품 생성 시도
        val invalidProduct = Product("상품1", 10000, 0, 3000)
    } catch (e: CustomError.InvalidQuantity) {
        println("예외 발생: ${e.message}")
    }

    try {
        // 빈 장바구니로 주문 생성 시도
        val emptyCart = Cart()
        val order = Order(emptyCart)
    } catch (e: CustomError.EmptyCart) {
        println("예외 발생: ${e.message}")
    }

    // 정상 흐름
    val product1 = Product("상품1", 10000, 1, 3000) // 일반 배송
    val product2 = Product("상품2", 20000, 2, 5000) // 빠른 배송
    val product3 = Product("상품3", 15000, 1, 0)    // 무료 배송

    val cart = Cart()
    cart.addItem(CartItem(product1, 1))
    cart.addItem(CartItem(product2, 1))
    cart.addItem(CartItem(product3, 1))

    val order = Order(cart)
    println("주문 생성 완료: 총 금액 = ${order.getTotalAmount()}")

    val paymentMethod = CardPayment("1234-5678-9012-3456", "Visa")
    order.pay(paymentMethod)

    order.refund(paymentMethod)

    try {
        order.cancel()
    } catch (e: CustomError.OrderNotAllowed) {
        println("예외 발생: ${e.message}")
    }
}
