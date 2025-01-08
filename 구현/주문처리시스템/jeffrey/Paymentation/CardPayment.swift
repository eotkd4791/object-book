//
//  CardPayment.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

public struct CardPayment: Paymentable {
    public enum CardType {
        case visa
        case masterCard
    }

    private let cardNumber: String
    private let type: CardType
    
    public init(cardNumber: String, type: CardType) {
        self.cardNumber = cardNumber
        self.type = type
    }
    
    public func processPayment(amount: Decimal) async throws {
        print("카드 결제 진행 중")
        try await Task.sleep(for: .seconds(1))
        print("카드 결제 진행 완료")
    }
}

extension CardPayment: Refundable {
    public func refund() async throws {
        print("카드 결제 환불 중")
        try await Task.sleep(for: .seconds(1))
        print("카드 결제 환불 완료")
    }
}
