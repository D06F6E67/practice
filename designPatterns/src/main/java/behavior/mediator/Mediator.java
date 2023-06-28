package behavior.mediator;

/**
 * 抽象中介者
 *
 * @author Lee
 */
abstract class Mediator {

    /**
     * 登记
     * @param colleague
     */
    abstract void register(Colleague colleague);

    /**
     * 转发
     * @param colleague
     */
    abstract void relay(Colleague colleague);
}