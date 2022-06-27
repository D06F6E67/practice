package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列
 *
 * @author Lee
 */
public class permute {

    public static void main(String[] args) {
        // 不重复的值
        List<String> values = Arrays.asList("1", "2", "3");
        backtrack(values);

        res.forEach(s -> System.out.println(s));

    }


    // 全排结果
    static List<String> res = new ArrayList<>();
    // 当前全排值
    static StringBuilder compose = new StringBuilder();

    static void backtrack(List<String> value) {

        if (value.size() == compose.length()) {
            res.add(compose.toString());
            return;
        }

        for (String s : value) {
            if (compose.indexOf(s) != -1)
                continue;
            // 前
            compose.append(s);
            backtrack(value);
            // 后
            compose = compose.deleteCharAt(compose.length() - 1);
        }
    }
}
