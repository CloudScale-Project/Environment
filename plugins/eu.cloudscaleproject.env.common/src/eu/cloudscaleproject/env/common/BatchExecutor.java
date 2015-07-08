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
	
	private volatile Object lock = new Object();
	
	private volatile Map<Object, Long> queueTime = new HashMap<Object, Long>();
	private volatile Map<Object, Runnable> queueTask = new HashMap<Object, Runnable>();
	
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
								try{
									queueTask.get(entry.getKey()).run();
								}
								catch(Exception e){
									e.printStackTrace();
								}
								queueTask.remove(entry.getKey());
								iter.remove();
							}
						}
					}
				}
				
				//wait until next entry arrives
				try {
					synchronized (lock) {
						if(queueTask.isEmpty()){
							lock.wait();
						}
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
