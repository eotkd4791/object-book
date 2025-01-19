//
//  AccountTransferPayment.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

public struct AccountTransferPayment: Payable {
    public enum Bank {
        case kb
        case ibk
    }
    private let accountNumber: String
    private let bank: Bank
    
    init(accountNumber: String, bank: Bank) {
        self.accountNumber = accountNumber
        self.bank = bank
    }
    
    public func processPayment(amount: Decimal) async throws {
        print("계좌이체 결제 진행 중")
        try await Task.sleep(for: .seconds(1))
        print("계좌이체 결제 진행 완료")
    }
}

extension AccountTransferPayment: Refundable {
    public func refund() async throws {
        print("계좌이체 결제 환불 중")
        try await Task.sleep(for: .seconds(1))
        print("계좌이체 결제 환불 완료")
    }
}
