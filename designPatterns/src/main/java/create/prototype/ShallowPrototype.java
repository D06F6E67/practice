package create.prototype;

import lombok.SneakyThrows;

/**
 * 原型 浅克隆
 * <p>
 * 浅克隆：创建一个新对象，新对象的属性和原来对象完全相同，对于非基本类型属性，仍指向原有属性所指向的对象的内存地址。
 * 深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
 *
 * @author Lee
 */
public class ShallowPrototype implements Cloneable {

    private String name;

    ShallowPrototype(String name) {
        System.out.println("创建");
        this.name = name;
    }

    public void getName() {
        System.out.println(name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("克隆成功");
        return super.clone();
    }
}

class ShallowPrototypeTest {
    @SneakyThrows
    public static void main(String[] args) {
        ShallowPrototype s1 = new ShallowPrototype("名称");
        s1.getName();
        ShallowPrototype s2 = (ShallowPrototype) s1.clone();
        s2.getName();
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
