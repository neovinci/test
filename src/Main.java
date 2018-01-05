import com.fileutil.handler.FileHandler;

import javax.xml.bind.MarshalException;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		String customWorkingDirStr = System.getProperty("data.dir");
		String defaultWorkingDirStr = System.getProperty("user.dir");
		File workingDir;
		File configDir;
		if (customWorkingDirStr != null && !customWorkingDirStr.isEmpty())
		{
			workingDir = new File(customWorkingDirStr);
			configDir = new File(customWorkingDirStr);
		}
		else
		{
			workingDir = new File(defaultWorkingDirStr);
			configDir = new File(defaultWorkingDirStr, "config");
		}

		FileHandler fileHandler = new FileHandler();
		try {
			fileHandler.execute(configDir);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}


	}
}
