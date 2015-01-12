package eu.cloudscaleproject.env.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip
{
	/**
	 * Unzip it
	 * 
	 * @param zipFile
	 *            input zip file
	 * @param output
	 *            zip file output folder
	 * @throws FileNotFoundException
	 */

	public static void unZip(String zipFile, String outputFolder) throws IOException
	{

		unZip(new FileInputStream(zipFile), outputFolder);
	}

	public static void unZip(InputStream zipStream, String outputFolder) throws IOException
	{

		byte[] buffer = new byte[1024];

		// create output directory is not exists
		File folder = new File(outputFolder);
		if (!folder.exists())
		{
			folder.mkdir();
		}

		// get the zip file content
		ZipInputStream zis = new ZipInputStream(zipStream);
		// get the zipped file list entry
		ZipEntry ze = zis.getNextEntry();

		while (ze != null)
		{

			String fileName = ze.getName();
			File newFile = new File(outputFolder + File.separator + fileName);

			System.out.println("file unzip : " + newFile.getAbsoluteFile());

			// create all non exists folders
			// else you will hit FileNotFoundException for compressed folder
			if (ze.isDirectory())
			{
				newFile.mkdirs();
			}
			else
			{
				// To be sure, create subdirs
			new File(newFile.getParent()).mkdirs();

			FileOutputStream fos = new FileOutputStream(newFile);

			int len;
			while ((len = zis.read(buffer)) > 0)
			{
				fos.write(buffer, 0, len);
			}

			fos.close();
				
			}

			ze = zis.getNextEntry();
		}

		zis.closeEntry();
		zis.close();

		System.out.println("Done");
	}
}
