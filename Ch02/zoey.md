


## Java Collection 프레임워크의 다형성

- Java Collection Framework에서 Iterator와 Iterable 인터페이스는 다형성을 활용하여 컬렉션 요소를 순회할 수 있도록 설계되었다.
- 컬렉션 객체가 동일한 메세지를 수신했을 때, 구현체의 타입에 따라 다르게 응답할 수 있기때문에, 구현체에 관계없이 컬렉션을 반복할 수 있으며, 새로운 컬렉션 타입이 추가 되더라도 동일한 순회 방식을 유지할 수 있다.

### Iterable, Iterator 인터페이스

- Iterable
    - 컬렉션이 반복 가능한 객체임을 나타내고, Iterable을 구현한 객체는 Iterator을 반환하는 iterator() 메서드를 제공한다.

        ```java
        public interface Iterable<T> {
            /**
             * Returns an iterator over elements of type {@code T}.
             *
             * @return an Iterator.
             */
            Iterator<T> iterator();
        
        ```

    - 모든 Collection은 Iterable 인터페이스를 구현한다.

        ```java
        public interface Collection<E> extends Iterable<E>
        ```

- Iterator
    - 컬렉션의 요소를 **순차적으로 접근**할 수 있는 메서드를 제공한다.

        ```java
        public interface Iterator<E> {
            
            boolean hasNext();
        
            /**
             * Returns the next element in the iteration.
             */
            E next();
            
            ...
        
        ```


### Iterable, Iterator의 다형성

- 다형성이란 동일한 메세지를 수신했을 때 객체의 타입에 따라 다르게 응답할 수 있는 능력을 의미한다.
- 동일한 메세지 수신
    - `iterator()`
    - `hasNext()`
- 객체의 타입에 따라 다르게 응답
    - 컬렉션 타입에 따라 `iterator()` 메세지를 수신했을 때,  각 컬렉션 타입에 따라 다른 Iterator 구현체를 반환한다.

        ```bash
        ArrayList : java.util.ArrayList$Itr@1dbd16a6
        HashMap : java.util.HashMap$KeyIterator@251a69d7
        LinkedList : java.util.LinkedList$ListItr@58644d46
        HashSet : java.util.HashMap$KeyIterator@14dad5dc
        ```

    - 동일한 `hasNext()` 호출 시, 각 컬렉션 타입의 저장 방식에 따라 다르게 응답한다.
        - ArrayList의 `hasNext()`

        ```java
        @Override
        public boolean hasNext() {
        		return cursor < a.length;
        }
        ```

        - HashMap의 `hasNext()`

        ```java
        public final boolean hasNext() {
            return next != null;
        }
        ```


- 다양한 컬렉션 구현체를 다형적으로 처리할 수 있어, 컬렉션 타입이 추가되는 경우에도 순회로직은 동일하게 유지할 수 있다.
    - 만약 MyArray라는 컬렉션 타입이 추가 되더라도, Iterable과 Iterator 인터페이스를 구현하고 있다면, 순회 로직을 동일하게 사용할 수 있다.

    ```java
    public class MyArray implements Iterable<Integer> {
    
        private int[] array;
    
        public MyArray(int[] array) {
            this.array = array;
        }
    
        @Override
        public Iterator<Integer> iterator() {
            return new MyIterator(array); // MyArray에서 사용할 반복자 ArrayIterator를 반환
        }
    }
    
    public class MyIterator implements Iterator<Integer> {
    
        private int currentIndex = -1; // 현재 index
        private int[] array;
    
        public MyIterator(int[] array) {
            this.array = array;
        }
    
        @Override
        public boolean hasNext() {
            return currentIndex < array.length - 1; // 다음 인덱스가 있는지 체크한다
        }
    
        @Override
        public Integer next() {
            return array[++currentIndex]; // next()를 호출할 때마다 하나씩 증가시킨다.
        }
    }
    ```

    ```java
    public static void main(String[] args) {
            MyArray integers = new MyArray(new int [] {1,2,3,4});
            if (integers.iterator().hasNext()) {
                System.out.println(integers.iterator().next());
            }
        }
    ```