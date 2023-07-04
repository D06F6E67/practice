package behavior.visitor;

/**
 * 具体元素B
 *
 * @author Lee
 */
public class ConcreteElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "operationB";
    }
}