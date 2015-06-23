package eu.cloudscaleproject.env.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class BatchExecutor {
	
	private static final Long DELAY_TIME = 1000l;
	
	private static BatchExecutor instance = null;
	public static BatchExecutor getInstance(){
		if(instance == null){
			instance = new BatchExecutor();
		}
		return instance;
	}
	
	private final Object lock = new Object();
	
	private final Map<Object, Long> queueTime = new HashMap<Object, Long>();
	private final Map<Object, Runnable> queueTask = new HashMap<Object, Runnable>();
	
	private final Thread executor = new Thread(new Runnable() {
		
		@Override
		public void run() {
						
			while(!Thread.interrupted()){
				
				while(!queueTask.isEmpty()){
										
					//execute tasks and remove them from the queue
					synchronized (lock) {
						Iterator<Entry<Object, Long>> iter = queueTime.entrySet().iterator();
						while(iter.hasNext()){
							
							Entry<Object, Long> entry = iter.next();							
							Long currentTime = System.currentTimeMillis();

							if(currentTime - entry.getValue() > DELAY_TIME){
								queueTask.get(entry.getKey()).run();
								queueTask.remove(entry.getKey());
								iter.remove();
							}
						}
					}
					
					//wait for 30ms
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//wait until next entry arrives
				try {
					synchronized (lock) {
						lock.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}, "Batch executor");
	
	public BatchExecutor() {
		executor.start();
	}
	
	public void addTask(Object key, Runnable task){
		synchronized (lock) {
			Long time = System.currentTimeMillis();
			queueTime.put(key, time);
			queueTask.put(key, task);
			lock.notifyAll();
		}
	}

}
