package behavior.strategy;

/**
 * 策略实现类B
 *
 * @author Lee
 */
public class ContextStrategyB implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}