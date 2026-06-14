SELECT  type, count(*) as loss_count
FROM user_bet 
INNER JOIN bet ON bet.id = user_bet.id
INNER JOIN bet_type ON bet_type_id = bet_type.id
WHERE state = 'loss'
GROUP BY type
ORDER BY loss_count
LIMIT 1
