package behavior.mediator;

/**
 * 具体同事B
 *
 * @author Lee
 */
public class ConcreteColleagueB extends Colleague{
    @Override
    void receive() {
        System.out.println("B 收到");
    }

    @Override
    void send() {
        System.out.println("B 发送");
        mediator.relay(this);
    }
}