package cn.how2j.multithreading;

public class Hero1 {
	public String name;
	public float hp;
	
	public int damage;
	
	public void attackHero(Hero1 h) {
		try {
			Thread.sleep(100);//Ϊ�˱�ʾ������Ҫʱ�䣬ÿ�ι�����ͣ100����
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		h.hp -= damage;
		System.out.format("%s ���ڹ��� %s��%s��Ѫ����� %.0f%n", name,h.name,h.name,h.hp);
		if (h.isDead()) {
			System.out.println(h.name+"���ˣ�");
		}
	}
	
	public boolean isDead() {
		return hp <= 0;
	}
}
