package behavior.chainOfResponsibility;

/**
 * 责任链模式
 *
 * @author Lee
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        // 组装责任链
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNext(handlerB);

        // 提交请求
        handlerA.handleRequest("A");
        handlerA.handleRequest("B");
        handlerA.handleRequest("C");
    }
}