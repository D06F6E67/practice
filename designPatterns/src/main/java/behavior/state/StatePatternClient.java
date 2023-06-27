package behavior.state;

/**
 * 状态模式
 *
 * @author Lee
 */
public class StatePatternClient {
    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.handle();
        context.handle();
    }
}