package eu.cloudscaleproject.env.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.widgets.Display;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public class BatchExecutor {
	
	private static final Long DELAY_TIME = 200l;
	
	private static BatchExecutor instance = null;
	public static BatchExecutor getInstance(){
		if(instance == null){
			instance = new BatchExecutor();
		}
		return instance;
	}
	
	private static volatile Object lock = new Object();
	
	private volatile Map<Object, Long> queueTime = new HashMap<Object, Long>();
	private volatile Map<Object, BatchRunnable> queueTask = new HashMap<Object, BatchRunnable>();
	
	private static class BatchRunnable implements Runnable{
		
		private final Runnable runnable;
		private final boolean ui;
		
		public BatchRunnable(Runnable runnable, boolean ui) {
			this.runnable = runnable;
			this.ui = ui;
		}
		
		@Override
		public void run() {
			if(ui){
				Display.getDefault().asyncExec(runnable);
			}
			else{
				runnable.run();
			}
		}
	}
	
	private final Thread executor = new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			final Map<Object, Runnable> runTask = new HashMap<Object, Runnable>();
						
			while(!Thread.interrupted()){
				
				while(!queueTask.isEmpty()){
															
					//execute tasks and remove them from the queue
					synchronized (lock) {
						
						for(Entry<Object, Long> entry : queueTime.entrySet()){
							
							Long currentTime = System.currentTimeMillis();
							if(currentTime - entry.getValue() > DELAY_TIME){
								runTask.put(entry.getKey(), queueTask.get(entry.getKey()));
							}
						}
						
						//remove processed
						for(Object o : runTask.keySet()){
							queueTask.remove(o);
							queueTime.remove(o);
						}
					}
					
					//execute tasks
					for(Runnable runnable : runTask.values()){
						try{
							runnable.run();
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
					
					runTask.clear();
					
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
	
	private BatchExecutor() {
		executor.start();
	}
	
	public void addTask(Object key, Runnable task){
		synchronized (lock) {
			Long time = System.currentTimeMillis();
			queueTime.put(key, time);
			queueTask.put(key, new BatchRunnable(task, false));
			lock.notifyAll();
		}
	}
	
	public void addUITask(Object key, Runnable task){
		synchronized (lock) {
			Long time = System.currentTimeMillis();
			queueTime.put(key, time);
			queueTask.put(key, new BatchRunnable(task, true));
			lock.notifyAll();
		}
	}

}
