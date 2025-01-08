//
//  Payable.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 결제 모듈
public typealias TransactionOperable = Payable & Refundable

public protocol Payable {
    func processPayment(amount: Decimal) async throws
}
