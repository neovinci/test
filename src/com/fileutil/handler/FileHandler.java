package com.fileutil.handler;

import com.fileutil.FileNameFilter;
import com.fileutil.entity.ReportNote;
import com.fileutil.unmarshal.UnmarshallerUtil;

import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
	public boolean execute(File configDir) throws FileNotFoundException, MarshalException, ValidationException
	{
		boolean retVal = false;
		List<ReportNote> reportNotes = null;

		try
		{
			FileNameFilter customFileNameFilter = new FileNameFilter();
			customFileNameFilter.setContains("test");
			customFileNameFilter.setEndsWith(".xml");

			List<File> configFiles = new ArrayList<>();

			addConfigurationFiles(configFiles, configDir, customFileNameFilter);


			if (!configFiles.isEmpty())
			{
				reportNotes = new ArrayList<>();
				for (File configFile : configFiles)	{
					ReportNote reportNote = (ReportNote) UnmarshallerUtil.unmarshal(configFile, "com.fileutil.entity");
					reportNotes.add(reportNote);
				}
			} else
			{
				System.out.println("Nothing to do... No Point Of Sale Manage Configuration Files Found");
			}

			retVal = true;
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally
		{
//			rollbackOrCommitAndRelease(dbAccess, dbServer, retVal);
		}

		for (ReportNote reportNote : reportNotes) {
			System.out.println(reportNote.toString());
		}

		return retVal;
	}

	protected void addConfigurationFiles(List<File> configFiles, File dir, FileNameFilter filenameFilter)
	{
		List<File> dirToProcess = new ArrayList<>();

		if ((dir != null) && dir.exists())
		{
			String[] orderedList = dir.list();
			Arrays.sort(orderedList);

			for (String filePath : orderedList)
			{
				File file = new File(dir, filePath);

				if (file.isFile() && filenameFilter.accept(file, file.getName()))
				{
					configFiles.add(file);
				}
				else if (file.isDirectory())
				{
					dirToProcess.add(file);
				}
			}
		}

		for (File tempDir : dirToProcess)
		{
			addConfigurationFiles(configFiles, tempDir, filenameFilter);
		}
	}
}
