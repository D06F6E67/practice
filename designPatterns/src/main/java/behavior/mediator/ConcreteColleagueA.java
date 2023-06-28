package behavior.mediator;

/**
 * 具体同事A
 *
 * @author Lee
 */
public class ConcreteColleagueA extends Colleague{
    @Override
    void receive() {
        System.out.println("A 收到");
    }

    @Override
    void send() {
        System.out.println("A 发送");
        mediator.relay(this);
    }
}