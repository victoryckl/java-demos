package cn.how2j.multithreading;

public class TestThread2 {

	public static void main(String[] args) {
		final Hero2 gareen = new Hero2();
        gareen.name = "¸ÇÂ×";
        gareen.hp = 16;
             
        Thread t1 = new Thread(){
            public void run(){
                while(true){
                       
                    //ÎÞÐèÑ­»·ÅÐ¶Ï
//                    while(gareen.hp==1){
//                        continue;
//                    }
                       
                    gareen.hurt();
                     
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
   
            }
        };
        t1.start();
   
        Thread t2 = new Thread(){
            public void run(){
                while(true){
                    gareen.recover();
   
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
   
            }
        };
        t2.start();
	}

}
