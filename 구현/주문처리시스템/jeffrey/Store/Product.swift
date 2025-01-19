//
//  Product.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 스토어 모듈
public struct Product {
    public enum DeliveryCharge {
        case free
        case normal
        case quick
        
        public var cost: Decimal {
            switch self {
            case .free: return 0
            case .normal: return 3_000
            case .quick: return 5_000
            }
        }
    }
    public let name: String
    public let price: Decimal
    /// 수량은 주문 내역 등에서 알 필요가 없기 때문에 internal로 설정.
    let quantity: Int
    let deliveryCharge: DeliveryCharge

    public init(name: String,
                price: Decimal,
                quantity: Int,
                deliveryCharge: DeliveryCharge = .free) {
        self.name = name
        self.price = price
        self.quantity = quantity
        self.deliveryCharge = deliveryCharge
    }
    
    public var hasStock: Bool {
        self.quantity > 0
    }
}
