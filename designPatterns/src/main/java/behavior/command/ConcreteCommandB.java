package behavior.command;

/**
 * 具体命令B
 *
 * @author Lee
 */
public class ConcreteCommandB implements Command{

    private ReceiverB receiverB;

    public ConcreteCommandB() {
        this.receiverB = new ReceiverB();
    }

    @Override
    public void execute() {
        receiverB.action();
    }
}