import Foundation

public class Screening {
    private let movie: Movie
    private let sequence: Int
    private let whenScreened: Date
    
    public init(movie: Movie, sequence: Int, whenScreened: Date) {
        self.movie = movie
        self.sequence = sequence
        self.whenScreened = whenScreened
    }
    
    public var startTime: Date {
        whenScreened
    }
    
    public func isSequence(_ sequence: Int) -> Bool {
        self.sequence == sequence
    }
    
    public var movieFee: Money {
        movie.fee
    }
    
    public func reserve(for customer: Customer, audienceCount: Double) -> Reservation {
        Reservation(customer: customer,
                    screening: self,
                    fee: movieFee,
                    audienceCount: audienceCount)
    }
    
    private func calculateFee(forAudienceCount audienceCount: Double) {
        movie.calculateMovieFee(self).times(audienceCount)
    }
}

public struct Movie {
    public let fee: Money
    private let title: String
    private let runningTime: Duration
    private var discountPolicy: any DiscountPolicy
    
    public init(title: String,
                runningTime: Duration,
                fee: Money,
                discountPolicy: any DiscountPolicy) {
        self.title = title
        self.runningTime = runningTime
        self.discountPolicy = discountPolicy
        self.fee = fee
    }
    
    public func calculateMovieFee(_ screening: Screening) -> Money {
        fee.minus(discountPolicy.calculateDiscountAmount(screening: screening))
    }
    
    public mutating func changeDiscountPolicy(to discountPolicy: any DiscountPolicy) {
        self.discountPolicy = discountPolicy
    }
}

public struct Money: Sendable {
    public static let zero: Money = Money.wons(0)
    private let amount: Decimal
    
    public static func wons(_ amount: Int) -> Money {
        Money(amount: Decimal(amount))
    }
    
    public static func wons(_ amount: Double) -> Money {
        Money(amount: Decimal(amount))
    }
    
    public init(amount: Decimal) {
        self.amount = amount
    }
    
    public func plus(_ amount: Money) -> Money {
        Money(amount: self.amount + amount.amount)
    }
    
    public func minus(_ amount: Money) -> Money {
        Money(amount: self.amount - amount.amount)
    }
    
    public func times(_ percent: Double) -> Money {
        Money(amount: self.amount * Decimal(percent))
    }
    
    public func isLess(than other: Money) -> Bool {
        amount < other.amount
    }
    
    public func isGreaterThanOrEqual(_ other: Money) -> Bool {
        amount >= other.amount
    }
}

public struct Customer {}

public struct Reservation {
    private let customer: Customer
    private let screening: Screening
    private let fee: Money
    private let audienceCount: Double
    
    public init(customer: Customer, screening: Screening, fee: Money, audienceCount: Double) {
        self.customer = customer
        self.screening = screening
        self.fee = fee
        self.audienceCount = audienceCount
    }
}

public protocol DiscountPolicy {
    var conditions: [DiscountCondition] { get }
    func calculateDiscountAmount(screening: Screening) -> Money
    func getDiscountAmount(screening: Screening) -> Money
}

public extension DiscountPolicy {
    func calculateDiscountAmount(screening: Screening) -> Money {
        for each in conditions {
            if each.isSatisfied(by: screening) {
                return getDiscountAmount(screening: screening)
            }
        }
        return .zero
    }
}

public protocol DiscountCondition {
    func isSatisfied(by screening: Screening) -> Bool
}

public struct SequenceCondition: DiscountCondition {
    private let sequence: Int
    
    public init(sequence: Int) {
        self.sequence = sequence
    }
    
    public func isSatisfied(by screening: Screening) -> Bool {
        screening.isSequence(sequence)
    }
}

public struct PeriodCondition: DiscountCondition {
    private let dayOfWeek: DayOfWeek
    private let startTime: LocalTime
    private let endTime: LocalTime
    
    public init(dayOfWeek: DayOfWeek, startTime: LocalTime, endTime: LocalTime) {
        self.dayOfWeek = dayOfWeek
        self.startTime = startTime
        self.endTime = endTime
    }
    
    public func isSatisfied(by screening: Screening) -> Bool {
        screening.startTime.getDayOfWeek == dayOfWeek &&
        startTime <= screening.startTime.localTime &&
        endTime >= screening.startTime.localTime
    }
}

public struct AmountDiscountPolicy: DiscountPolicy {
    public let conditions: [any DiscountCondition]
    private let discountAmount: Money
    
    public init(discountAmount: Money, conditions: any DiscountCondition ...) {
        self.conditions = conditions
        self.discountAmount = discountAmount
    }
    
    public func getDiscountAmount(screening: Screening) -> Money {
        discountAmount
    }
}

public struct PercentDiscountPolicy: DiscountPolicy {
    public let conditions: [any DiscountCondition]
    private let percent: Double
    
    public init(percent: Double, conditions: any DiscountCondition ...) {
        self.percent = percent
        self.conditions = conditions
    }
    
    public func getDiscountAmount(screening: Screening) -> Money {
        screening.movieFee.times(percent)
    }
}

public struct NoneDiscountPolicy: DiscountPolicy {
    public let conditions: [any DiscountCondition] = []
    
    public func getDiscountAmount(screening: Screening) -> Money {
        .zero
    }
}

// MARK: - Swift에서 지원하지 않는 Java 관련 타입, 속성, 메서드
public extension Date {
    var getDayOfWeek: DayOfWeek {
        let calendar = Calendar.current
        let weekday = calendar.component(.weekday, from: self)
        return DayOfWeek(rawValue: weekday) ?? .sunday
    }
    
    var localTime: LocalTime {
        let calendar = Calendar.current
        let hour = calendar.component(.hour, from: self)
        let minute = calendar.component(.minute, from: self)
        return LocalTime(hour: hour, minute: minute)
    }
}

public enum DayOfWeek: Int {
    case sunday = 1
    case monday, tuesday, wednesday, thursday, friday, saturday

    public static func from(weekday: Int) -> DayOfWeek? {
        return DayOfWeek(rawValue: weekday)
    }
}

public struct LocalTime: Comparable {
    private let hour: Int
    private let minute: Int
    
    public init(hour: Int, minute: Int) {
        self.hour = hour
        self.minute = minute
    }
    
    var localTime: Date {
        var timeComponents = DateComponents()
        timeComponents.hour = hour
        timeComponents.minute = minute

        let calendar = Calendar.current
        return calendar.date(from: timeComponents) ?? Date()
    }
    
    public static func < (lhs: LocalTime, rhs: LocalTime) -> Bool {
        if lhs.hour != rhs.hour {
            return lhs.hour < rhs.hour
        } else {
            return lhs.minute < rhs.minute
        }
    }
}

var avatar = Movie(title: "아바타",
                   runningTime: .seconds(60 * 120),
                   fee: .wons(10000),
                   discountPolicy: AmountDiscountPolicy(discountAmount: .wons(800),
                                                        conditions: SequenceCondition(sequence: 1),
                                                        SequenceCondition(sequence: 10),
                                                        PeriodCondition(dayOfWeek: .monday,
                                                                        startTime: LocalTime(hour: 10, minute: 0),
                                                                        endTime: LocalTime(hour: 11, minute: 59)),
                                                        PeriodCondition(dayOfWeek: .thursday,
                                                                        startTime: LocalTime(hour: 10, minute: 0),
                                                                        endTime: LocalTime(hour: 20, minute: 59))))
avatar.changeDiscountPolicy(to: PercentDiscountPolicy(percent: 0.1))

let titanic = Movie(title: "타이타닉",
                    runningTime: .seconds(60 * 180),
                    fee: .wons(11000),
                    discountPolicy: PercentDiscountPolicy(percent: 0.1,
                                                          conditions: SequenceCondition(sequence: 1),
                                                          SequenceCondition(sequence: 10),
                                                          PeriodCondition(dayOfWeek: .monday,
                                                                          startTime: LocalTime(hour: 10, minute: 0),
                                                                          endTime: LocalTime(hour: 11, minute: 59)),
                                                          PeriodCondition(dayOfWeek: .thursday,
                                                                          startTime: LocalTime(hour: 10, minute: 0),
                                                                          endTime: LocalTime(hour: 20, minute: 59))))

let starWars = Movie(title: "스타워즈",
                     runningTime: .seconds(60 * 210),
                     fee: .wons(10000),
                     discountPolicy: NoneDiscountPolicy())
