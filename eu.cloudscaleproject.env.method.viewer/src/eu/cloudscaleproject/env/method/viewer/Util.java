package eu.cloudscaleproject.env.method.viewer;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;

public class Util {

	public static void createMethod(IFile ifile){
		
		
		InputStream in  = null;
		try {
			in = Util.class.getClassLoader().getResourceAsStream("method/method.workflow");
			ifile.create(in, true, null);
			in.close();
		    
		} catch (Exception e1) {
		    e1.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
