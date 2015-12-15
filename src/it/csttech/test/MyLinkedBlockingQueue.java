package it.csttech.test;

import java.util.concurrent.LinkedBlockingQueue;

public class MyLinkedBlockingQueue<T> extends LinkedBlockingQueue<T>{
    
    private static final long serialVersionUID = 1L;
    
    public MyLinkedBlockingQueue(int capacity)
    {
        super(capacity);
    }
    
    @Override
    public void put(T item)
    {
        
        try
        {
            super.put(item);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean offer(T item){
        System.out.println("offer -> put element n." + (this.size() + 1));
        
        put(item);
        
        return true;
        // return super.offer(item);
        
    }
    
    
}
