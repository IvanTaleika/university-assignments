SELECT location AS race_location, name AS race_name, n_race_bets, n_distinct_users_with_bets
FROM (
	SELECT race.id, race.name, race.location, count(bet.id) AS n_race_bets
	FROM race
	LEFT OUTER JOIN bet ON race.id = bet.race_id
    GROUP BY race.id
) AS race_with_bets INNER JOIN (
	SELECT count(DISTINCT user.id) AS n_distinct_users_with_bets, race.id AS race_id
	FROM user
	INNER JOIN user_bet ON user_bet.user_id = user.id
	INNER JOIN bet ON user_bet.bet_id = bet.id
	RIGHT OUTER JOIN race ON race.id = bet.race_id
	GROUP BY race.id
) AS distinct_users_with_bets ON distinct_users_with_bets.race_id = race_with_bets.id
