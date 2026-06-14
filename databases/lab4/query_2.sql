SELECT first_name, second_name, odds, stake, race_time
FROM horse_racing.user INNER JOIN(
	SELECT bet_id, user_id, stake, odds, race_time
    FROM horse_racing.user_bet INNER JOIN (
		SELECT bet.id, race_id, race_time
		FROM horse_racing.bet INNER JOIN (
			SELECT id, date_time AS race_time
			FROM horse_racing.race
            WHERE MONTH(date_time) = 10
		) AS october_race ON bet.race_id = october_race.id
	) AS october_bet ON user_bet.bet_id = october_bet.id
) AS october_user_bet ON october_user_bet.user_id = user.id
ORDER BY race_time, odds;



