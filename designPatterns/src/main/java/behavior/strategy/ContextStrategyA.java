package behavior.strategy;

/**
 * 策略实现类A
 *
 * @author Lee
 */
public class ContextStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}