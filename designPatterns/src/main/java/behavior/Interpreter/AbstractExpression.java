package behavior.Interpreter;

/**
 * 抽象表达式
 *
 * @author Lee
 */
public interface AbstractExpression {

    /**
     * 解释方法
     * @param info
     */
    void interpret(String info);
}