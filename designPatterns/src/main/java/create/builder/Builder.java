package create.builder;

/**
 * 建造者
 * @author Lee
 */
public class Builder {
    public static void main(String[] args) {
        Builders builders = new ConcreteBuilder();
        Director director = new Director(builders);
        Product product = director.construct();
        product.show();
    }
}

class Product{
    private String a;
    private String b;
    private String c;

    public void setA(String a) {
        this.a = a;
    }
    public void setB(String b) {
        this.b = b;
    }
    public void setC(String c) {
        this.c = c;
    }

    public void show() {
        System.out.println(a + b + c);
    }
}
abstract class Builders {
    protected Product product = new Product();
    public abstract void buildA();
    public abstract void buildB();
    public abstract void buildC();
    public Product getProduct() {
        return product;
    }
}
class ConcreteBuilder extends Builders {
    @Override
    public void buildA() {
        product.setA("A");
    }
    @Override
    public void buildB() {
        product.setB("B");
    }
    @Override
    public void buildC() {
        product.setC("C");
    }
}
class Director {
    private Builders builders;
    public Director(Builders builders) {
        this.builders = builders;
    }
    public Product construct() {
        builders.buildA();
        builders.buildB();
        builders.buildC();
        return builders.getProduct();
    }
}