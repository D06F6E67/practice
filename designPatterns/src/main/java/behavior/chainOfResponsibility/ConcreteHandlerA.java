package behavior.chainOfResponsibility;

/**
 * 具体处理者A
 *
 * @author Lee
 */
public class ConcreteHandlerA extends Handler {
    @Override
    public void handleRequest(String request) {
        if ("A".equals(request)) {
            System.out.println("ConcreteHandlerA handleRequest");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("无人处理");
            }
        }
    }
}