INSERT INTO `race` (`id`, `name`, `location`, `distance`, `date_time`, `recorded`) VALUES 
	(1,'Connellyberg','Vermont',2000,'2019-09-21 03:50:00',1),
    (2,'Brauntown','Nebraska',3000,'2019-10-20 12:30:00',0),
    (3,'New Lavonnemouth','Louisiana',1000,'2019-09-01 12:40:00',1);

INSERT INTO `horse` (`id`, `name`, `age`, `sex`) VALUES 
	(1,'Boris Ferry',10,'male'),
    (2,'Lou Barton',12,'female'),
    (3,'Maybelle Goodwin IV',8,'gelding'),
    (4,'Amely Price',6,'gelding'),
    (5,'Dr. Nicholas Smitham',6,'female'); 
    
INSERT INTO `form` (`id`, `color`, `image_path`) VALUES 
	(1,'blue-yellow','/images/form/blue_yellow.png'),
    (2,'blue-white','/images/form/blue_white.png'),
    (3,'purple-black','/images/form/purple_black.png'),
    (4,'red-white','/images/form/red_white.png'),
    (5,'green-white','/images/form/green_white.png'),
    (6,'blue_red_dotted','/images/form/blue_red_dotted.png'),
    (7,'green_blue_strip.png','/images/form/green_blue_strip.png'); 

INSERT INTO `race_m2m_horse` (`race_id`, `horse_id`, `form_id`, `number`, `place`) VALUES 
	(1,1,1,1,1),
	(1,2,2,3,2),
    (1,3,5,2,3),
    (2,3,5,3,NULL),
    (2,5,4,2,NULL),
    (2,1,3,1,NULL),
    (3,4,1,1,4),
    (3,5,2,2,5),
    (3,1,4,3,2),
    (3,3,5,4,1),
    (3,2,3,5,3); 
    
INSERT INTO `bet_type` (`id`, `type`) VALUES 
	(1,'Win'),
    (2,'Forecast'),
    (3,'Reversed Forecast'),
    (4,'Top'),
    (5,'Exact place'),
    (6,'Outsider');

INSERT INTO `bet` (`id`, `race_id`, `bet_type_id`, `odds`) VALUES
	(1,3,1,2.50),
    (2,3,2,3.25),
    (3,3,1,3.60),
    (4,3,3,5.20),
    (5,3,5,2.50),
    (6,2,1,2.50),
    (7,2,6,3.25),
    (8,2,1,3.60); 

INSERT INTO `bet_description` (`bet_id`, `condition`, `horse_id`) VALUES 
	(2,1,4), 
    (2,2,5),
    (1,1,4),
    (3,1,1), 
    (4,5,2),
    (4,2,3), 
    (5,3,1), 
    (6,1,3),
	(7,3,1),
	(8,1,1);

INSERT INTO `user` (`id`, `email`, `first_name`, `second_name`, `role`, `balance`, `birth_date`, `passport_series`, `passport_id`, `is_deleted`, `password`, `salt`) VALUES
	(1,'yana32@example.net','Yanina','Lavrinovich','client',20001.00,'1999-07-30','MC','1234567',0,'1f5be7b366b21db886798f51795a2d5ed4ba1a9c25c06de829eddbf31a7aee8335ec6344fd93030cfc9095e989c1b67033459a2c79a885b35178b45167ea89a6','0d0c3bc62756e74502e08dd779e41b212932379e02f4b659194e9ecb4f43c11c16b38c3d08892065aff70f2120fc18c1f248a3d6eb9d7f8a283227a80475c3b1'),
    (2,'angeline85@example.org','Jamison','Sanford','client',1090.60,'1979-12-12','MC','1811383',0,'ae205d4777a68aa976414216a6d640977cad676839b8e78be76381b920f2aa278c7187a9e1d8e5293e2941b64553084159fd6b8ba475362f2b7c196ab5427759','c23c5f4e282e7e29b262ddf4498225da47ddff5e3462201ecdf5c987ca16743c77626f7e0dbef973678924623e94f8a3200da6adf22692d37e29c9bd94221370'),
    (3,'reymundo00@example.org','Clementine','Conroy','bookmaker',0.00,'1997-03-27','MC','1104658',0,'356bc968418ee206f7c503c2fd10ede6d38a52d58309fbd172a4fedb93532fbef4744bcac9dced6fac02dc1882df433aa6a23a556145e71da3b4938bc69bdeba','a83a23a92e6165436fe1880a456b0ffd292c33e7bc755f0c10a3d48e5d32d87c28e1d8d0df6fa1269dde8641ac907477cc69a98e5eb2fad1f6a3f7ba45b0d2fd'),
    (4,'iemard@example.net','Janice','Fisher','admin',0.00,'1984-11-09','MC','5601606',0,'a7d115af9ccf13eb3eb54e06aeee7190436eb3e479f07588db35fd461e4113653b4add288574b6fd25b5b8b9d331006d598149000132ab7ebab5771310001b83','e0a5ddaeffe982aaba3a393f1a62495f8630363ba133a2f0a565d892d71c73c256547c1020117ebe06967c55ab8bc73d844e40ff47e103d1bb1ae2e88169a3d2');
 
INSERT INTO `user_bet` (`id`, `bet_id`, `user_id`, `stake`, `odds`, `state`) VALUES 
	(1,1,1,140.00,2.50,'loss'),
	(2,3,1,200.50,3.20,'won'),
	(3,6,2,300.00,2.80,'placed'),
	(4,5,2,320.00,2.50,'won'),
	(5,7,1,350.50,6.40,'placed');
