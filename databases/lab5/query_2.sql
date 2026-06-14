SELECT first_name, second_name, balance
FROM user
INNER JOIN user_bet ON user_id = user.id
INNER JOIN bet ON bet_id = bet.id
GROUP BY user.id
HAVING count(DISTINCT bet_type_id) = 1
