package com.fileutil;

import java.io.File;

public class FileNameFilter {
	/** contains value */
	private String m_contains;
	/** starts with value */
	private String m_startsWith;
	/** ends with value */
	private String m_endsWith;

	/**
	 * Get the contents value
	 * @return the contents value
	 */
	public String getContains()
	{
		return m_contains;
	}

	/**
	 * Set the contains value
	 * @param contains contains with value
	 */
	public void setContains(String contains)
	{
		this.m_contains = contains;
	}

	/**
	 * Get the starts with value
	 * @return the starts with value
	 */
	public String getStartsWith()
	{
		return m_startsWith;
	}

	/**
	 * Set the starts with value
	 * @param startsWith the starts with value
	 */
	public void setStartsWith(String startsWith)
	{
		this.m_startsWith = startsWith;
	}

	/**
	 * Get the ends with value
	 * @return the ends with value
	 */
	public String getEndsWith()
	{
		return m_endsWith;
	}

	/**
	 * Set the ends with value
	 * @param endsWith the ends with value
	 */
	public void setEndsWith(String endsWith)
	{
		this.m_endsWith = endsWith;
	}

	/**
	 * Tests if a specified file should be included in a file list.
	 *
	 * @param dir the directory in which the file was found.
	 * @param name the name of the file
	 * @return true if and only if the name should be included in the file list; false otherwise.
	 */
	public boolean accept(File dir, String name)
	{
		if (m_contains == null && m_startsWith == null && m_endsWith == null)
		{
			return false;
		}

		boolean criteraMatch = true;

		if (m_contains != null)
		{
			if (!name.toLowerCase().contains(m_contains.toLowerCase()))
			{
				criteraMatch = false;
			}
		}

		if (m_startsWith != null)
		{
			if (!name.toLowerCase().startsWith(m_startsWith.toLowerCase()))
			{
				criteraMatch = false;
			}
		}

		if (m_endsWith != null)
		{
			if (!name.toLowerCase().endsWith(m_endsWith.toLowerCase()))
			{
				criteraMatch = false;
			}
		}

		return criteraMatch;
	}

	/**
	 * Get the source control version for this file.
	 *
	 * @return <code>$Revision: 1.0 $, $Date: 0000/00/00 00:00:00 $";</code>
	 */
	public static String getVersion()
	{
		return "$Revision: 1.0 $, $Date: 2000/00/00 00:00:00 $";
	}
}
