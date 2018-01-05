package com.fileutil.handler;

import com.fileutil.FileNameFilter;

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

		try
		{
			FileNameFilter customFileNameFilter = new FileNameFilter();
			customFileNameFilter.setContains("PointOfSaleManageSvRQ");
			customFileNameFilter.setEndsWith(".xml");

			List<File> configFiles = new ArrayList<>();

			addConfigurationFiles(configFiles, configDir, customFileNameFilter);


//			if (!configFiles.isEmpty())
//			{
//					for (File configFile : configFiles)
//					{
//						Reader replacedPOS = replaceProperties(configFile);
//						PointOfSaleManageSvRQ pointOfSaleManageSvRQ = (PointOfSaleManageSvRQ) CastorUtil.unmarshall(replacedPOS,
//								PointOfSaleManageSvRQ.class);
//						PointOfSaleCreateRQ[] pointOfSaleCreateRQAry = pointOfSaleManageSvRQ.getPointOfSaleCreateRQ();
//						for (PointOfSaleCreateRQ pointOfSaleCreateRQ : pointOfSaleCreateRQAry)
//						{
//							String posCode = pointOfSaleCreateRQ.getCreatePointOfSale().getPointOfSale()
//									.getPointOfSaleCode();
//
//							Number posVersion = getPOSVersion(posCode, posSearch, dbAccess);
//
//							if (posVersion != null)
//							{
//								boolean overwrite = confirmOverwrite("A Point of Sale " + posCode
//										+ " already exists in the database.\nDo you want to overwite it?");
//
//								if (overwrite)
//								{
//									getUIUtil().displayMessage("Overwriting Existing Point Of Sale - Code: %s", posCode);
//
//									pointOfSaleCreateRQ.getCreatePointOfSale().getPointOfSale().setPointOfSaleVersion(
//											posVersion.intValue());
//
//									installPOS(pointOfSaleCreateRQ.getCreatePointOfSale().getPointOfSale(),
//											true, posModify, dbAccess);
//								}
//								else
//								{
//									getUIUtil().displayMessage("Skipping Overwriting of Point Of Sale - Code: %s", posCode);
//								}
//							}
//							else
//							{
//								// Install
//								getUIUtil().displayMessage("Installing Point Of Sale - Code: %s", posCode);
//								installPOS(pointOfSaleCreateRQ.getCreatePointOfSale().getPointOfSale(), false, posCreate, dbAccess);
//							}
//						}
//					}
//				}
//			else
//			{
//				getUIUtil().displayMessage("Nothing to do... No Point Of Sale Manage Configuration Files Found");
//			}

			retVal = true;
		}
		finally
		{
//			rollbackOrCommitAndRelease(dbAccess, dbServer, retVal);
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
