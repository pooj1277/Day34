package com.bridgelabz.jdbc;

import java.time.LocalDate;

public class EmployeePayroll {

	int id;
	String name;
	double salary;
	LocalDate date;

	public EmployeePayroll(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public EmployeePayroll(int id, String name, double salary, LocalDate date) {
		this(id, name, salary);
		this.date = date;
	}

	public String toString() {
		return "Id : " + id + ", Name : " + name + ", Salary : " + salary + ", Joining Date : " + date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePayroll other = (EmployeePayroll) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
	}

}
