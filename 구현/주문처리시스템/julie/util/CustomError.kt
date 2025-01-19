sealed class CustomError(message: String) : Exception(message) {
    object InvalidQuantity : CustomError("상품의 수량은 0보다 커야 합니다.") // 수량이 0 이하일 때 발생
    object EmptyCart : CustomError("장바구니가 비어 있습니다.") // 빈 장바구니로 주문 생성 시 발생
    object OrderNotAllowed : CustomError("결제 이전 상태에서만 주문을 취소할 수 있습니다.") // 주문 취소 조건
    object RefundNotAllowed : CustomError("결제 완료된 주문만 환불할 수 있습니다.") // 환불 조건
}
