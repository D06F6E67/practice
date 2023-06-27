package behavior.state;

/**
 * 状态A
 *
 * @author Lee
 */
public class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("stateA");
        context.setState(new ConcreteStateB());
    }
}