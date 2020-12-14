package edu.miu.cs.auctionproject.dynamicscheduling;

import edu.miu.cs.auctionproject.controller.DepositPaymentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class MyJobThirtyDaysAfterShipping implements SchedulingConfigurer {
	private long productId = 0;
	LocalDateTime dueBeginner = LocalDateTime.of(LocalDate.of(2030, 12, 11)
			, LocalTime.of(22, 38, 0));


	@Autowired
	DepositPaymentController depositPaymentController;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		System.out.println(taskRegistrar);
		taskRegistrar.addTriggerTask(new Runnable() {
			//fix the scheduler for 30 days
			@Scheduled(fixedRate = 10000)
			@Override
			public void run() {

				// Do not put @Scheduled annotation above this method, we don't need it anymore.
				System.out.println("Running Scheduler thiry days ----");
				System.out.println("product id =" + selectedProdId());
				depositPaymentController.returnPaymentToSeller(selectedProdId());


			}

		}, triggerContext -> {
			Calendar nextExecutionTime = new GregorianCalendar();
			Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
			nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
			nextExecutionTime.add(Calendar.MILLISECOND, (int) getNewExecutionTime());
			return nextExecutionTime.getTime();
		});
	}


	public long getNewExecutionTime() {
		System.out.println("-------------inside getExecution thirty" + timeThirtyDaysHashMap(productId));
		return timeThirtyDaysHashMap(productId);
	}


	long minutes = 50000;
	long simon = 15000;
	long selectedId = 0;

	Queue<Long> queue = new LinkedList<>();
	public long timeThirtyDaysHashMap(Long productId) {

		long prodId = productId;
		minutes = 10000000008888L;
		if(prodId == 0){
			return minutes;
		};

		long threeDaysEpochi = LocalDateTime.of(LocalDate.of(1970, 1, 3)
				, LocalTime.of(0, 0, 0)).atZone(ZoneId.of("America/Chicago")).toInstant().toEpochMilli();

		queue.add(prodId);
		System.out.println("-----"+queue.toString());
		selectedId = queue.peek();
		System.out.println("----------selectedId thirty "+ selectedId+"---");
		queue.remove();
		System.out.println(queue);
		return 200000000;
	}

	public long selectedProdId(){
		return selectedId;
	}

	@Bean
	public TaskScheduler poolSchedulerThirtyDays() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		scheduler.setPoolSize(3);
		scheduler.initialize();
		return scheduler;
	}


}

