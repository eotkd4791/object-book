//
//  Products.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 스토어 모듈
public struct Products: CustomStringConvertible {
    private let products: [Product]
    
    public init(products: [Product]) {
        self.products = products
    }
    
    public var totalOrderAmount: Decimal {
        products.reduce(0) { partialResult, product in
            partialResult + (product.price * Decimal(product.quantity)) + product.deliveryCharge.cost
        }
    }
    
    public var description: String {
        "현재 카트에 담겨져 있는 항목" + products
            .map { product in
                "\n- 상품명: \(product.name), 가격: \(product.price), 수량: \(product.quantity) "
            }.joined()
    }
}
