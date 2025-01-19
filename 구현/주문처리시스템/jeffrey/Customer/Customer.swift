//
//  Customer.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 고객 모듈
public struct Customer {
    private let cart = Cart()
    private let orders = Orders()
    
    public func pick(products: Product ...) {
        products.forEach { product in
            self.pick(product: product)
        }
    }
    
    /// 고객이 솔드아웃인 상품을 고른 것이기에, 품절된 상품은 pick을 하지 않도록 처리.
    public func pick(product: Product) {
        cart.add(product)
        print(cart)
    }
    
    @MainActor
    public func order(by paymentMethod: PaymentFactory) async {
        do {
            let order = try Order(cart: cart, payment: paymentMethod.make())
            orders.add(order)
            print(order)
            cart.clear()
        } catch let error as CustomError {
            print(error.localizedDescription)
        } catch {
            print(error)
        }
    }
    
    @MainActor
    public func payment() async {
        do {
            let order = try orders.lastOrder
            try await order.payment()
            print(order)
        } catch let error as CustomError {
            print(error.localizedDescription)
        } catch {
            print(error)
        }
    }
    
    @MainActor
    public func cancel() {
        do {
            let order = try orders.lastOrder
            try order.cancel()
            print(order)
        } catch let error as CustomError {
            print(error.localizedDescription)
        } catch {
            print(error)
        }
    }
    
    @MainActor
    public func refund() async {
        do {
            let order = try orders.selectedOrder
            try await order.refund()
            print(order)
        } catch let error as CustomError {
            print(error.localizedDescription)
        } catch {
            print(error)
        }
    }
}
