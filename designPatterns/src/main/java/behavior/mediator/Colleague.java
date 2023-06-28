package behavior.mediator;

/**
 * 抽象同事类
 *
 * @author Lee
 */
abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    /**
     * 接收
     */
    abstract void receive();

    /**
     * 发送
     */
    abstract void send();
}