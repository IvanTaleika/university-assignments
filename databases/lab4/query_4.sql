SELECT name, sex, age
FROM horse_racing.horse LEFT OUTER JOIN (
	SELECT horse_id
    FROM horse_racing.race_m2m_horse
) as horse_with_race on horse.id = horse_with_race.horse_id
WHERE horse_id IS NULL
