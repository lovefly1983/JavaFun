package fun.multithread;// Unrolled and not inlined version with 2 readers and 2 writers
// No data structure used for readers and writers to work with
// Specific notification locks are used to improve performance

class RWnotif {
    static Controller ctl;

    public static void main (String argv[]) {
        ctl = new Controller();
        new Writer(ctl, "writer2").start();
        new Reader(ctl, "reader1").start();
        new Reader(ctl, "reader3").start();
        new Writer(ctl, "writer1").start();
    }
}


final class Reader extends Thread {
    private Controller ctl;
    private String name_;

    public Reader(Controller c, String name) { 
        ctl = c; 
        name_ = name;
    }

    public void run()
    {
        while (true) {
            ctl.startRead();
            System.out.println(name_ + " reading:###########################");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(name_ + " done reading:");
            ctl.stopRead();
        }
    }
}

final class Writer extends Thread {
    private Controller ctl;
    private String name_;

    public Writer(Controller c, String name) { ctl = c; name_ = name;}  

    public void run()  
    {  
        while (true) {
            ctl.startWrite();
            System.out.println(name_ + " writing:..................................");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(name_ + " done writing");
            ctl.stopWrite();
        }
    }
}

class Controller
{
    private int nr = 0;
    private int nw = 0;
    private Object or = new Object();
    private Object ow =  new Object();

    public void startRead()
    {
        synchronized(or) {
            while(!isOkToRead()) {
                try{
                    or.wait();
                }
                catch(InterruptedException e){
                    
                }
            }
        }
    }

    private synchronized boolean isOkToRead()
    {
        if(nw == 0)
        {
            nr++;
            return true;
        }
        else return false;
    }

    public void stopRead()
    {
        synchronized(this) {nr--;}
        synchronized(ow) {ow.notify();}
    }

    public void startWrite()
    {
        synchronized(ow)
        {
            while(!isOkToWrite()) {
                try{
                    ow.wait();
                }
                catch(InterruptedException e) {
                    System.out.println("dfdf");
                }
            }
        }
    }

    private synchronized boolean isOkToWrite()
    {
        if((nw == 0) && (nr == 0))
        {
            nw++;
            return true;
        }
        else return false;
    }

    public void stopWrite()
    {
        synchronized(this) {nw--;}
        synchronized(or) {or.notifyAll();}
        synchronized(ow) {ow.notifyAll();}
    }
}