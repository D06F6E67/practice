package behavior.visitor;

/**
 * 访问者模式
 *
 * @author Lee
 */
public class VisitorPattern {
    public static void main(String[] args) {
        ObjectStructure structure = new ObjectStructure();
        structure.add(new ConcreteElementA());
        structure.add(new ConcreteElementB());

        structure.accept(new ConcreteVisitorA());
        structure.accept(new ConcreteVisitorB());
    }
}