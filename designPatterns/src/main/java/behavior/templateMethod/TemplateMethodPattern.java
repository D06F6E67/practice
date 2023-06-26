package behavior.templateMethod;

/**
 * 模板方法模式
 *
 * @author Lee
 */
public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.TemplateMethod();
    }
}