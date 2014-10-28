package eu.cloudscaleproject.env.csm2pcm;

import org.eclipse.m2m.qvt.oml.util.Log;

public class TransformationLogger implements Log{

	@Override
	public void log(int level, String message, Object param) {
		System.out.println(message+param.toString());
	}
	
	@Override
	public void log(String message, Object param) {
		System.out.println(message+param.toString());	
	}
	
	@Override
	public void log(int level, String message) {
		System.out.println(message);
		
	}
	
	@Override
	public void log(String message) {
		System.out.println(message);
	}
}
