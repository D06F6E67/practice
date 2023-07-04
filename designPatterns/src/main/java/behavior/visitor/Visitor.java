package behavior.visitor;

/**
 * 访问者接口
 *
 * @author Lee
 */
public interface Visitor {
    void visit(ConcreteElementA element);
    void visit(ConcreteElementB element);
}