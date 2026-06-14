CREATE TABLE IF NOT EXISTS hopeless_horse (
	horse_id INT UNSIGNED NOT NULL PRIMARY KEY,
	best_place INT UNSIGNED NOT NULL,
	average_place DECIMAL(4, 2) NOT NULL,
	worst_place INT UNSIGNED NOT NULL,
	races_count INT UNSIGNED NOT NULL
);

DROP TABLE IF EXISTS new_hopeless_horse;
CREATE TEMPORARY TABLE new_hopeless_horse 
AS
	SELECT 
		horse_id,
		MIN(place) as best_place,
		AVG(place) as average_place,
		MAX(place) as worst_place,
		count(*) as races_count
	FROM race_m2m_horse
	WHERE place IS NOT NULL
	GROUP BY horse_id
	ORDER BY average_place DESC
	LIMIT 1;

SET @worst_horse := (SELECT horse_id FROM new_hopeless_horse);

START TRANSACTION;
INSERT INTO hopeless_horse SELECT * FROM new_hopeless_horse;

DELETE FROM race_m2m_horse WHERE horse_id = @worst_horse;
DELETE FROM bet WHERE id IN (
	SELECT DISTINCT bet_id
    FROM bet_description
    WHERE horse_id = @worst_horse
);
DELETE FROM horse WHERE id = @worst_horse;
COMMIT;

DROP TABLE IF EXISTS new_hopeless_horse;

