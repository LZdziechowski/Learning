package com.patterns2.adapter.company;

import com.patterns2.adapter.company.newhrsystem.CompanySalaryProcessor;
import com.patterns2.adapter.company.newhrsystem.Employee;
import com.patterns2.adapter.company.newhrsystem.SalaryProcessor;

import java.math.BigDecimal;
import java.util.List;

public class SalaryAdaptee implements SalaryProcessor {

    @Override
    public BigDecimal calculateSalaries(List<Employee> employeeList) {
        CompanySalaryProcessor theProcessor = new CompanySalaryProcessor();
        return theProcessor.calculateSalaries(employeeList);
    }
}
