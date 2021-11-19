package com.bridgelabz.JDBC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOError;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.bridgelabz.jdbc.EmployeePayroll;
import com.bridgelabz.jdbc.EmployeePayrollService;
import com.bridgelabz.jdbc.EmployeePayrollService.IOService;

public class EmployeePayrollServiceTest {

	@Test//done
	public void given3EmployeeDetailsWhenWrittenToTheFileShouldMatchTheEntries() {
		EmployeePayroll[] arrayOfEmpData = { new EmployeePayroll(1, "Sarah", 5000),
				new EmployeePayroll(2, "Blaire", 30000), new EmployeePayroll(3, "Teressa", 40000) };

		EmployeePayrollService employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmpData));
		employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
		employeePayrollService.printData(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		assertEquals(3, entries);
	}

	@Test //done
	public void givenFileWhenReadingShouldMatchTheEmployeeCount() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		long entries = employeePayrollService.readData(IOService.FILE_IO);
		assertEquals(3, entries);
	}

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchTheCount() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayroll> payrollList = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		assertEquals(6, payrollList.size());
	}

	@Test
	public void givenNewSalary_WhenUpdated_ShouldSyncWithDB() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayroll> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("Teressa", 3000000.0);
		boolean result = employeePayrollService.checkPayrollObjectDataIsSyncWithDB("Teressa");
		assertTrue(result);
	}

	@Test
	public void givenDateRange_whenFetched_shouldReturnEmployeeWithinThatRange() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		LocalDate startDate = LocalDate.of(2018, 1, 1);
		LocalDate endDate = LocalDate.now();
		List<EmployeePayroll> employeePayrollData = employeePayrollService
				.getEmployeePayrollDataFromDateRange(IOService.DB_IO, startDate, endDate);
		assertEquals(6, employeePayrollData.size());
	}

	@Test
	public void givenPayrollData_whenAverageByGenderRetrieved_shouldReturnRightValue() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Map<String, Double> avgSalaryByGender = employeePayrollService.getAvgSalaryByGender(IOService.DB_IO);
		assertTrue(avgSalaryByGender.get("M").equals(2000000.00) && avgSalaryByGender.get("F").equals(3000000.00));
	}
}
