# 04. 설계 품질과 트레이드 오프

- 객체지향 설계는 데이터가 아니라 책임에 초점을 맞춰야 한다
    - 객체의 상태는 구현에 속하고 구현은 변하기 쉽다. 상태 변경은 인터페이스의 변경을 초래하므로 데이터에 초점을 맞추는 설계는 변경에 취약하다.

- 데이터 중심의 설계
    - 객체 내부에 저장되는 데이터를 기반으로 시스템을 분할하는 방법
    - 객체의 종류를 저장하는 인스턴스 변수(movieType)와 인스턴스의 종류에 따라 배타적으로 사용될 인스턴스 변수( amount, percent)를 하나의 클래스 안에 포함시키는 방식

### 설계 트레이드 오프

- 캡슐화
    - 변경 가능성이 높은 부분을 객체 내부로 숨기는 추상화 기법
- 응집도
    - 모듈에 포함된 내부요소들이 연관돼 있는 정도
    - 변경이 발생할 때 모듈 내부에서 발생하는 변경의 정도로 측정 가능
    - 변경을 수용하기 위해 모듈전체가 변경된다면 응집도가 높은 것
    - 하나의 변경에 대해 하나의 모듈만 변경된다면 응집도가 높지만 다수의 모듈이 함께 변경돼야 한다면 응집도가 낮은 것
- 결합도
    - 의존성의 정도, 다른 모듈에 대해 얼마나 자세하게 알고있는지를 나타내는 척도
    - 내부구현을 변경했을 때 다른 모듈에 영향을 미치는 경우는 결합도가 낮다
- 좋은 설계란 높은 응집도와 낮은 결합도를 가진 모듈로 구성된 설계
- 캡슐화를 지키면 모듈안의 응집도는 높아지고 모듈 사이의 결합도는 낮아진다.

### 데이터 중심의 설계가 가진 문제점

1. 캡슐화 위반
    1. getter, setter 메서드는 캡슐화를 수행하지 못한다.
        1. 대부분의 내부 구현이 public 인터페이스에 그대로 노출
        2. 객체가 수행할 책임이 아니라 내부에 저장할 데이터에 초점을 맞췄기 때문
2. 높은 결합도

    ```java
    Money fee; // fee 타입이 변경된다면 movie 객체의 getFee도 변경돼야함
    
            if (discountable) {
                Money discountAmount = Money.ZERO;
                switch (movie.getMovieType()) {
                    case AMOUNT_DISCOUNT -> discountAmount = movie.getDiscountAmount();
                    case PERCENT_DISCOUNT -> discountAmount = movie.getFee().times(movie.getDiscountPercent());
                }
                fee = movie.getFee().minus(discountAmount);
            } else {
              fee = movie.getFee(); // getFee() 정상적으로 캡슐화 불가능
            }
    ```

    ```java
    // DiscountCondition 데이터 변경시 해당 클래스도 수정돼야한다
            for (DiscountCondition condition : movie.getDiscountConditions()) {
                if (condition.getType() == DiscountConditionType.PERIOD) {
                    discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek())
                            && condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0
                            && condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
                } else {
                    discountable = condition.getSequence() == screening.getSequence();
                }
    
                if (discountable) {
                    break;
                }
            }
    ```

3. 낮은 응집도
    - 서로 다른 이유로 변경되는 코드가 하나의 모듈 안에 공존할 때 모듈의 응집도가 낮다고 한다.
    - 변경의 이유가 서로 다른 코드들이 하나의 모듈에 있기때문에 변경과 아무 상관 없는 코드들이 영향 받게 된다.
    - 하나의 요구사항 변경을 반영하기 위해 동시에 여러 모듈을 수정해야한다.

### 데이터 중심 설계의 문제점

- 객체의 행동보다는 상태에 초점을 맞춘다
- 객체의 내부로 초점이 향하게 되므로 객체의 구현이 이미 결정된 상태에서 다른 객체와의 협력 방법을 고민하게되어 협력이 구현 세부사항에 종속되게 된다.