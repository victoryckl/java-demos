package com.example.filetools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	public FileUtils() {
	}

	public static void main(String[] args) {
		testmergeFile();
	}

	private static void testmergeFile() {
		String f1 = "e:/data/hanzi/整理好的语音/xiaomi/xiǎo.mp3";
		String f2 = "e:/data/hanzi/整理好的语音/xiaomi/mǐ.mp3";
		String out = "e:/data/hanzi/整理好的语音/xiaomi/xiǎo mǐ.mp3";
		mergeMp3File(f1, f2, out);
	}
	
	//合并mp3文件
	//合并时要去掉MP3头，否则只能发第一个字的音。
	//MP3头的长度是不确定的，到达第一个FF F2为止，也有可能是其他的标识，例如FF F3等 。
	//但目前通过ue打开汉字的语音文件，可以看出文件头都长度为25Byte，暂时按照25Byte处理。
	private static void mergeMp3File(String f1, String f2, String outf) {
		File file1 = new File(f1);
		File file2 = new File(f2);
		File outfile = new File(outf);
		
		if (!file1.exists()) {
			System.out.println("not exist: "+file1.getAbsolutePath());
			return ;
		}
		if (!file2.exists()) {
			System.out.println("not exist: "+file2.getAbsolutePath());
			return ;
		}
		
		if (outfile.exists()) {
			outfile.delete();
		}
		
		InputStream in1 = null;
		InputStream in2 = null;
		OutputStream out = null;
		
		try {
			in1 = new FileInputStream(file1);
			in2 = new FileInputStream(file2);
			out = new FileOutputStream(outfile);
			
			copyStream(in1, 25, out);
			copyStream(in2, 25, out);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in1 != null) in1.close();
				if (in2 != null) in2.close();
				if (out != null) out.close();
			} catch (Exception e2) {}
		}
	}
	
	private static void copyStream(InputStream in, int startSeek, OutputStream out) 
			throws IOException {
		int count = 0;
		byte[] buffer = new byte[1024*10];
		in.read(buffer, 0, startSeek);
		
		do {
			count = in.read(buffer);
			if (count <= 0) {
				break;
			} else {
				out.write(buffer, 0, count);
			}
		} while(true);
		out.flush();
	}
}
