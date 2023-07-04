package behavior.memento;

/**
 * 发起人
 *
 * @author Lee
 */
public class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    void restoreMemento(Memento memento) {
        setState(memento.getState());
    }
}