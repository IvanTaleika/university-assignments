SELECT first_name, second_name, concat(substring(first_name, 1, 1), substring(second_name, 1, 1)) as initials, passport_id 
FROM horse_racing.user 
WHERE passport_id LIKE '%4%' AND passport_id LIKE '%1%';
