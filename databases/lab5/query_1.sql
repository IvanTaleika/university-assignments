SELECT name, place AS median_place, races_count
FROM (
	SELECT 
		count(*) OVER (PARTITION BY horse.id) AS races_count, 
		row_number() OVER (PARTITION BY horse.id ORDER BY place) AS race_idx,
		name,
		place
	FROM race_m2m_horse INNER JOIN horse ON race_m2m_horse.horse_id = horse.id
	WHERE place IS NOT NULL and name = 'Lou Barton'
) AS horse_races
WHERE race_idx = ceiling(races_count / 2)
