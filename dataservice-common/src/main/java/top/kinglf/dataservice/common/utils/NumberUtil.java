package top.kinglf.dataservice.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
    public static double getDouble(String str){
        // 车长，统一转换成 x.x的格式

        String regx = "[0123456789一二三四五六七八九十]{1,5}[米mM][0123456789一二三四五六七八九]{0,5}";
        Pattern pat = Pattern.compile(regx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            String strFind = mat.group();
            strFind = strFind.replaceAll("米", ".");
            strFind = strFind.replaceAll("m", ".");
            strFind = strFind.replaceAll("M", ".");
            strFind = strFind.replaceAll("一", "1");
            strFind = strFind.replaceAll("二", "2");
            strFind = strFind.replaceAll("三", "3");
            strFind = strFind.replaceAll("四", "4");
            strFind = strFind.replaceAll("五", "5");
            strFind = strFind.replaceAll("六", "6");
            strFind = strFind.replaceAll("七", "7");
            strFind = strFind.replaceAll("八", "8");
            strFind = strFind.replaceAll("九", "9");
            strFind = strFind.replaceAll("十", "1");
            try {
                // 去掉中文
                strFind = strFind.replaceAll("\u4e00-\u9fa5", "");
                Float length = Float.parseFloat(strFind);
                str = length.toString();
            } catch (Exception e) {
                str = "";
            }
        } else {
            try {
                str = str.replaceAll("一", "1");
                str = str.replaceAll("二", "2");
                str = str.replaceAll("三", "3");
                str = str.replaceAll("四", "4");
                str = str.replaceAll("五", "5");
                str = str.replaceAll("六", "6");
                str = str.replaceAll("七", "7");
                str = str.replaceAll("八", "8");
                str = str.replaceAll("九", "9");
                str = str.replaceAll("十", "1");
                // 去掉中文
                str = str.replaceAll("\u4e00-\u9fa5", "");
                Float length = Float.parseFloat(str);
                str = length.toString();
            } catch (Exception e) {
                str = "";
            }
        }
        return Double.parseDouble(str);
    }


}
