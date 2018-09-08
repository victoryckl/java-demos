package cn.how2j.multithreading;

public class TestThread1 {

	public static void main(String[] args) {
		Hero1 gareen = new Hero1();
		gareen.name = "¸ÇÂ×";
		gareen.hp = 616;
		gareen.damage = 50;
		
		Hero1 teemo = new Hero1();
		teemo.name = "ÌáÄª";
		teemo.hp = 300;
		teemo.damage = 30;
		
		Hero1 bh = new Hero1();
		bh.name = "ÉÍ½ğÁÔÈË";
		bh.hp = 500;
		bh.damage = 65;
		
		Hero1 leesin = new Hero1();
		leesin.name = "Ã¤É®";
		leesin.hp = 455;
		leesin.damage = 80;
		
        KillThread killThread1 = new KillThread(gareen,teemo);
        killThread1.start();
        //KillThread killThread2 = new KillThread(bh,leesin);
        //killThread2.start();
        
        Battle battle2 = new Battle(bh,leesin);
        new Thread(battle2).start();
	}

}
