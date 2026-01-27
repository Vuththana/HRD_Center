enum JobType {
    FULL_TIME,
    PART_TIME
}

void main() {
    // Homework 1. Electricity usage monthly total cost
    Scanner scanner = new Scanner(System.in);
    float low = 1500f;
    float high = 2000f;


    System.out.print("Enter your monthly electricity usage(kWh): ");
    String electricityUsage = scanner.nextLine();

    float convertedElectricityUsage = electricityUsage.matches("^\\d+(\\.\\d+)?$") ? Float.parseFloat(electricityUsage) : 0;
    float totalCost = convertedElectricityUsage <= 50 ? convertedElectricityUsage * low : (convertedElectricityUsage - 50) * high + (50 * low);

    System.out.printf("Your total electricity cost per month: %.2f\n", totalCost);

    // Homework 2. Employee Information
    System.out.println("=".repeat(5) + " Input Information Employee Registration " + "=".repeat(5));

    // Employee ID
    System.out.print("Employee ID: ");
    String employeeId = scanner.nextLine();

    // Employee Name
    System.out.print("Employee Name: ");
    String employeeName = scanner.nextLine();

    // Employee Age
    System.out.print("Employee Age: ");
    String employeeAge = scanner.nextLine();

    // Employee Salary
    System.out.print("Employee Salary: ");
    String employeeSalary = scanner.nextLine();

    // Employee Type
    System.out.print("Enter Employee Type (FULL_TIME, PART_TIME): ");
    String employeeType = scanner.nextLine();

    // Employee Email
    System.out.print("Employee Email: ");
    String employeeEmail = scanner.nextLine();

    /* Validation */

    // Employee ID
    String id = employeeId.matches("^[1-9]+[0-9]{2,}$") ? // checks if they input 3 digits not 2 digits
            employeeId : "INVALID_ID";

    // Employee Name
    String name = employeeName.matches("^\\p{L}{2,30}(?:\\s\\p{L}{2,30}){0,2}$") ? // Checks for employee that has first name middle name and last name
            employeeName : "INVALID_NAME";

    // Employee Age
    int age = employeeAge.matches("^[0-9]{2,}") ? // Regex to matches the string if we enter numbers or not
            Integer.parseInt(employeeAge) >= 18 ? // Check if the employee age is up to 18
            Integer.parseInt(employeeAge) <= 60 ? // Check if the employee age is less than 60
                    Integer.parseInt(employeeAge) : 0 : 0 : 0;

    // Employee Salary
    float salary = employeeSalary.matches("^[1-9]\\d+$") ? // Checks the input to match with numbers only
            Float.parseFloat(employeeSalary) : 0f;

    // Employee Final salary
    Float finalSalary = employeeType.equalsIgnoreCase(JobType.FULL_TIME.toString()) ?
            Float.valueOf(salary * 1f) // We need the method valueOf() or else NullExceptionError will occur
            : employeeType.equalsIgnoreCase(JobType.PART_TIME.toString()) ? salary * 0.6f : null;



    String email = employeeEmail.matches("^([a-zA-Z0-9]+)([.-_][a-zA-Z0-9]+)*@[a-z]+(\\.[a-z]{2,6})$") ? // Checks if the input is an email or not
            employeeEmail : "INVALID_EMAIL";

    // Summary
    System.out.println("=".repeat(5) + " Employee Registration Summary " + "=".repeat(5));
    System.out.println("Employee ID: " + id);
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Original Salary: " + salary);
    System.out.println("Final Salary(rounded): " + finalSalary);
    System.out.println("Employee Email: " + email);

}