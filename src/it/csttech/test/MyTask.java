package it.csttech.test;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyTask implements Callable<TaskResult>
{
    private int i;
    
    public MyTask(int i)
    {
        this.i = i;
    }

    @Override
    public TaskResult call() throws Exception
    {
        Random rand = new Random();
        int max = 10;
        int min = 1;
        
        int randomNum = rand.nextInt((max - min) + 1) + min;
        
        System.out.println("start task n." + i + " sleeping for " + randomNum +  " s.");
        Thread.sleep(randomNum * 1000);
        System.out.println("end task n." + i );
        
        return new TaskResult(randomNum, i);
    }

    public int getI()
    {
        return i;
    }
    
    

}
