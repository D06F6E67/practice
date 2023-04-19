package structure.decorator;

/**
 * 装饰器模式
 *
 * @author Lee
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        ConcreteComponent concreteComponent = new ConcreteComponent();
        concreteComponent.operation();

        System.out.println("--------------------------------");

        ConcreteDecorator concreteDecorator = new ConcreteDecorator(concreteComponent);
        concreteDecorator.operation();
    }
}

/**
 * 抽象构件角色
 */
interface Component {
    void operation();
}

/**
 * 具体构件角色
 */
class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.println("创建具体构件角色");
    }
}

/**
 * 抽象装饰角色
 */
class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

/**
 * 具体装饰角色
 */
class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
}