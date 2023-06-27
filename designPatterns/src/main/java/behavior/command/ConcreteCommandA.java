package behavior.command;

/**
 * 具体命令A
 *
 * @author Lee
 */
public class ConcreteCommandA implements Command{

    private ReceiverA receiverA;

    public ConcreteCommandA() {
        this.receiverA = new ReceiverA();
    }

    @Override
    public void execute() {
        receiverA.action();
    }
}