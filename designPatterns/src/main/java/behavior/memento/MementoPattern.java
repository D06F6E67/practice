package behavior.memento;

/**
 * 备忘录模式
 *
 * @author Lee
 */
public class MementoPattern {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("0");
        System.out.println(originator.getState());

        caretaker.setMemento(originator.createMemento());
        originator.setState("1");
        System.out.println(originator.getState());

        originator.restoreMemento(caretaker.getMemento());
        System.out.println(originator.getState());
    }
}