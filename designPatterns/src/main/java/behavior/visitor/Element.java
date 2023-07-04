package behavior.visitor;

/**
 * 元素接口
 *
 * @author Lee
 */
public interface Element {

    void accept(Visitor visitor);
}