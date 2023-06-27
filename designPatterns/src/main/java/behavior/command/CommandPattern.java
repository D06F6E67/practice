package behavior.command;

/**
 * 命令模式
 *
 * @author Lee
 */
public class CommandPattern {
    public static void main(String[] args) {
        Command command = new ConcreteCommandA();
        Invoker invoker = new Invoker(command);
        invoker.cell();

        System.out.println("--------------------------------");

        invoker.setCommand(new ConcreteCommandB());
        invoker.cell();
    }
}