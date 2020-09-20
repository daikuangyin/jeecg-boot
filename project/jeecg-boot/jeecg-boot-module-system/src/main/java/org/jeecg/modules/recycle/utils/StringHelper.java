package org.jeecg.modules.recycle.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringHelper extends StringUtils {

  public static boolean isSameWithNull(String str1, String str2) {
    return String.valueOf(str1).equals(String.valueOf(str2));
  }
  public static boolean isTextSame(String str1, String str2) {
    return String.valueOf(str1).trim().equals(String.valueOf(str2).trim());
  }
  public static String fillWithChar(String str, char fillChar, int totalLength) {
    for (int i = (totalLength - str.length()); i > 0; i--) {
      str = str + fillChar;
    }
    return str;
  }

  /**
   * 移除HTML标签
   */
  public static String removeHtmlTag(String html) {
    return html.replaceAll("<.*?>", "").replaceAll("&", "&amp;")
        .replaceAll("\"", "&quot;");
  }

  /**
   * 转义 HTML 标签到页面可显示标签。
   */
  public static String conveyHtmlToPage(String html) {
    return html.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(
        "&", "&amp;").replaceAll("\"", "&quot;");
  }

  public static String getLastFromSp(String str, String sp, boolean withSp) {
    if (str.contains(sp)) {
      str = str.substring(str.lastIndexOf(sp)
          + (withSp ? 0 : sp.length()), str.length());
    }
    return str;
  }

  public static String getFrontBeforeSp(String str, String sp) {
    if (str.contains(sp)) {
      // str = str.substring(0, str.lastIndexOf(sp) - 1);
      str = str.substring(0, str.lastIndexOf(sp));
    }
    return str;
  }

  /**
   * 将max值乘于1.5并将除第一位的数字补0 如参数max值为2002 则返回值 3000 主要适用于柱状图的修改
   */
  public static String convertMaxValue(double max) {
    max = max * 1.5;
    String temp = String.valueOf(max);
    if (temp.contains(".")) {
      temp = temp.substring(0, temp.indexOf("."));
    }
    int m = 0;
    m = Integer.parseInt(temp.substring(0, 1));
    if (temp.length() >= 2) {
      int z = Integer.parseInt(temp.substring(1, 2));
      if (z >= 5) {
        m = m + 1;
      }
    }
    for (int i = 1; i < temp.length(); i++) {
      m = m * 10;
    }
    return String.valueOf(m);
  }

  /**
   * 如果orderName或者orderType为空则不做操作，直接返回。
   *
   * @param orderName 排序名
   * @param mapping 添加排序名的映射，例如{{"name","C.CHANNEL_NAME"}{"areaName","A.AREA_NAME"} }。
   * 如果为空则或者没有找到映射则直接用orderName参数的值作为数据库的排序字段。
   * @return StringBuilder对象
   */
  public static String getOrderName(String orderName, String[][] mapping) {
    if (orderName == null) {
      return null;
    } else {
      if (mapping != null) {
        for (int i = 0; i < mapping.length; i++) {
          if (mapping[i][0].equals(orderName)) {
            orderName = mapping[i][1];
            return orderName;
          }
        }
      }

    }
    return orderName;
  }

  public static String intArrayToString(int[] arr, String dou) {
    String str = "";
    for (int i : arr) {
      str += i + dou;
    }
    return str.substring(0, str.lastIndexOf(dou));
  }

  /**
   * 获取给定字符串的值，若字符串非空则返回自身，若字符串为null或""则返回默认值
   *
   * @param value ：给定字符串
   * @param defaultValue ：默认值
   */
  public static String defaultIfEmpty(String value, String defaultValue) {
    return checkNull(value) ? defaultValue : value;
  }

  /**
   * 判断字符串是否为空
   *
   * @param str 需要验证的字符串
   * @return boolean 为空(null或""),返回true;不为空,返回false
   */
  public static boolean checkNull(String str) {
    boolean check = true;
    if ((null != str) && (!"".equals(str.trim())) && (!"null".equals(str.trim()))) {
      check = false;
    }
    return check;
  }

  /**
   * 将字符串转换成list
   */
  public static List<String> string2List(String string, String separator) {
    String[] split = string.split(separator);
    return new ArrayList<String>(Arrays.asList(split));
  }
  /**
   * 给字符串右补0
   * @param str
   * @param strLength
   * @return
   */
  public static String addLZeroForPassword(String str, int strLength) {
    int strLen = str.length();
    StringBuffer sb = null;
    while (strLen < strLength) {
      sb = new StringBuffer();
      sb.append("0").append(str);// 左补0
      //sb.append(str).append("0");//右补0
      str = sb.toString();
      strLen = str.length();
    }
    return str;
  }
}
