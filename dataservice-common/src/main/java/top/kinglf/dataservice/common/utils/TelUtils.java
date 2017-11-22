package top.kinglf.dataservice.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelUtils {
    public static ArrayList<String> getTelList(String strContainTel){
        ArrayList<String> telsArr=new ArrayList<>();
        strContainTel = StringFormer.format(strContainTel);
        strContainTel = "start" + strContainTel + "end";
        //手机
        String regx = "[^0-9][086|86|+86|0086]*(1[3|4|5|6|7|8|9][0-9]{9})[^0-9]";
        Pattern pat = Pattern.compile(regx);
        Matcher mat = pat.matcher(strContainTel);
        while( mat.find() ){
            String phone = telFormat(mat.group(1));
            if( telsArr.indexOf(phone) == -1 ){
                telsArr.add(phone);
            }
        }
        //固话
        regx = "[^0-9][086|86|+86|0086]*(0[1|2|3|4|5|6|7|8|9][0-9]{1,2}[-| |—|_]*[1|2|3|4|5|6|7|8|9][0-9]{5,7})[^0-9]";
        pat = Pattern.compile(regx);
        mat = pat.matcher(strContainTel);
        while( mat.find() ){
            String tel = telFormat(mat.group(1));
            if( telsArr.indexOf(tel) == -1 ){
                telsArr.add(tel);
            }
        }
        /**
         * 排序
         */
        Collections.sort(telsArr,new PriceComparator());
        while( telsArr.size() < 5 ){
            telsArr.add("");
        }
        return telsArr;
    }

    //自定义排序方法
    @SuppressWarnings("rawtypes")
    static class PriceComparator implements Comparator {
        public int compare(Object object1, Object object2) {
            String h1 = String.valueOf( object1 ) ;
            String h2 = String.valueOf( object2 ) ;
            //两个都是空，随便返回哪个都可以
            if( h1.equals("") && h2.equals("") ){
                return 1;
            }
            //2是电话，1是空字符串，则返回电话
            if( h1.equals("") && !h2.equals("") ){
                return 1;
            }
            //1是电话，2是空字符串，则返回电话
            if( !h1.equals("") && h2.equals("") ){
                return -1;
            }
            //1是电话，2是手机，返回手机
            if( h1.indexOf("-") != -1 && h2.indexOf("-") == -1){
                return 1;
            }
            //1是手机，2是电话，返回手机
            if( h1.indexOf("-") == -1 && h2.indexOf("-") != -1){
                return -1;
            }

            //1和2都是手机或者都是电话，则判断大小，然后返回一个
            h1 = h1.replaceAll("-", "");
            h2 = h2.replaceAll("-", "");
            long nb1 = Long.parseLong(h1);
            long nb2 = Long.parseLong(h2);
            if( nb1 > nb2 ){
                return 1;
            }
            return -1;
        }
    }

    public static String telFormat(String strSrc){
        strSrc = strSrc.replaceAll("—", "-");
        strSrc = strSrc.replaceAll("-", "");
        strSrc = strSrc.replaceAll("\\s", "");
        //如果开头是0的（固话）
        if( strSrc.indexOf("0") == 0 ){
            strSrc = strSrc.replaceFirst("0", "");
        }
        //10  20 21等开头的直辖市
        if( strSrc.indexOf("10") == 0 || strSrc.indexOf("20") == 0 || strSrc.indexOf("21") == 0|| strSrc.indexOf("22") == 0|| strSrc.indexOf("23") == 0|| strSrc.indexOf("24") == 0|| strSrc.indexOf("25") == 0|| strSrc.indexOf("27") == 0|| strSrc.indexOf("28") == 0|| strSrc.indexOf("29") == 0){
            strSrc = strSrc.substring(0, 2) + "-" + strSrc.substring(2);
            strSrc = "0" + strSrc;
        }
        //1开头的，手机号
        else if(strSrc.indexOf("1")==0){
            String regx = "[^0-9][086|86|+86|0086]*(1[3|4|5|6|7|8|9][0-9]{9})[^0-9]";
            Pattern pat = Pattern.compile(regx);
            Matcher mat = pat.matcher("a" + strSrc + "a");
            if( mat.find() && strSrc.length() == 11 ){

            }else{
                strSrc = "";
            }
        }
        //其他开头的，非直辖市的固话
        else{
            strSrc = strSrc.substring(0, 3) + "-" + strSrc.substring(3);
            strSrc = "0" + strSrc;
        }
        if( strSrc.indexOf("-") != -1  ){
            String strTemp = strSrc.substring(strSrc.indexOf("-") + 1);
            if( strTemp.length() != 7 && strTemp.length() != 8 ){
                strSrc = "";
            }
        }
        return strSrc;
    }
}
