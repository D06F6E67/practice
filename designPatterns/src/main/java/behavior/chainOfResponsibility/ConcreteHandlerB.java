package behavior.chainOfResponsibility;

/**
 * 具体处理者B
 *
 * @author Lee
 */
public class ConcreteHandlerB extends Handler {
    @Override
    public void handleRequest(String request) {
        if ("B".equals(request)) {
            System.out.println("ConcreteHandlerB handleRequest");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("无人处理");
            }
        }
    }
}