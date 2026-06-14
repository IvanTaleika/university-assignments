SELECT 
	concat_ws(' ', first_name, second_name) AS user_name,
    IFNULL(sum(stake), 0) AS money_lost
FROM user 
LEFT OUTER JOIN (
	SELECT stake, user_id
    FROM user_bet
	WHERE state = 'loss'
) as loss_bets ON user.id = loss_bets.user_id
GROUP BY user.id
UNION
SELECT 
	'Total' AS user_nmae, 
    sum(stake) AS money_lost
FROM user_bet 
WHERE state = 'loss';
