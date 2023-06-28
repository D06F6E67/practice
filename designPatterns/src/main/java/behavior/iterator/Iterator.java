package behavior.iterator;

/**
 * 迭代器接口
 *
 * @author Lee
 */
public interface Iterator {

    /**
     * 第一个
     *
     * @return
     */
    Object first();

    /**
     * 下一个
     *
     * @return
     */
    Object next();

    /**
     * 有下一个
     *
     * @return
     */
    boolean hasNext();
}