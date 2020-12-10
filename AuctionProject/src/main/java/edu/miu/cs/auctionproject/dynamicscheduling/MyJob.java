package edu.miu.cs.auctionproject.dynamicscheduling;

import edu.miu.cs.auctionproject.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;


@Configuration
public class MyJob implements SchedulingConfigurer  {



	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		 //taskRegistrar.setScheduler(poolScheduler());
	     taskRegistrar.addTriggerTask(new Runnable() {
	            @Override
	            public void run() {
	                // Do not put @Scheduled annotation above this method, we don't need it anymore.
	               System.out.println("Running Schedular");
	            }
	        }, new Trigger() {
	            @Override
	            public Date nextExecutionTime(TriggerContext triggerContext) {
	                Calendar nextExecutionTime = new GregorianCalendar();
	                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
	                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
	                nextExecutionTime.add(Calendar.MILLISECOND, getNewExecutionTime());
	                return nextExecutionTime.getTime();
	            }
	        });
	}
	
	private static int getNewExecutionTime() {
		//Load Your execution time from database or property file
		System.out.println("this one ----");
		return getNewExecutionTimeToBeSent(min);
	}

	static int min = 80000;
	public static int timeHashMap(Product product, LocalDateTime due){

		System.out.println("--------inside---bini-----");
		System.out.println(LocalDateTime.now().getSecond());
		System.out.println("duedateeeeee"+due);

        int min = due.getSecond() - LocalDateTime.now().getSecond();

		System.out.println("--------"+ min +"---bini-----");
		Map<Product, LocalDateTime> dueDate = new HashMap<>();
		dueDate.put(product,due);

		for(LocalDateTime d : dueDate.values()){
			System.out.println(d.getSecond() - LocalDateTime.now().getSecond());
			System.out.println("d-------"+LocalDateTime.now().getSecond());
			if(min > d.getSecond() - LocalDateTime.now().getSecond()){
				min = d.getSecond() - LocalDateTime.now().getSecond();
				//to be continued
				System.out.println(min);
			}

		}
		return min;

	}

	private static int getNewExecutionTimeToBeSent(int min) {
		return min;
	}


	@Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

}
