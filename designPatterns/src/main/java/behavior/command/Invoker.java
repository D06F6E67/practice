package behavior.command;

/**
 * 调用者
 *
 * @author Lee
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void cell() {
        command.execute();
    }
}