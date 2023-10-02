# https://leetcode.com/problems/employees-earning-more-than-their-managers/description/
SELECT a.NAME AS Employee
FROM Employee AS a JOIN Employee AS b
ON a.ManagerId = b.Id AND a.Salary > b.Salary;