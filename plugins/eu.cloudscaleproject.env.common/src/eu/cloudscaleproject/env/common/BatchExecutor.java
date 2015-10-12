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
	
	private volatile Map<BatchKey, Long> queueTime = new HashMap<BatchKey, Long>();
	private volatile Map<BatchKey, BatchRunnable> queueTask = new HashMap<BatchKey, BatchRunnable>();
	
	private static class BatchKey{

		public final Object source;
		public final Object key;
		
		public BatchKey(Object source, Object key) {
			this.source = source;
			this.key = key;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((source == null) ? 0 : source.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BatchKey other = (BatchKey) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (source == null) {
				if (other.source != null)
					return false;
			} else if (!source.equals(other.source))
				return false;
			return true;
		}
	}
	
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
			
			final Map<BatchKey, Runnable> runTask = new HashMap<BatchKey, Runnable>();
						
			while(!Thread.interrupted()){
				
				while(!queueTask.isEmpty()){
															
					//execute tasks and remove them from the queue
					synchronized (lock) {
						
						for(Entry<BatchKey, Long> entry : queueTime.entrySet()){
							
							Long currentTime = System.currentTimeMillis();
							if(currentTime - entry.getValue() > DELAY_TIME){
								runTask.put(entry.getKey(), queueTask.get(entry.getKey()));
							}
						}
						
						//remove processed
						for(BatchKey o : runTask.keySet()){
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
	
	public void addTask(Object source, Object key, Runnable task){
		synchronized (lock) {
			Long time = System.currentTimeMillis();
			BatchKey batchKey = new BatchKey(source, key);
			queueTime.put(batchKey, time);
			queueTask.put(batchKey, new BatchRunnable(task, false));
			lock.notifyAll();
		}
	}
	
	public void addUITask(Object source, Object key, Runnable task){
		synchronized (lock) {
			Long time = System.currentTimeMillis();
			BatchKey batchKey = new BatchKey(source, key);
			queueTime.put(batchKey, time);
			queueTask.put(batchKey, new BatchRunnable(task, true));
			lock.notifyAll();
		}
	}

}
