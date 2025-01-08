//
//  Paymentable.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 결제 모듈
public typealias TransactionOperable = Paymentable & Refundable

public protocol Paymentable {
    func processPayment(amount: Decimal) async throws
}
