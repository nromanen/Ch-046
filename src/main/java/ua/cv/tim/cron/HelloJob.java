package ua.cv.tim.cron;

/**
 * Created by rmochetc on 07.02.2017.
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements Job
{
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        System.out.println("Time: " + new Date() + ". Hello Quartz!");

    }

}