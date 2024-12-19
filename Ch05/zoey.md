## 책임 주도 설계로 전환하기 위한 두 가지 원칙

- 데이터보다 행동을 먼저 결정하라
- 협력이라는 문맥 안에서 책임을 결정하라
    - 협력에 적합한 책임이란 메세지를 전송하는 클라이언트의 의도에 적합한 책임

## 책임 할당을 위한 GRASP 패턴

- General Responsibility Assignment software pattern
- Information Expert 패턴
    - 책임을 수행할 정보를 알고 있는 객체에게 책임을 할당하라
- Low Coupling, High Cohesion
    - 낮은 결합도 패턴
    - 높은 응집도 패턴
- Creator 패턴
    - 어떤 방식으로든 생성되는 객체와 연결되거나 관련될 필요가 있는 객체에 해당 객체를 생성할 책임을 맡기는 것

### 코드를 통해 변경의 이유를 파악할 수 있는 방법

- 인스턴스 변수가 초기화되는 시점
    - 응집도가 높은 클래스는 인스턴스를 생성할 때 모든 속성을 함께 초기화한다.
    - 반면 응집도가 낮은 클래스는 객체의 속성 중 일부만 초기화하고 일부는 초기화되지 않은 상태로 남겨진다.

        ```java
        public class DiscountCondition {
        
            private DiscountConditionType type;
            private int sequence;
            private DayOfWeek dayOfWeek;
            private LocalTime startTime;
            private LocalTime endTime;
            ...
         }
        ```

        - 순번 조건인 경우 sequence는 초기화, dayOfWeek, startTime, endTime는 초기화되지 않는다.
        - 클래스 속성이 서로 다른 시점에 초기화되거나 일부만 초기화된다는 것은 응집도가 낮다는 증거다
        - 따라서 함께 초기화되는 속성을 기준으로 코드를 분리해야 한다.
- 메서드들이 인스턴스 변수를 사용하는 방식
    - 모든 메서드가 객체의 모든 속성을 사용한다면 클래스의 응집도는 높다
        - 반면, 메서드들이 사용하는 속성에 따라 그룹이 나뉜다면 클래스의 응집도가 낮다

    ```java
    public boolean isSetisfiedBySequence(int sequence) {
            return sequence == this.sequence;
        }
    ```

    - sequence는 사용하지만 다른 인스턴스 변수들은 사용하지 않는다
    - 클래스의 응집도를 높이기 위해서는 속성 그룹과 해당 그룹에 접근하는 메서드 그룹을 기준으로 코드를 분리해야한다.

## 리팩터링

### 메서드 응집도

- 긴 메서드를 작고 응집도 높은 메서드로 분리하면 각 메서드를 적절한 클래스로 이동하기가 더 수월해진다.
    - 작고 명확하며 한가지 일에 집중하는 응집도 높은 메서드는 변경 가능한 설계를 이끌어내는 기반이 된다.

### 메서드 분배

- 각 메서드가 사용하는 데이터를 정의하고 있는 적절한 클래스로 분배
    - 메서드가 사용하는 데이터를 저장하고 있는 클래스로 메서드를 이동시키면 된다.