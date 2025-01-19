//
//  Refundable.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/9/25.
//

import Foundation

public protocol Refundable {
    func refund() async throws
}
