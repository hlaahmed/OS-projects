package javaapplication5;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
 abstract class MySemaphore {
        protected abstract void Block();
        protected abstract void Wakeup();
        protected int value;
        protected MySemaphore(int initialvalue) {
            value = initialvalue;
        }
  
}
public final class countingSemaphore extends MySemaphore {
public countingSemaphore(int initialvalue) {super(initialvalue);}
synchronized public void Block() {
value--;
if (value<0)
try { System.out.println("blocked");
    wait(); } catch (InterruptedException e) {}
}
synchronized public void Wakeup() {
++value;
if (value <=0)
{
    System.out.println("released");
    notify();
}

}
}
class productnum
{
    int number=10;
    void incrementnum()
    {
        number++;
    }
     void decrementnum()
    {
        number--;
    }
}

 class thread1 extends Thread{
    MySemaphore mySemaphore;
    productnum  number;
    

    thread1( MySemaphore mySemaphore,productnum number){
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Block();
       //critical section
        number.decrementnum();
        System.out.println("product number is "+number.number+" after t1");
        mySemaphore.Wakeup();
        
        
    }
}
class thread2 extends Thread{
     MySemaphore mySemaphore;
      productnum  number;
     
    thread2( MySemaphore mySemaphore,productnum number){
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Block();
        //critical section
        number.incrementnum();
        System.out.println("product number is "+number.number+" after t2");
        
        mySemaphore.Wakeup();
       
    }
}
class thread3 extends Thread{
    MySemaphore mySemaphore;
     productnum  number;
    
    thread3( MySemaphore mySemaphore,productnum number){
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Block();
        //critical section
        number.incrementnum();
        System.out.println("product number is "+number.number+" after t3");

        mySemaphore.Wakeup();
        
    }
  }
class thread4 extends Thread{
     MySemaphore mySemaphore;
      productnum  number;
      
    thread4( MySemaphore mySemaphore,productnum number)
    {
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Block();
        //critical section
        number.decrementnum();
        System.out.println("product number is "+number.number+" after t4");
        
       mySemaphore.Wakeup();
      
      
    }
}
class TestSemaphore{
    public static void main(String args[]) {
     
        MySemaphore mySemaphore = new countingSemaphore(1);
        productnum number = new productnum();
     
        thread1 t1=new thread1(mySemaphore,number);
        thread2 t2=new thread2(mySemaphore,number);
        thread3 t3=new thread3(mySemaphore,number);
        thread4 t4=new thread4(mySemaphore,number);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        

} }