package behavior.observer;

/**
 * 具体观察者A
 *
 * @author Lee
 */
public class ConcreteObserverA implements Observer {
    @Override
    public void response() {
        System.out.println("ConcreteObserverA response");
    }
}