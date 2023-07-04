package behavior.visitor;

/**
 * 具体访问者B
 *
 * @author Lee
 */
public class ConcreteVisitorB implements Visitor{
    @Override
    public void visit(ConcreteElementA element) {
        System.out.println("ConcreteB ->" + element.operationA());
    }

    @Override
    public void visit(ConcreteElementB element) {
        System.out.println("ConcreteB ->" + element.operationB());
    }
}