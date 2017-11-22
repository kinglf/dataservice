package top.kinglf.dataservice.common.utils;

public class StringFormer {
    public static String format(String dstStr){
        dstStr = dstStr.replaceAll("\\s+", " ");									//替换所有的空白字符,部分空白符号，mysql不支持;
        dstStr = dstStr.replaceAll("([a-z|0-9|A-Z|\u4e00-\u9fa5])\\1{11,}", "");	//替换连续相同11位及以上的字符
        dstStr = dstStr.replaceAll("<.*?>", "");									//替换所有的网页标签
        dstStr = dstStr.replaceAll("(&nbsp;)+", "");								//替换所有的&nbsp;
        dstStr = dstStr.replaceAll("(&raquo;)+", "");								//替换所有的&raquo;
        dstStr = dstStr.replaceAll("[\\s~·`!！@#￥$%^…&*（()）_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。》>、/？?]+",",");		//替换所有的特殊字符
        if(dstStr.indexOf(",") == 0 ){
            dstStr = dstStr.replaceFirst(",", "");
        }
        if( dstStr.length() > 1 ){
            if( dstStr.lastIndexOf(",") == dstStr.length()-1 ){
                dstStr = dstStr.substring(0,dstStr.length()-1);
            }
        }
        return dstStr.trim();
    }

}