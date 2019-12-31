import java.io.File;

public class Main {

	public static void main(String[] args) {
		//rmDir("f:/temp/111/dtat");
		testSplit();
	}
	
	private static void testSplit() {
		String str = "rén zhī chū	   xìng běn shàn";
		str = str.replaceAll("\t", " ").replaceAll(" {2,}", " ");
		String[] ss = str.split(" ");
		for (String s : ss) {
			System.out.println("-"+s+"-");
		}
	}
	
	public static boolean rmDir(String path) {
		return rmDir(new File(path));
	}
	
	public static boolean rmDir(File file) {
		boolean isOk = false;
		
		if (!file.exists()) {
			return true;
		}
		
		if (file.isFile()) {
			return file.delete();
		}
		
		//file.isDirectory()
		File [] subFiles = file.listFiles();
		if (subFiles == null || subFiles.length<=0) {
			return file.delete();
		}
		
		for (File f : subFiles) {
			if (f.isFile()) {
				isOk = f.delete();
				if (!isOk) return isOk;
			} else if (f.isDirectory()) {
				isOk = rmDir(f);
				if (!isOk) return isOk;
			}
		}
		
		return file.delete();
	}
}
