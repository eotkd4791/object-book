//
//  Orders.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 주문 모듈
public final class Orders {
    private var orders: [Order] = []
    
    public init() {}
    
    public func add(_ order: Order) {
        self.orders.append(order)
    }
    
    public var lastOrder: Order {
        get throws {
            guard let lastOrder = orders.last else {
                throw CustomError.emptyOrder
            }
            return lastOrder
        }
    }
    
    public var selectedOrder: Order {
        get throws {
            guard let selectedOrder = orders.randomElement() else {
                throw CustomError.emptyOrder
            }
            return selectedOrder
        }
    }
}
