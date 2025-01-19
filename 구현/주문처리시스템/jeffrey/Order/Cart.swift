//
//  Cart.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 주문 모듈
public final class Cart: CustomStringConvertible {
    public private(set) var products: [Product] = []
    
    public init() {}
    
    public var isEmpty: Bool {
        products.isEmpty
    }
    
    public func add(_ products: Product ...) {
        for product in products {
            guard product.hasStock else {
                print(CustomError.soldOut.localizedDescription)
                continue
            }
            self.products.append(product)
        }
    }

    public func clear() {
        products.removeAll()
    }
    
    public var description: String {
        "현재 카트에 담겨져 있는 항목" + products
            .map { product in
                "\n- 상품명: \(product.name), 가격: \(product.price), 수량: \(product.quantity) "
            }.joined()
    }
}
