package edu.miu.cs.auctionproject.dynamicscheduling;

import edu.miu.cs.auctionproject.controller.BidController;
import edu.miu.cs.auctionproject.controller.ProductController;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;

@Component
public class MyJobPaymentDueDate implements SchedulingConfigurer {
	private long product = 0;
//	private long prodId = 0;
	LocalDateTime dueBeginner = LocalDateTime.of(LocalDate.of(2030, 12, 11)
			, LocalTime.of(22, 38, 0));


	@Autowired
	ProductController productController;


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
				productController.paymentDatedue(selectedProdId());

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
		System.out.println("-------------inside getExecution" + timePaymentHashMap(product,dueBeginner));
		return 4000000;
	}


	long minutes = 50000;
	long simon = 15000;
	long selectedId = 0;

	static TreeMap<Long, Long> paymentdueDate = new TreeMap<>();
	public long timePaymentHashMap(Long productId, LocalDateTime due) {
		System.out.println(productId +"and ---- insde due date:" + paymentdueDate);
		LocalDateTime dueProduct = due;
		long prodId = productId;
		minutes = 1000000000000000000L;

		if(prodId == 0 || dueProduct == dueBeginner){
			return minutes;
		};

		long dueEpochi = dueProduct.atZone(ZoneId.of("America/Chicago")).toInstant().toEpochMilli()
				- LocalDateTime.now().atZone(ZoneId.of("America/Chicago")).toInstant().toEpochMilli();

		paymentdueDate.put(dueEpochi, prodId);
		System.out.println(paymentdueDate.size() + " " + paymentdueDate.firstKey());
		minutes = paymentdueDate.firstKey();
		System.out.println(paymentdueDate.toString());

				selectedId = paymentdueDate.get(minutes);
				System.out.println("id" + product);

		System.out.println(product);
		return minutes;
	}

	public long selectedProdId(){
		return selectedId;
	}

		@Bean
		public TaskScheduler poolPaymentScheduler() {
			ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
			scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
			scheduler.setPoolSize(1);
			scheduler.initialize();
			return scheduler;
		}


	}

