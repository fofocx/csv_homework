# csv_homework
背景：在做数据集成的时候，需要读取第三方的数据并集成到本地的数据库中

第三方数据商为我们提供的是一个时间序列的数据，文件格式是csv，数据格式如下：

*   股票代码    数据项1  数据项2 数据项3
*   000001           0.01        1.02      -1.01  

*   000002          0.03        0.22      0.01

*   00000x           0.11         2.11       0.21



文件名称是 tradingDate.csv，例如 2017-09-19.csv。

请将上述数据商提供的数据写入到本地数据库中，本地数据库的表结构如下:

	CREATE TABLE  time_series_data (
	
	    item_id uuid,
	
	    trading_date date,
	
	    stock_code text,
	
	    item_value double,
	
	    PRIMARY KEY (item_id, trading_date, stock_code)
	
	)

 

请编写使用java/scala 编写读取第三方数据文件到本地的程序，并且附带test case。项目管理请用包管理工具maven或者gradle，其他工具不限。
