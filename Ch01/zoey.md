# 01_객체, 설계

## 소프트웨어 모듈이 가져야하는 세 가지 기능

- 모듈 : 크기와 상관없이 클래스나 패키지, 라이브러리와 같이 프로그램을 구성하는 임의의 요소
    - 실행중에 제대로 동작하는 것
    - 변경을 위해 존재하는 것
        - 변경하기 어려운 모듈은 제대로 동작하더라도 개선해야 한다.
    - 코드를 읽는 사람과 의사소통하는 것
        - 다른 개발자가 쉽게 읽고 이해할 수 있는 코드여야 한다.

그렇다면, 앞서 구현한 영화관 프로그램은 제대로 동작하지만 변경이 쉽지 않고, 주석없이 이해하기 쉽지는 않다

두번째 세번째 기능을 만족시키지 못하는 이유는 다음과 같다.

### 예상을 빗나가는 코드

- 관람객과 판매원이 티켓교환과 구매를 소극장에 의존하고 있음 → 현재 코드가 현실과 다르게 동작하기 때문에 이해하기 힘들다.
- 세부적인 내용을 한번에 기억하고 있어야 해서 이해하기 어렵다
    - Audience가 Bag을 가지고 있고, Bag에 Ticket과 Amount를 가지고 있다는 걸 기억해야 한다.
    - 하나의 클래스와 메서드에서 너무 많은 세부사항을 다루고 있음

### 변경에 취약한 코드

- Audience와 TicketSeller class를 변경하면 Theater도 변경해야한다.
    - 예를 들어, 관람객이 가방을 들고 다니지 않게 된다면
        - Audience 클래스에서 Bag을 삭제해야함
        - Audience.getBag에 직접접근하는 Theater의 enter 메서드도 수정되어야 함
        - 즉, 특정 클래스가 변경된다면, 클래스에 의존하는 다른 클래스도 변경해야 한다.
- 의존성이란 말 속에는 어떤 객체가 변경될 때 그 객체에게 의존하는 다른 객체도 함께 변경될 수 있다는 사실이 내포되어 있다.
- 애플리케이션의 기능을 구현하는 데 필요한 최소한의 의존성만 유지하고 불필요한 의존성을 제거하는것이 목표
- 결합도가 높다는 것은 객체 사이의 의존성이 과한 경우를 말한다. 즉 결합도가 높을 수록 함께 변경될 확률이 높아지므로 변경에 취약한 코드가 되기 쉽다.

최소한의 의존성만 유지하고, 결합도는 낮춰서 변경에 용이한 설계를 만드는 것이 설계의 목표이다.

## 예제 코드 개선하기

### 자율성 높이기

- Theater가 Audience나 Seller의 자세한 부분까지 알 필요는 없다. Audience는 초대장과 현금 처리를 Seller는 티켓 판매, 교환을 스스로 다루는 자율적인 존재로 만들면 문제해결이 가능하다.
- ticketOffice에 직접 접근하는 코드를 TicketSeller.sellTo()로 옮김

```java
public class TicketSeller {

    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketOffice.getTicket(); // ticketOffice에 대한 접근은 오직 TicketSeller 안에서만 존재하게 된다
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketOffice.getTicket();
            // 티켓 판매
            Long fee = ticket.getFee();
            audience.getBag().minusAmount(fee);
            ticketOffice.plusAmount(fee);

            audience.getBag().setTicket(ticket);
        }
    }
}

public class Theater {

    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        ticketSeller.sellTo(audience); // ticketOffice에 직접 접근하지 않는다.
    }
}
```

- TicketOffice에 대한 접근은 TicketSeller 내부에서만 하게 되면서, 외부에서 TicketOffice에 접근하는 코드인 getTicketOffice() 퍼블릭 메서드가 제거 됐다
- 이제 Theater에서 ticketOffice에 직접 접근하지 않으며, TicketSeller 내부에 TicketOffice가 있다는 사실도 알지 못한다.
- 개념적이나 물리적으로 객체 내부의 세부적인 사항을 감추는 것을 캡슐화라고 한다.
    - 캡슐화를 통해 객체 내부로의 접근을 제한하면 객체간의 결합도를 낮출 수 있다.
- 이로써, Theater는 오직 TicketSeller의 인터페이스에만  의존하게 됐다.
    - TicketSeller가 TicketOffice를 포함하고 있다는 것은 구현의 영역이다.
- 객체를 인터페이스와 구현으로 나누고 인터페이스만을 공개하는 것은 객체 사이의 결합도를 낮추고 변경하기 쉬운 코드를 작성하기 위해 따라야 하는 가장 기본적인 설계 원칙이다.

### 개선점

- 예상대로 동작하는 코드 - Audience, TicketSeller는 각자의 소지품(Bag, TicketOffice)을 스스로 관리한다
- Audience나 TicketSeller의 내부 구현을 변경하더라도 Theater를 함께 변경할 필요가 없어졌다.

### 캡슐화와 응집도

- 캡슐화
    - 객체 내부의 상태를 숨기고, 객체 간에 오직 메시지(인터페이스)를 통해서만 상호작용하도록 만든다.
- 응집도
    - 밀접하게 연관된 작업만을 수행하고 연관성 없는 작업은 다른 객체에게 위임하는 객체를 가리켜 응집도가 높다고 한다.
        - 응집도를 높이는 방법은 자신이 소유한 데이터는 자신이 스스로 처리하게 하는 자율적인 객체로 만들면 된다.

### 절차지향과 객체지향 프로래밍 방식의 차이

- 절차지향적 방식은 모든 Process가 하나의 클래스 내부에 위치하며, 각 클래스는 데이터의 역할만 수행한다.
    - 직관에 위배되며, 프로세스가 모든 데이터에 의존하므로 코드를 변경하기 어렵다.
- 변경하기 쉬운 설계는 한번에 하나의 클래스만 변경할 수 있는 설계
- 객체 지향 설계의 핵심은 캡슐화를 이용해 의존성을 적절하게 관리하여 객체 간의 결합도를 낮추는 것이다.
    - 자신의 문제는 스스로 처리한다는 예상을 만족시켜주기 때문에 이해하기 쉽고, 객체 내부의 변경이 다른 객체로 파급되지 않으므로 변경하기 쉽다.
- 책임의 이동
    - 절차지향 : 책임이 Theater에 집중되어 있다.
    - 객체지향 : 책임이 여러 객체에 분산되어 있다.
    - 책임의 이동 : 절차적인 설계에서는 Theater에 몰려있던 책임이 개별 객체로 이동했다.
        - 각 객체는 자신을 스스로 책임진다.

## 결국 설계는 트레이드오프의 산물

- 예제에서 TicketOffice의 자율성을 높여보자

```java
// TicketOffice 클래스
public Ticket sellTicketTo(Audience audience) {
        Ticket ticket = getTicket();
        audience.buy(ticket);
        plusAmount(ticket.getFee());
        return ticket;
    }
    
// TicketSeller 클래스
public void sellTo(Audience audience) {
        audience.buy(ticketOffice.sellTicketTo(audience));
    }
```

- TicketOffice의 자율성은 높였지만, 전체 설계의 관점에서는 결합도가 상승했다. → TicketOffice의 자율성보다 Audience에 대한 결합도를 낮추는 것이 더 중요하다는 결론에 도달했다.
- 설계는 균형의 예술이다. 훌륭한 설계는 적절한 트레이드 오프의 결과물임을 명심하자.
