//
//  CustomError.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

// MARK: - 공통 모듈
public enum CustomError: Error, LocalizedError {
    case emptyCart
    case emptyOrder
    case soldOut
    case paymentFailed
    case cancelFailed
    case refundFailed
    
    public var localizedDescription: String {
        switch self {
        case .soldOut: "재고가 소진되어 해당 상품을 담을 수 없습니다."
        case .emptyCart: "장바구니가 비어있어서 주문이 불가합니다./n장바구니에 상품을 담은 후 다시 시도해주세요."
        case .emptyOrder: "주문된 내역이 존재하지 않습니다."
        case .paymentFailed: "결제에 실패하였습니다. 다시 시도해주세요."
        case .cancelFailed: "주문 취소가 불가능합니다."
        case .refundFailed: "환불이 불가능합니다."
        }
    }
}
