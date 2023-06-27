package behavior.state;

/**
 * 状态B
 *
 * @author Lee
 */
public class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("StateB");
        context.setState(new ConcreteStateA());
    }
}