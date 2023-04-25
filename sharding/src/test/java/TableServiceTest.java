import com.lee.sharding.ShardingApplication;
import com.lee.sharding.service.TableService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Lee
 */
@SpringBootTest(classes = ShardingApplication.class)
public class TableServiceTest {

    @Resource
    private TableService tableService;

    @Test
    public void test() {
        tableService.createTable("info_base");
    }
}
