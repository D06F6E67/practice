package behavior.observer;

/**
 * 观察者模式
 *
 * @author Lee
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observerA = new ConcreteObserverA();
        Observer observerB = new ConcreteObserverB();

        subject.add(observerA);
        subject.add(observerB);

        subject.notifyObserver();
    }
}