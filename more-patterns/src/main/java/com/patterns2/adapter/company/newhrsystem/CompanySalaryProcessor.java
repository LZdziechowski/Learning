package com.patterns2.adapter.company.newhrsystem;

import java.math.BigDecimal;
import java.util.List;

public class CompanySalaryProcessor implements SalaryProcessor {

    @Override
    public BigDecimal calculateSalaries(List<Employee> employeeList) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Employee employee : employeeList) {
            System.out.println(employee);
            sum = sum.add(employee.getBaseSalary());
        }
        return sum;
    }
}
