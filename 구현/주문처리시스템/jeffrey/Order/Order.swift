//
//  Order.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 주문 모듈
public final class Order: CustomStringConvertible {
    public enum Status {
        /// 주문 접수
        case pending
        /// 결제 완료
        case paid
        /// 취소됨
        case canceled
        /// 환불 완료
        case refunded
    }

    private let payment: any TransactionOperable
    private var orderedProducts: Products
    public private(set) var status: Status
    
    public init(cart: Cart, payment: any TransactionOperable) throws {
        guard cart.isEmpty == false else {
            throw CustomError.emptyCart
        }
        self.payment = payment
        self.orderedProducts = Products(products: cart.products)
        self.status = .pending
    }
    
    public func payment() async throws {
        guard status == .pending else {
            throw CustomError.paymentFailed
        }
        try await payment.processPayment(amount: orderedProducts.totalOrderAmount)
        self.status = .paid
    }
    
    public func cancel() throws {
        guard status == .pending else {
            throw CustomError.cancelFailed
        }
        self.status = .canceled
    }
    
    public func refund() async throws {
        guard status == .paid else {
            throw CustomError.refundFailed
        }
        try await payment.refund()
        self.status = .refunded
    }
    
    public var description: String {
        switch status {
        case .pending: "주문 접수"
        case .paid: "결제 완료."
        case .canceled: "취소됨"
        case .refunded: "환불 완료"
        }
    }
}
