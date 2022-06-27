package create.abstractFactory;

import java.util.Objects;
import java.util.Scanner;

/**
 * 抽象工厂
 *
 * 当系统中只存在一个等级结构的产品时，抽象工厂模式将退化到工厂方法模式
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

interface Product {
    void show();
}

class Product1 implements Product{
    @Override
    public void show() {
        System.out.println("具体产品1");
    }
}
class Product2 implements Product{
    @Override
    public void show() {
        System.out.println("具体产品2");
    }
}

interface AbstractFactories {
    Product1 newProduct1();
    Product2 newProduct2();
}
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

