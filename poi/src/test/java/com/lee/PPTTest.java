package com.lee;

import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lee
 */
@SpringBootTest
public class PPTTest {

    @Test
    public void bcrypt() {
        String passwd = "e31dfa45-155e-476e-abd1-a04900f385df1234zxc";
        String salt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw(passwd, salt);
        System.out.println(hashpw);

        boolean checkpw = BCrypt.checkpw(passwd, hashpw);
        System.out.println(checkpw);
    }

    @Test
    public void test() throws IOException {
        // 打开文件
        Resource resource = new ClassPathResource("ndzj.ppt");
        HSLFSlideShow template = new HSLFSlideShow(resource.getInputStream());

        Map<String, String> update = new HashMap<>();
        update.put("title", "测试");
        update.put("author", "Lee");
        home(template, update);


        // directory(ppt);


        // deleteTemplate(ppt, 4);

        // 写入文件
        template.write(Files.newOutputStream(Paths.get("/Users/lee/Projects/IDEA/practice/poi/src/main/resources/ndzj2.ppt")));
        template.close();
    }

    private void home(HSLFSlideShow template, Map<String, String> update) {
        // 获取首页ppt
        HSLFSlide homeTemplate = template.getSlides().get(0);

        // 创建一页ppt
        HSLFSlide slideNew = template.createSlide();
        // Color foregroundColor = homeTemplate.getBackground().getFill().getForegroundColor();
        // slideNew.getBackground().getFill().setForegroundColor(foregroundColor);

        Color foregroundColor = homeTemplate.getBackground().getFill().getForegroundColor();
        slideNew.getBackground().getFill().setForegroundColor(foregroundColor);

        // 复制模板
        homeTemplate.getShapes().forEach(slideNew::addShape);

        // 修改值
        setText(slideNew, update);
    }

    private void directory(HSLFSlideShow ppt) {

        // XMLSlideShow ppt1 = new XMLSlideShow();

        // 获取目录ppt
        HSLFSlide directoryTemplate = ppt.getSlides().get(1);

        // 创建一页ppt
        HSLFSlide slideNew = ppt.createSlide();
        // 复制模板
        // directoryTemplate.getShapes().forEach(slideNew::addShape);

        List<HSLFShape> shapes = directoryTemplate.getShapes();

        for (int i = 0; i < 2; i++) {
            slideNew.addShape(shapes.get(i));
        }

        // new ArrayList<>(slideNew.getShapes()).forEach(shape -> {
        //     // 处理文本类型 XSLFGroupShape
        //     if (shape instanceof HSLFGroupShape) {
        //         HSLFGroupShape groupShape = (HSLFGroupShape) shape;
        //         Rectangle2D anchor = groupShape.getAnchor();
        //
        //         anchor.setFrame(0.0, 0.0, anchor.getWidth(), anchor.getHeight());
        //         groupShape.setAnchor(anchor);
        //         // slideNew.removeShape(shape);
        //
        //         XSLFAutoShape autoShape1 = slideNew.createAutoShape();
        //         // autoShape1.setShapeType(groupShape.getShapes().get(0).gett());;
        //     }
        // });

    }

    private void setText(HSLFSlide slide, Map<String, String> update) {

        slide.getShapes().forEach(shape -> {
            // 处理文本类型
            if (shape instanceof HSLFTextShape) {

                HSLFTextShape textShape = (HSLFTextShape) shape;
                // 获取原始文本
                String rawText = textShape.getRawText();
                String tagName = getTagName(rawText);
                if (Objects.nonNull(tagName)) {
                    String value = update.get(tagName);
                    rawText = rawText.replace(String.format("{{%s}}", tagName), value);
                    textShape.setText(rawText);
                }
            }
        });
    }

    private void deleteTemplate(HSLFSlideShow ppt, int pages) {
        for (int i = 0; i < pages; i++) {
            ppt.removeSlide(0);
        }
    }

    private String getTagName(String text) {
        // 定义正则表达式
        String regex = "\\{\\{(.+?)\\}\\}";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 创建 Matcher 对象
        Matcher matcher = pattern.matcher(text);

        // 查找匹配
        if (matcher.find()) {
            // 获取匹配到的内容
            return matcher.group(1);
        }

        return null;
    }
}
