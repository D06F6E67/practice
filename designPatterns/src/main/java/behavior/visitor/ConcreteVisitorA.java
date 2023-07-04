package behavior.visitor;

/**
 * 具体访问者A
 *
 * @author Lee
 */
public class ConcreteVisitorA implements Visitor{
    @Override
    public void visit(ConcreteElementA element) {
        System.out.println("ConcreteA ->" + element.operationA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.println("ConcreteA ->" + element.operationB());
    }
}