SELECT D.name AS Department, E.name AS Employee, E.salary AS Salary
FROM Employee E
         JOIN Department D ON E.departmentId = D.id
WHERE (E.salary, E.departmentId) IN (
    SELECT MAX(salary) AS salary, departmentId
    FROM Employee
    GROUP BY departmentId
);
