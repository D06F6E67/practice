package behavior.observer;

/**
 * 具体主题
 *
 * @author Lee
 */
public class ConcreteSubject extends Subject{
    @Override
    void notifyObserver() {
        System.out.println("ConcreteSubject.notifyObserver");
        for (Observer observer : observers) {
            observer.response();
        }
    }
}