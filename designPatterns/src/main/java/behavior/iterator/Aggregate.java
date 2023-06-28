package behavior.iterator;

/**
 * 聚合接口
 *
 * @author Lee
 */
public interface Aggregate {

    /**
     * 添加
     *
     * @param obj
     */
    void add(Object obj);

    /**
     * 删除
     *
     * @param obj
     */
    void remove(Object obj);

    /**
     * 获取迭代器
     *
     * @return
     */
    Iterator getIterator();
}