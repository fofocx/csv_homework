CREATE TABLE `time_series_data` (
  `item_id` varchar(32) NOT NULL COMMENT 'uuid',
  `trading_date` date NOT NULL COMMENT 'date',
  `stock_code` varchar(10) NOT NULL COMMENT 'text',
  `item_value` double(3,2) NOT NULL COMMENT 'double',
  PRIMARY KEY (`item_id`,`trading_date`,`stock_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

