--时间差(单位),较小的时间在前,反之时间差为负数
SELECT
MAX(create_time),MIN(create_time),
TIMESTAMPDIFF(SECOND,MIN(create_time),MAX(create_time)),
TIMESTAMPDIFF(MINUTE,MIN(create_time),MAX(create_time)),
TIMESTAMPDIFF(HOUR,MIN(create_time),MAX(create_time)),
TIMESTAMPDIFF(DAY,MIN(create_time),MAX(create_time))
from table_name
where session_id = 'mm'

-- 1、日期函数

-- 1）时间戳：unix_timestamp/from_unixtime

select unix_timestamp();  --查询当前时间的时间戳，返回 1486524284
select unix_timestamp('2017-02-08 11:15:50');  --查询指定时间的时间戳，返回 1486523750 （若转换失败返回0）
select unix_timestamp('20170208 11:00:00','yyyyMMdd HH:mm:ss');  --转换指定格式时间的时间戳
select from_unixtime(1486523750);  --查询指定时间戳的时间，默认格式yyyy-MM-dd HH:mm:ss ，返回 2017-02-08 11:15:50
select from_unixtime(1486523750,'yyyyMMdd');  --查询指定时间戳的时间，转换成指定格式，返回 20170208

-- 2）日期提取年月日时分秒+周：year/month/day/hour/minute/second/weekofyear

select year('2017-02-08 11:15:50'),month('2017-02-08 11:15:50'),day('2017-02-08 11:15:50'),hour('2017-02-08 11:15:50'),minute('2017-02-08 11:15:50'),second('2017-02-08 11:15:50'),weekofyear('2017-02-08 11:15:50');  --查询指定日期的年月日时分秒和周，返回 2017、2、8、11、15、50、6

-- 3）日期时间转日期：to_date

select to_date('2017-02-08 11:15:50'); --查询指定日期时间中的日期。返回 2017-02-08

-- 4）日期比较

--日期比较，结束时间距离开始时间的天数。datediff(string enddate, string startdate)
select datediff('2017-02-02','2017-01-31');--返回 2
--日期增加和减少。date_sub (string startdate, int days)
select date_add('2017-01-31',2); --返回 2017-02-02
select date_sub('2017-02-02',2);  --返回 2017-01-31

-- 5）日期偏移

--自定义日期操作函数（返回带横线的日期）：get_date
select get_date();--返回当前日期,返回  2017-02-09
select get_date(-2);--返回当前日期往前偏移2天的日期 ,返回  2017-02-07
--自定义日期操作函数（返回不带横线的日期）：get_dt_date
select get_dt_date();--获取当前日期，返回 20170209
select get_dt_date(get_date(-2));--获取当前日期偏移，转为不带横杆的格式
select get_dt_date('2017-02-02',-2);--20170131