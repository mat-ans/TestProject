package it.csttech.test;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class FTask implements Callable<Void>
{
    private Future<TaskResult> future = null;
    
    public FTask(Future<TaskResult> future)
    {
        this.future = future;
    }

    @Override
    public Void call() throws Exception
    {
        System.out.println(future.get());
        return null;
    }

    
}
