USE `horse_racing`;

DROP TABLE IF EXISTS `race`;
CREATE TABLE `race` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id заезда',
  `name` varchar(64) NOT NULL COMMENT 'Название заезда',
  `location` varchar(64) NOT NULL COMMENT 'Место проведения заезда',
  `distance` smallint unsigned NOT NULL COMMENT 'Расстояние заезда в метрах',
  `date_time` datetime NOT NULL,
  `recorded` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=0 COMMENT='Содержит текущие и будущие заезды';

DROP TABLE IF EXISTS `horse`;
CREATE TABLE `horse` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id лошади',
  `name` varchar(64) NOT NULL COMMENT 'Имя лошади',
  `age` tinyint unsigned NOT NULL COMMENT 'Возраст лошади',
  `sex` enum('male','female','gelding') NOT NULL COMMENT 'Пол лошади',
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=0 COMMENT='Содержит лошадей, учавствующих в скачках';

DROP TABLE IF EXISTS `form`;
CREATE TABLE `form` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id цвета',
  `color` varchar(128) NOT NULL COMMENT 'Цвет формы',
  `image_path` varchar(128) DEFAULT NULL COMMENT 'Картинка с изображением формы',
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=0 COMMENT='Содержит доступные цвета амуниции лошади во время забега';

DROP TABLE IF EXISTS `race_m2m_horse`;
CREATE TABLE `race_m2m_horse` (
  `race_id` int unsigned NOT NULL COMMENT 'Id забега',
  `horse_id` int unsigned NOT NULL COMMENT 'Id учавствующей лошади',
  `form_id` smallint unsigned NOT NULL COMMENT 'Id стартовой формы',
  `number` tinyint unsigned NOT NULL COMMENT 'Стартовый номер лошади',
  `place` tinyint DEFAULT NULL,
  PRIMARY KEY (`race_id`,`horse_id`),
  UNIQUE KEY `unique_race_color_idx` (`race_id`,`form_id`),
  UNIQUE KEY `unique_race_number_idx` (`race_id`, `number`),
  KEY `horse_id_idx` (`horse_id`),
  KEY `color_id_idx` (`form_id`),
  CONSTRAINT `FK_RaceM2MHorse_Color` FOREIGN KEY (`form_id`) REFERENCES `form` (`id`),
  CONSTRAINT `FK_RaceM2MHorse_Horse` FOREIGN KEY (`horse_id`) REFERENCES `horse` (`id`),
  CONSTRAINT `FK_RaceM2MHorse_Race` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`) ON DELETE CASCADE
) COMMENT='Содержит предстоящие и текущие забеги и их участников';

DROP TABLE IF EXISTS `bet_type`;
CREATE TABLE `bet_type` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id типа ставки.',
  `type` varchar(64) NOT NULL COMMENT 'Название типа ставки',
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=0 COMMENT='Содержит все доступные типы ставок на скачки.';

DROP TABLE IF EXISTS `bet`;
CREATE TABLE `bet` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id ставки',
  `race_id` int unsigned NOT NULL COMMENT 'Id заезда',
  `bet_type_id` tinyint unsigned NOT NULL COMMENT 'Id типа ставки',
  `odds` decimal(4,2) DEFAULT NULL COMMENT 'Коэффициет ставки. Значение NULL - SP ставка (коэффициет становится известен только перед началом забега, после принятия всех ставок)',
  PRIMARY KEY (`id`),
  KEY `race_id_idx` (`race_id`),
  KEY `FK_Bet_BetType_idx` (`bet_type_id`),
  CONSTRAINT `FK_Bet_BetType` FOREIGN KEY (`bet_type_id`) REFERENCES `bet_type` (`id`),
  CONSTRAINT `FK_Bet_Race` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`) ON DELETE CASCADE
) AUTO_INCREMENT=0 COMMENT='Содержит заезды и назначенные для них ставки';

DROP TABLE IF EXISTS `bet_description`;
CREATE TABLE `bet_description` (
  `bet_id` bigint unsigned NOT NULL COMMENT 'Id ставки.',
  `condition` int unsigned NOT NULL COMMENT 'Необходимое условие выигрыша',
  `horse_id` int unsigned NOT NULL COMMENT 'Id лошади, на которую сделана ставка',
  PRIMARY KEY (`bet_id`,`condition`,`horse_id`),
  KEY `FK_BetDescription_Bet_idx` (`horse_id`),
  CONSTRAINT `FK_BetDescription_Bet` FOREIGN KEY (`bet_id`) REFERENCES `bet` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_BetDescription_Horse` FOREIGN KEY (`horse_id`) REFERENCES `horse` (`id`)
) COMMENT='Содержит описание ставок.';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id пользователя',
  `email` varchar(64) NOT NULL COMMENT 'Email пользователя',
  `first_name` varchar(64) NOT NULL COMMENT 'Имя пользователя',
  `second_name` varchar(64) NOT NULL COMMENT 'Фамилия пользователя',
  `role` enum('client','bookmaker','admin') DEFAULT 'client' COMMENT 'Роль пользователя',
  `balance` decimal(13,2) DEFAULT '0.00' COMMENT 'Баланс пользователя',
  `birth_date` date NOT NULL COMMENT 'Дата рождения пользователя',
  `passport_series` char(2) NOT NULL COMMENT 'Серия паспорта',
  `passport_id` char(7) NOT NULL COMMENT 'Норер паспорта',
  `is_deleted` tinyint DEFAULT '0' COMMENT 'Удалён ли пользователь',
  `password` char(128) NOT NULL COMMENT 'Password hash value',
  `salt` char(128) NOT NULL COMMENT 'Password salt',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) AUTO_INCREMENT=0 COMMENT='Содержит зарегестророванных пользователей';

DROP TABLE IF EXISTS `user_bet`;
CREATE TABLE `user_bet` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `bet_id` bigint unsigned DEFAULT NULL COMMENT 'Id пользовательской ставки',
  `user_id` int unsigned NOT NULL COMMENT 'Id пользователя, сделавшего ставку.',
  `stake` decimal(13,2) NOT NULL COMMENT 'Поставленные деньги',
  `odds` decimal(4,2) NOT NULL COMMENT 'Коэффициет ставки. NULL - значение ещё не известно (ставка определяется перед началом забега).',
  `state` enum('placed','won','loss','return') DEFAULT 'placed',
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `FK_UserBett_Bet_idx` (`bet_id`),
  CONSTRAINT `FK_UserBett_Bet` FOREIGN KEY (`bet_id`) REFERENCES `bet` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK_UserBett_User` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) AUTO_INCREMENT=0 COMMENT='Содержит ставки пользователя (возможна экспресс ставка)';

