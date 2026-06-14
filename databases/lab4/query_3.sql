SELECT name 
FROM horse_racing.horse INNER JOIN (
	SELECT horse_id
	FROM horse_racing.bet_description INNER JOIN ( 
		SELECT id
		FROM horse_racing.bet INNER JOIN (
			SELECT bet_id 
			FROM horse_racing.user_bet inner join (
				SELECT id 
				FROM horse_racing.user
				WHERE second_name LIKE '%ford'
			) as ford_like_user on user_bet.user_id = ford_like_user.id
		) ford_like_user_bet on bet.id = ford_like_user_bet.bet_id
	) as ford_like_bet on bet_description.bet_id = ford_like_bet.id
) as ford_like_bet_description on horse.id = ford_like_bet_description.horse_id
