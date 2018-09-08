package cn.how2j.multithreading;

public class Hero1 {
	public String name;
	public float hp;
	
	public int damage;
	
	public void attackHero(Hero1 h) {
		try {
			Thread.sleep(100);//为了表示攻击需要时间，每次攻击暂停100毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		h.hp -= damage;
		System.out.format("%s 正在攻击 %s，%s的血变成了 %.0f%n", name,h.name,h.name,h.hp);
		if (h.isDead()) {
			System.out.println(h.name+"死了！");
		}
	}
	
	public boolean isDead() {
		return hp <= 0;
	}
}
