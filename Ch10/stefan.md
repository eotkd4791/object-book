# 10. 상속과 코드 재사용

### 코드 중복

- 코드 중복은 많은 문제의 원인.
  - 코드의 일관성을 깨고, 변경에 취약하게 만든다.
  - 이는 버그 발생률을 높인다.
  - 중복을 제거하기 위한 방법 중 하나는 중복이 발생한 클래스를 합치는 것이다.
  - 그러려면 타입을 나타내는 필드를 추가하여, 그에 따라 로직을 분기해야한다.
  - 이러한 패턴은 응집도를 낮추고 결합도를 높인다.
  - 이때 사용하기 좋은 방법이 상속이다.

### 상속의 문제점

- 자식 클래스 작성자가 부모 클래스 구현에 대해 정확한 지식을 가저야함.
- 상속은 부모 클래스와 자식 클래스 간의 결합도를 높임.
  - 상속은 부모 클래스의 구현에 자식 클래스가 강하게 결함됨
  - 캡슐화를 약화시키고, 결합도를 높인다.
  - 부모 클래스의 퍼블릭 인터페이스가 아닌 구현을 변경하더라도 자식 클래스에 영향을 끼칠 수 있다.
  - 자식 클래스를 확장하는 데에는 용이하지만, 높은 결합도로 인해 부모 클래스를 개선하는건 어렵게 한다.
  - 불필요한 인터페이스도 상속하게 한다.
  - 인터페이스 설계는 제대로 쓰기엔 쉽게, 엉터리로 쓰기엔 어렵게 만들어야한다.
- 상속은 부모 클래스의 구현을 재사용하는 것이기 때문에 결합도가 높아질 수 밖에 없다.
- 상속은 코드 재사용을 위해 캡슐화를 희생한다.

### 상속을 위한 경고

1. super 참조를 하는 것은 결합도를 높인다. 제거하는 게 좋다.
2. 부모 클래스의 메서드가 자식 클래스의 내부 구조에 대한 규칙을 깰 수 있다.
3. 자식 클래스가 부모 클래스의 메서드를 오버라이딩할 경우 부모 클래스가 자신의 메서드를 사용하는 방법에 자식 클래스가 결합될 수 있다.
4. 상속은 결국 자식-부모 간의 결합도를 높여서 둘 다 변경하거나, 둘 다 변경하지 않거나. 둘 중 하나이다.

### 상속의 문제점 해결하기

- 부모, 자식 모두 추상화에 의존하도록 설계하기

1. 차이를 메서드로 추출.
2. 부모 클래스의 코드를 하위로 내리지 말고, 자식 클래스의 코드를 상위로 올리기.


- 변경 되는 것과 변경되지 않는 것 분리하기.
- 추상 클래스를 이용하여 변경되는 부분만 추상화하기.
- 공통(중복) 코드는 부모 클래스(추상 클래스)로 옮기기.
- 변경되는 부분은 자식 클래스에서 구현하기.

**결과**
- 추상화에 의존하는 설계.
- (SRP) 추상 클래스와 이를 구현하는 클래스 모두 서로 다른 변경의 이유를 지닌다.
- 추상 클래스도 내부의 추상 메소드를 호출하기 때문에 추상화에 의존함.
- (DIP) "요금 계산"의 상위 수준의 정책을 구현하는 추상 클래스가 세부적인 요금계산 로직을 구현하는 클래스에 의존하지 않음.
  - 그 반대로 구현 클래스들이 추상 클래스에 의존함. 
- (OCP) 새로운 요금제 추가하려면 새로운 클래스 추가 후, 메서드 오버라이딩만 하면 끝


```java
public abstract class Phone {
  private double taxRate;
  private List<Call> calls = new ArrayList<>();

  public Phone(double taxRate) {
    this.taxRate = taxRate;
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for(Call call : calls) {
      result = result.plus(calculateCallFee(call));
    }

    return result.plus(result.times(taxRate));
  }

  protected abstract Money calculateCallFee(Call call);
}

```

```java
public class RegularPhone extends Phone {
  private Money amount;
  private Duration seconds;

  public RegularPhone(Money amount, Duration seconds, double taxRate) {
    super(taxRate);
    this.amount = amount;
    this.seconds = seconds;
  }

  public Money getAmount() {
    return amount;
  }

  public Duration getSeconds() {
    return seconds;
  }

  @Override
  protected Money calculateCallFee(Call call) {
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  } 
}
```

```java
public class NightlyDiscountPhone extends Phone {
  private static final int LATE_NIGHT_HOUR = 22;

  private Money nightlyAmount;
  private Money regularAmount;
  private Duration seconds;

  public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds, double taxRate) {
    super(taxRate);
    this.nightlyAmount = nightlyAmount;
    this.regularAmount = regularAmount;
    this.seconds = seconds;
  }

  @Override
  protected Money calculateCallFee(Call call) {
    if(call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
      return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    } 
    return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}

```


### 그래도 상속은 문제점이 있다.

- 클래스엔 메소드 뿐만 아니라, 인스턴스 변수도 있다.
- 부모 클래스에 인스턴스 변수가 추가되면 자식 클래스도 영향을 받는다.
 - 아무리 책임을 잘 분배하더라도, 이 상황은 피할 수 없음.
- 상속으로 인한 클래스 사이의 결합을 피할 수는 없음.
  - 따라서 상속은 꼭 필요한 경우에만 사용하기.
