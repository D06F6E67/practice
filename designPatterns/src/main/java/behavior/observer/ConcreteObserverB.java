package behavior.observer;

/**
 * 具体观察者B
 *
 * @author Lee
 */
public class ConcreteObserverB implements Observer {
    @Override
    public void response() {
        System.out.println("ConcreteObserverB response");
    }
}