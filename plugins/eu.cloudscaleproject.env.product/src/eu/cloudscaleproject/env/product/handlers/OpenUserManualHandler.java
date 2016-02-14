 
package eu.cloudscaleproject.env.product.handlers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Execute;

public class OpenUserManualHandler {
	@Execute
	public void execute() {
			File workspaceFolder = Platform.getLocation().toFile();
			File usermanual = new File(workspaceFolder, "usermanual.pdf");
			
			if (!usermanual.exists())
			{
				InputStream is = getClass().getClassLoader().getResourceAsStream("resources/docs/userguide.pdf");
				try {
					Files.copy(is, usermanual.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			try {
				Desktop.getDesktop().open(usermanual);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
}