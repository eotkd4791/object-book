//
//  MobilePayment.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

public struct MobilePayment: Payable {
    public enum Telecommunication {
        case skt
        case kt
        case lg
    }
    
    private let mobileNumber: String
    private let telecommunication: Telecommunication
    
    public init(mobileNumber: String, telecommunication: Telecommunication) {
        self.mobileNumber = mobileNumber
        self.telecommunication = telecommunication
    }
    
    public func processPayment(amount: Decimal) async throws {
        print("모바일 결제 진행 중")
        try await Task.sleep(for: .seconds(1))
        print("모바일 결제 진행 완료")
    }
}

extension MobilePayment: Refundable {
    public func refund() async throws {
        print("모바일 결제 환불 중")
        try await Task.sleep(for: .seconds(1))
        print("모바일 결제 환불 완료")
    }
}
