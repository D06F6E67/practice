package behavior.state;

/**
 * 状态接口
 *
 * @author Lee
 */
public interface State {

    /**
     * 处理
     * @param context
     */
    void handle(Context context);
}