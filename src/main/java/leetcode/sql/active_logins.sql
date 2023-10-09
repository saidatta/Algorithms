-- First, we'll rank login dates for each user.
-- This will help identify consecutive dates.
WITH RankedLogins AS (
    SELECT  id,
            login_date,
            DENSE_RANK() OVER(PARTITION BY id ORDER BY login_date) as consecutive_rank
    FROM Logins
),

-- Next, we derive a unique grouping for each set of consecutive dates.
-- We do this by subtracting the rank from the date.
-- Consecutive dates will result in the same difference (GroupIdentifier).
     ConsecutiveGroups AS (
         SELECT  id,
                 login_date,
                 consecutive_rank,
                 DATE_ADD(login_date, INTERVAL -consecutive_rank DAY) as GroupIdentifier
         FROM RankedLogins
     ),

-- Here, we identify the start and end date of each group of consecutive logins.
-- We filter out groups that don't span at least 5 days.
     ValidGroups AS (
         SELECT  id,
                 MIN(login_date) as StartDate,
                 MAX(login_date) as EndDate,
                 DATEDIFF(MAX(login_date), MIN(login_date)) as Duration
         FROM ConsecutiveGroups
         GROUP BY id, GroupIdentifier
         HAVING Duration >= 4
     )

-- Finally, we join with the Accounts table to get the names.
-- We use DISTINCT to ensure each active user is reported once.
SELECT DISTINCT v.id, name
FROM ValidGroups v
         JOIN Accounts acc ON v.id = acc.id
ORDER BY v.id;
--
-- This is a problem I keep running into as a professional data scientist. There are a ton of inefficient ways to do it,
-- but I found these blog posts to be super helpful:
--
-- https://stackoverflow.com/questions/26117179/sql-count-consecutive-days
-- https://mattboegner.com/improve-your-sql-skills-master-the-gaps-islands-problem/
-- This is what is known as a 'Gap & Islands' Problem. It's fairly common to run into it, and knowing how to do it opens
-- up the door to many important analysis: calculating churn, retention, ressurrected users, etc.
-- https://leetcode.com/problems/active-users/solutions/644070/100-faster-than-submissions-window-functions-documentation-linked/