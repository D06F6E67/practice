package create.factoryMethod;

import java.util.Objects;
import java.util.Scanner;

/**
 * 工厂方法
 *
 * @author Lee
 */
public class FactoryMethod {
    public static AbstractFactory createFactory(Integer type) {
        switch (type) {
            case 1:
                return new Factory1();
            case 2:
                return new Factory2();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractFactory abstractFactory;
        Products products;
        do {
            int i = scanner.nextInt();
            abstractFactory = createFactory(i);
            products = abstractFactory.newProduct();
            products.show();
        } while (Objects.nonNull(abstractFactory));
    }
}

/**
 * 抽象产品：提供了产品的接口
 */
interface Products {
    void show();
}

/**
 * 具体产品1：实现抽象产品中的抽象方法
 */
class Product1 implements Products {
    @Override
    public void show() {
        System.out.println("产品1");
    }
}

/**
 * 具体产品2：实现抽象产品中的抽象方法
 */
class Product2 implements Products {
    @Override
    public void show() {
        System.out.println("产品2");
    }
}

/**
 * 抽象工厂：提供了厂品的生成方法
 */
interface AbstractFactory {
    /**
     * 生成产品
     *
     * @return 产品
     */
    Products newProduct();
}

/**
 * 具体工厂1：实现了厂品的生成方法
 */
class Factory1 implements AbstractFactory {
    @Override
    public Products newProduct() {
        System.out.println("具体工厂1生成-->具体产品1...");
        return new Product1();
    }
}

/**
 * 具体工厂2：实现了厂品的生成方法
 */
class Factory2 implements AbstractFactory {
    @Override
    public Products newProduct() {
        System.out.println("具体工厂2生成-->具体产品2...");
        return new Product2();
    }
}