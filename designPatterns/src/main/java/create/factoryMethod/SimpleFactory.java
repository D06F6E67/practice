package create.factoryMethod;

import java.util.Objects;
import java.util.Scanner;

/**
 * 简单工厂 该模式不在23之中
 * @author Lee
 */
public class SimpleFactory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product product;
        do {
            int type = scanner.nextInt();
            product = createProduct(type);
            product.show();
        } while (Objects.nonNull(product));
    }

    public static Product createProduct(Integer type) {
        switch (type) {
            case 1:
                return new ConcreteProduct1();
            case 2:
                return new ConcreteProduct2();
            default:
                return null;
        }
    }
}
//抽象产品：提供了产品的接口
interface Product {
    void show();
}
//具体产品1：实现抽象产品中的抽象方法
class ConcreteProduct1 implements Product {
    @Override
    public void show() {
        System.out.println("产品1");
    }
}
//具体产品2：实现抽象产品中的抽象方法
class ConcreteProduct2 implements Product {
    @Override
    public void show() {
        System.out.println("产品2");
    }
}