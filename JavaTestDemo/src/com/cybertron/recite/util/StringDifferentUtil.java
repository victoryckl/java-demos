package com.cybertron.recite.util;

public class StringDifferentUtil {
	public static void main(String[] args) {			
//		String a = "welcome back nice to see your";
//		String b = "Welcome back! Nice to see you again.";
		String a = "天地分上下，\n日月照今古。\n小鸟说：“早，早，早，\nhello!\"”";
		String b = "天地飞上下日月照金骨。小鸟说找找找hello";
		String[] result = getDiff(replaceChars(a), replaceChars(b));
		String out = getHighLight(a, result[0]);
		System.out.println(out);
		System.out.println(a+"\n包含不参与比较的字符总数:"+markCount(a));
	}
	
    public static String[] getHighLightDifferent(String a, String b) {
        String[] temp = getDiff(a, b);
        String[] result = {getHighLight(a, temp[0]), getHighLight(b, temp[1])};
        return result;
    }

    public static String getHighLight(String source, String temp) {
        StringBuffer sb = new StringBuffer();
        char[] sourceChars = source.toCharArray();
        char[] tempChars = temp.toCharArray();
        boolean flag = false;
        for (int i = 0; i < sourceChars.length; i++) {
            if (tempChars[i] != ' ') {
                if (i == 0) {
                    sb.append("<span style='background-color:#D6A3A3'>").append(sourceChars[i]);
                } else if (flag) {
                    sb.append(sourceChars[i]);
                } else {
                    sb.append("<span style='background-color:#D6A3A3'>").append(sourceChars[i]);
                }
                flag = true;
                if (i == sourceChars.length - 1) {
                    sb.append("</span>");
                }
            } else if (flag == true) {
                sb.append("</span>").append(sourceChars[i]);
                flag = false;
            } else {
                sb.append(sourceChars[i]);
            }
        }
        return sb.toString();
    }

    public static String[] getDiff(String a, String b) {
    	System.out.println(a);
    	System.out.println(b);
        String[] result = null;
        //选取长度较小的字符串用来穷举子串
        if (a.length() < b.length()) {
            result = getDiff(a, b, 0, a.length());
        } else {
            result = getDiff(b, a, 0, b.length());
            result = new String[]{result[1], result[0]};
        }
        System.out.println("result: "+result[0]);
        return result;
    }

    //将a的指定部分与b进行比较生成比对结果
    private static String[] getDiff(String a, String b, int start, int end) {
        String[] result = new String[]{a, b};
        int len = result[0].length();
        while (len > 0) {
            for (int i = start; i < end - len + 1; i++) {
                String sub = result[0].substring(i, i + len);
                int idx = -1;
                if ((idx = result[1].indexOf(sub)) != -1) {
                    result[0] = setEmpty(result[0], i, i + len);
                    result[1] = setEmpty(result[1], idx, idx + len);
                    if (i > 0) {
                        //递归获取空白区域左边差异
                        result = getDiff(result[0], result[1], 0, i);
                    }
                    if (i + len < end) {
                        //递归获取空白区域右边差异
                        result = getDiff(result[0], result[1], i + len, end);
                    }
                    len = 0;//退出while循环
                    break;
                }
            }
            len = len / 2;
        }
        return result;
    }

    //将字符串s指定的区域设置成空格
    public static String setEmpty(String s, int start, int end) {
        char[] array = s.toCharArray();
        for (int i = start; i < end; i++) {
            array[i] = ' ';
        }
        return new String(array);
    }
    
    private static String beRepaceChars = "？?：:“”\"！!，,。.\n";

    //替换一些不参与比较的字符
    public static String replaceChars(String str) {
        return str.replace('零', '0')
                .replace('一', '1')
                .replace('二', '2')
                .replace('三', '3')
                .replace('四', '4')
                .replace('五', '5')
                .replace('六', '6')
                .replace('七', '7')
                .replace('八', '8')
                .replace('九', '9')

                .replaceAll("["+beRepaceChars+"]", " ")//标点符号和换行
                .toLowerCase();
    }
    
    //统计不参与比较的字符个数
    public static int markCount(String str) {
    	int count = 0;
    	if (str == null || str.isEmpty()) return 0;
    	for (int i = 0; i < str.length(); i++) {
    		char ch = str.charAt(i);
    		if (beRepaceChars.indexOf(ch) != -1) {
    			count++;
    		}
    	}
    	return count;
    }
}
