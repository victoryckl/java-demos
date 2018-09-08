package cn.how2j.multithreading;

public class Battle implements Runnable {
    
    private Hero1 h1;
    private Hero1 h2;
 
    public Battle(Hero1 h1, Hero1 h2){
        this.h1 = h1;
        this.h2 = h2;
    }
 
    public void run(){
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}