package behavior.mediator;

/**
 * 具体同事C
 *
 * @author Lee
 */
public class ConcreteColleagueC extends Colleague{
    @Override
    void receive() {
        System.out.println("C 收到");
    }

    @Override
    void send() {
        System.out.println("C 发送");
        mediator.relay(this);
    }
}