package behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题
 *
 * @author Lee
 */
abstract class Subject {

    protected List<Observer> observers = new ArrayList<>();

    public void add(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知观察者
     */
    abstract void notifyObserver();
}