SELECT type, passport_id, stake
FROM horse_racing.bet_type INNER JOIN (
	SELECT bet_type_id, passport_id, stake
	FROM horse_racing.bet INNER JOIN (
		SELECT bet_id, passport_id, stake
		FROM horse_racing.user_bet inner join (
			SELECT id, passport_id
			FROM horse_racing.user
			WHERE balance > 1000
		) as thousand_balance_user on user_bet.user_id = thousand_balance_user.id
	) thousand_balance_user_bet on bet.id = thousand_balance_user_bet.bet_id
) as thousand_balance_bet on bet_type.id = thousand_balance_bet.bet_type_id
ORDER BY stake DESC