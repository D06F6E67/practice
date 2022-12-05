package create.abstractFactory;

import java.util.Objects;
import java.util.Scanner;

/**
 * 抽象工厂
 * <p>
 * 当系统中只存在一个等级结构的产品时，抽象工厂模式将退化到工厂方法模式
 *
 * @author Lee
 */
public class AbstractFactory {

    public static AbstractFactories create(Integer type) {
        switch (type) {
            case 1:
                return new AbstractFactories1();
            case 2:
                return new AbstractFactories2();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractFactories abstractFactories;
        do {
            int i = scanner.nextInt();
            abstractFactories = create(i);
            abstractFactories.newProduct1().show();
            abstractFactories.newProduct2().show();
        } while (Objects.nonNull(abstractFactories));

    }

}

/**
 * 产品接口
 */
interface Product {
    void show();
}

/**
 * 产品1
 */
class Product1 implements Product {
    @Override
    public void show() {
        System.out.println("具体产品1");
    }
}

/**
 * 产品2
 */
class Product2 implements Product {
    @Override
    public void show() {
        System.out.println("具体产品2");
    }
}

/**
 * 工厂接口
 */
interface AbstractFactories {
    /**
     * 生成产品1
     *
     * @return 产品1
     */
    Product1 newProduct1();

    /**
     * 生成产品2
     *
     * @return 产品2
     */
    Product2 newProduct2();
}

/**
 * 工厂1
 */
class AbstractFactories1 implements AbstractFactories {
    @Override
    public Product1 newProduct1() {
        System.out.println("具体工厂 1 生成-->具体产品 11");
        return new Product1();
    }

    @Override
    public Product2 newProduct2() {
        System.out.println("具体工厂 1 生成-->具体产品 12");
        return new Product2();
    }
}

/**
 * 工厂2
 */
class AbstractFactories2 implements AbstractFactories {
    @Override
    public Product1 newProduct1() {
        System.out.println("具体工厂 2 生成-->具体产品 21");
        return new Product1();
    }

    @Override
    public Product2 newProduct2() {
        System.out.println("具体工厂 2 生成-->具体产品 22");
        return new Product2();
    }
}

