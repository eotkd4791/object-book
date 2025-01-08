//
//  PaymentFactory.swift
//  ShoppingMall
//
//  Created by Mephrine on 1/8/25.
//

import Foundation

public enum PaymentFactory {
    case card(cardNumber: String, type: CardPayment.CardType)
    case accountTransfer(accountNumber: String, bank: AccountTransferPayment.Bank)
    case mobile(mobileNumber: String, telecommunication: MobilePayment.Telecommunication)
    
    public func make() -> any TransactionOperable {
        switch self {
        case .card(let cardNumber, let type): CardPayment(cardNumber: cardNumber, type: type)
        case .accountTransfer(let accountNumber, let bank): AccountTransferPayment(accountNumber: accountNumber, bank: bank)
        case .mobile(let mobileNumber, let telecommunication): MobilePayment(mobileNumber: mobileNumber, telecommunication: telecommunication)
        }
    }
}
