package behavior.strategy;

/**
 * 策略模式
 *
 * @author Lee
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context();
        Strategy strategy = new ContextStrategyA();
        context.setStrategy(strategy);
        context.strategyMethod();

        System.out.println("--------------------------------");

        strategy = new ContextStrategyB();
        context.setStrategy(strategy);
        context.strategyMethod();
    }
}