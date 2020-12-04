package com.APD.TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.reporter.reportengine.extentreports.ExtentReport;
import com.taf.core.TestApplication;
import com.taf.core.TestEngine;
import com.taf.impl.selenium.SeleniumTestEngine;
import com.testlytic.testng.base.TestlyticTestNGBase;

/**
 * The Class BaseTest.
 */
public class BaseTest extends TestlyticTestNGBase{

	/** The test engine. */
	protected ThreadLocal<TestEngine> testEngine = new ThreadLocal<TestEngine>();

	/** The test application. */
	protected ThreadLocal<TestApplication> testApplication = new ThreadLocal<TestApplication>();

	/** The extent reporter. */
	public static final ExtentReport EXTENTREPORTER = new ExtentReport();
	
	/**
	 * Load configuration.
	 */
	@BeforeSuite(alwaysRun = true)
	public void loadConfiguration() throws Exception {
		EXTENTREPORTER.initializeReportEngine("My Report");
	}

	/**
	 * Sets the up.
	 *
	 * @param method
	 *            the new up
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(final Method method) throws Exception {
		testEngine.set(new SeleniumTestEngine());
		testApplication.set(testEngine.get().start("http://automationpractice.com/index.php"));
		EXTENTREPORTER.createNewAndAssignTest(method.getName());
		Test test = method.getAnnotation(Test.class);
		EXTENTREPORTER.addCategories(test.groups());
	}

	/**
	 * After test.
	 *
	 * @param result
	 *            the result
	 */
	@AfterMethod(alwaysRun = true)
	public void afterTest(final ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			if (!Objects.isNull(result.getThrowable().getMessage())) {
				if (!result.getThrowable().getMessage().equals("")) {
					EXTENTREPORTER.logFail(result.getThrowable().getMessage());
				}
			} else {
				EXTENTREPORTER.logSkip("Skipped Issue");
			}
			final long timeStamp = new Date().getTime();
			testApplication.get().getTestContext().takeScreenShot("Failure_" + timeStamp + ".png");
			EXTENTREPORTER.takeScreenshot("Failure_" + timeStamp + ".png");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			EXTENTREPORTER.logSkip(result.getThrowable().toString());
		}
		EXTENTREPORTER.endOrCloseTest();
		testEngine.get().stop();
	}

	/**
	 * Tear down.
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		EXTENTREPORTER.closeReportEngine();
	}

	/**
	 * Data provider using CSV.
	 *
	 * @param fileName
	 *            the file name
	 * @return the string[][]
	 */
	@DataProvider(name = "csvDataProvider")
	public String[][] dataProviderUsingCSV(final String fileName) {
		CSVReader reader = null;

		final List<String[]> neededRows = new ArrayList<String[]>();
		try {
			reader = new CSVReader(new FileReader(new File(fileName)));
			// List<String> columnHeaders = Arrays.asList(reader.readNext());
			final List<Integer> neededIndexes = new ArrayList<Integer>();
			final int numNeededCols = neededIndexes.size();
			for (String[] row = reader.readNext(); row != null; row = reader.readNext()) {
				final String[] neededRow = new String[numNeededCols];
				for (int i = 0; i < numNeededCols; ++i) {
					neededRow[i] = row[neededIndexes.get(i)];
				}
				neededRows.add(neededRow);
			}
			reader.close();
		} catch (final UnsupportedEncodingException ex) {
		} catch (final IOException ex) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
				}
			}
		}
		return neededRows.toArray(new String[neededRows.size()][]);
	}

	/**
	 * @param method
	 *            the method
	 * @return the object[][]
	 */
	@SuppressWarnings({ "deprecation", "resource" })
	@DataProvider(name = "ExcelDataProvider", parallel = true)
	public static Object[][] readWorkFlowProgramsExcel() {
		Object[][] obj = null;
		final String fileLoc = System.getProperty("user.dir") + "/src/test/resources/WorkFlow.xlsx";

		final File file = new File(fileLoc);
		FileInputStream inputStream = null;
		Workbook workBook = null;
		try {
			inputStream = new FileInputStream(file);
			workBook = new XSSFWorkbook(inputStream);
			final Sheet sheet = workBook.getSheet("TestDataSheet");
			final int rownum = sheet.getPhysicalNumberOfRows();
			final int colnum = sheet.getRow(0).getLastCellNum();
			obj = new Object[rownum - 1][colnum];

			for (int i = 1; i < rownum; i++) {
				final Row row = sheet.getRow(i);
				for (int j = 0; j < colnum; j++) {
					final Cell cell = row.getCell(j);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						obj[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						obj[i - 1][j] = NumberToTextConverter.toText(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						obj[i - 1][j] = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BLANK:
						obj[i - 1][j] = "";
						break;
					default:
						break;
					}

				}
			}
			return obj;
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				// inputStream.close();//TODO
			}
			if (workBook != null) {
				// workBook.close();
			}
		}
		return obj;
	}

	/**
	 * Take screenshot.
	 *
	 * @param stringpath
	 *            the stringpath
	 */
	public void takeScreenshot(final String stringpath) {
		testApplication.get().getTestContext().takeScreenShot(stringpath);
		EXTENTREPORTER.takeScreenshot(stringpath);
	}
}