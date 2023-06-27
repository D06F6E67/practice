package behavior.chainOfResponsibility;

/**
 * 抽象处理者
 *
 * @author Lee
 */
abstract class Handler {

    private Handler handler;

    /**
     * 处理请求方法
     * @param request 请求
     */
    public abstract void handleRequest(String request);

    public Handler getNext() {
        return handler;
    }

    public void setNext(Handler handler) {
        this.handler = handler;
    }
}