package structure.bridge;

/**
 * 桥接模式
 *
 * @author Lee
 */
public class Bridge {
    public static void main(String[] args) {
        ConcreteImplementor concreteImplementor = new ConcreteImplementor();
        RefinedAbstraction refinedAbstraction = new RefinedAbstraction(concreteImplementor);
        refinedAbstraction.Operation();
    }
}

/**
 * 实现化角色
 */
interface Implementor {
    void OperationImpl();
}

/**
 * 具体实现化角色
 */
class ConcreteImplementor implements Implementor {
    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}

abstract class Abstraction {
    protected Implementor imple;

    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }

    public abstract void Operation();
}

/**
 * 扩展抽象化角色
 */
class RefinedAbstraction extends Abstraction {
    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    public void Operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        imple.OperationImpl();
    }
}