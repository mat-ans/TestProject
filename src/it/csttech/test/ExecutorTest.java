package it.csttech.test;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest
{
    private long count;
    
    public ExecutorTest()
    {
        Collection<Future<TaskResult>> futures = new ConcurrentLinkedQueue<>();
        
        ExecutorService executor = newFixedThreadPool();
        ExecutorService fe = futureThreadPool();
        
        for (int i = 0; i < 40 ; i++)
        {
            Future<TaskResult> future =  executor.submit(new MyTask(i));
            fe.submit(new FTask(future));
            
        }

        System.out.println("future execution service start shutdown");
        // attendo che tutti i task siano completati
        // syncExecutor(futures, executor);
        fe.shutdown();
        
        System.out.println("future execution service shutdown");
        
        executor.shutdown();
        
        System.out.println("After shutdown");
        
    }
    
    public static ExecutorService futureThreadPool() 
    {
        int  corePoolSize  =    5;
        int  maxPoolSize   =   10;
        long keepAliveTime = 5000;

        
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                                      keepAliveTime, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }

    
    
    public static ExecutorService newFixedThreadPool() 
    {
        int  corePoolSize  =    5;
        int  maxPoolSize   =   10;
        long keepAliveTime = 5000;

        
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                                      keepAliveTime, TimeUnit.MILLISECONDS,
                                      new MyLinkedBlockingQueue<Runnable>(20));
    }

    
    public void syncExecutor(Collection<Future<TaskResult>> futures, ExecutorService executor)
    {
        while(futures.size() > 0)
        {
            System.out.println("Queue length " + ((ThreadPoolExecutor) executor).getQueue().size());
            for(Future<TaskResult> future : futures){
                
                if (future.isDone()) {
                    try
                    {
                        System.out.println(future.get());
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                    futures.remove(future);
                }
            }
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            
        }
    }
    
    
    public static void main(String[] args)
    {
        
        new ExecutorTest();
    }
    
    

}
