package behavior.visitor;

/**
 * 具体元素A
 *
 * @author Lee
 */
public class ConcreteElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "operationA";
    }
}