class Order(
    private val cart: Cart // 장바구니
) {
    var status: OrderStatus = OrderStatus.PENDING // 초기 주문 상태는 "주문 접수"
        private set
    private var totalAmount: Int = 0

    init {
        cart.validateNotEmpty() // 장바구니가 비어 있는지 확인
        totalAmount = cart.totalPrice() // 배송비 포함된 총 금액 계산
    }

    // 총 주문 금액 반환
    fun getTotalAmount(): Int = totalAmount

    // 주문 취소
    fun cancel() {
        if (status != OrderStatus.PENDING) throw CustomError.OrderNotAllowed // 결제 이전 상태에서만 취소 가능
        status = OrderStatus.CANCELLED
        println("주문이 취소되었습니다.")
    }

    // 결제 처리
    fun pay(paymentMethod: PaymentMethod) {
        if (status != OrderStatus.PENDING) throw CustomError.OrderNotAllowed // 결제 중복 방지
        paymentMethod.pay(totalAmount)
        status = OrderStatus.PAID
        println("결제가 완료되었습니다.")
    }

    // 환불 처리
    fun refund(paymentMethod: PaymentMethod) {
        if (status != OrderStatus.PAID) throw CustomError.RefundNotAllowed // 결제 완료 상태에서만 환불 가능
        paymentMethod.refund(totalAmount)
        status = OrderStatus.REFUNDED
        println("환불이 완료되었습니다.")
    }
}
