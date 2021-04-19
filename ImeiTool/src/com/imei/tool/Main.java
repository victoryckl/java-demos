package com.imei.tool;

/**
 * imei由15位数字组成，
 * 前6位(TAC)是型号核准号码，代表手机类型。
 * 接着2位(FAC)是最后装配号，代表产地。
 * 后6位(SNR)是串号，代表生产顺序号。
 * 最后1位 (SP)是检验码。 
 * 
 * 检验码计算：
 * (1).将偶数位数字分别乘以2，分别计算个位数和十位数之和
 * (2).将奇数位数字相加，再加上上一步算得的值
 * (3).如果得出的数个位是0则校验位为0，否则为10减去个位数 
 */
public class Main {
	
	private static final long TAC_FAC = 86740002000000L;
	private static final int MAX_SNR = 999999;

	public static void main(String[] args) {		
		long imei = 86740002031661L;
		genImeiTest(10000,20000);
	}
	
	public static void genImeiTest(int start, int end) {
		start = Math.max(0, start);
		end = Math.max(0, end);
		if (start > MAX_SNR || end > MAX_SNR) return;
		if (start >= end) return;
		
		long startMs = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for (int i = start; i < end; i++) {
			sb.append(checkSum(TAC_FAC+i)).append('\n');
		}
		long time1 = System.currentTimeMillis() - startMs;
		System.out.println(sb.toString());
		
		startMs = System.currentTimeMillis();
		sb = new StringBuffer();
		for (int i = start; i < end; i++) {
			sb.append(addCheckSum(TAC_FAC+i)).append('\n');
		}
		long time2 = System.currentTimeMillis() - startMs;
		System.out.println(sb.toString());
		
		System.out.println("time1="+time1);
		System.out.println("time2="+time2);
	}

	public static String checkSum(long imei) {
		String imeiString=""+imei;//前14位
		char[] imeiChar=imeiString.toCharArray();
		int resultInt=0;
		for (int i = 0; i < imeiChar.length; i++) {
			int a=Integer.parseInt(String.valueOf(imeiChar[i]));
			i++;
			final int temp=Integer.parseInt(String.valueOf(imeiChar[i]))*2;
			final int b=temp<10?temp:temp-9;
			resultInt+=a+b;
		}
		resultInt%=10;
		resultInt=resultInt==0?0:10-resultInt;
		return imeiString+resultInt;
	}
	
	public static long addCheckSum(long imei) {
		long checksum = getCheckSum(imei);
		return imei * 10 + checksum;
	}
	
	public static long getCheckSum(long imei) {
		long current = imei;
		long checksum = 0;
	 
		//从个位开始求checksum，共14位，每次2位，循环7次
		for (int i = 0; i < 7; i++)
		{
			int d1 = (int)(current % 10) * 2;
			current = current / 10;
	 
			int d0 = (int)(current % 10);
			current = current / 10;
	 
			checksum += d0 + (d1 / 10) + (d1 % 10);
		}
	 
		checksum = 10 - (checksum % 10);
		if (checksum == 10) checksum = 0;
	 
		return checksum;
	}
}
