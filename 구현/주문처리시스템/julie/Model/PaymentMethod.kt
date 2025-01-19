interface PaymentMethod {
    fun pay(amount: Int)
    fun refund(amount: Int)
}

class CardPayment(private val cardNumber: String, private val cardCompany: String) : PaymentMethod {
    override fun pay(amount: Int) {
        println("카드 결제 완료 - 금액: $amount, 카드번호: $cardNumber, 카드사: $cardCompany")
    }

    override fun refund(amount: Int) {
        println("카드 환불 완료 - 금액: $amount, 카드번호: $cardNumber, 카드사: $cardCompany")
    }
}

class BankTransfer(private val accountNumber: String, private val bank: String) : PaymentMethod {
    override fun pay(amount: Int) {
        println("계좌 이체 완료 - 금액: $amount, 계좌번호: $accountNumber, 은행: $bank")
    }

    override fun refund(amount: Int) {
        println("계좌 환불 완료 - 금액: $amount, 계좌번호: $accountNumber, 은행: $bank")
    }
}

class MobilePayment(private val phoneNumber: String, private val carrier: String) : PaymentMethod {
    override fun pay(amount: Int) {
        println("모바일 결제 완료 - 금액: $amount, 전화번호: $phoneNumber, 통신사: $carrier")
    }

    override fun refund(amount: Int) {
        println("모바일 환불 완료 - 금액: $amount, 전화번호: $phoneNumber, 통신사: $carrier")
    }
}
