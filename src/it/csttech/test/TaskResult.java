package it.csttech.test;

public class TaskResult
{
    private int result;
    private int taskNumber;
    
    public TaskResult(int result, int taskNumber)
    {
        this.result = result;
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("TaskResult [result=");
        builder.append(result);
        builder.append(", taskNumber=");
        builder.append(taskNumber);
        builder.append("]");
        return builder.toString();
    }

    
    
}
