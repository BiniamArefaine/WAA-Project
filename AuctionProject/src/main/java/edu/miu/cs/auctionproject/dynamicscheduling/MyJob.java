package edu.miu.cs.auctionproject.dynamicscheduling;

import edu.miu.cs.auctionproject.controller.BidController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
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
public class MyJob implements SchedulingConfigurer {
	private long product = 0;
//	private long prodId = 0;
	LocalDateTime dueBeginner = LocalDateTime.of(LocalDate.of(2030, 12, 11)
			, LocalTime.of(22, 38, 0));


	@Autowired
	BidController bidController;


	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		System.out.println(taskRegistrar);
		taskRegistrar.addTriggerTask(new Runnable() {
			@Scheduled(fixedRate = 60000)
			@Override
			public void run() {

				// Do not put @Scheduled annotation above this method, we don't need it anymore.
				System.out.println("Running Scheduler----");
				System.out.println("product id =" + selectedProdId());
				bidController.scheduler(selectedProdId());

			}

		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				Calendar nextExecutionTime = new GregorianCalendar();
				Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
				nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
				nextExecutionTime.add(Calendar.MILLISECOND, (int) getNewExecutionTime());
				return nextExecutionTime.getTime();
			}
		});
	}


	public long getNewExecutionTime() {
		System.out.println("-------------inside getExecution" + timeHashMap(product,dueBeginner));
		return 60000;
	}


	long minutes = 50000;
	long simon = 15000;
	long selectedId = 0;

	static TreeMap<Long, Long> dueDate = new TreeMap<>();
	public long timeHashMap(Long productId, LocalDateTime due) {
		System.out.println(productId +"and ---- insde due date:" + dueDate);
		LocalDateTime dueProduct = due;
		long prodId = productId;
		minutes = 1000000000000000000L;

		if(prodId == 0 || dueProduct == dueBeginner){
			return minutes;
		};

		long dueEpochi = dueProduct.atZone(ZoneId.of("America/Chicago")).toInstant().toEpochMilli()
				- LocalDateTime.now().atZone(ZoneId.of("America/Chicago")).toInstant().toEpochMilli();

		dueDate.put(dueEpochi, prodId);
		System.out.println(dueDate.size() + " " + dueDate.firstKey());
		minutes = dueDate.firstKey();
		System.out.println(dueDate.toString());

				selectedId = dueDate.get(minutes);
				System.out.println("id" + product);

		System.out.println(product);
		return minutes;
	}

	public long selectedProdId(){
		return selectedId;
	}

		@Bean
		public TaskScheduler poolScheduler () {
			ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
			scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
			scheduler.setPoolSize(1);
			scheduler.initialize();
			return scheduler;
		}


	}

