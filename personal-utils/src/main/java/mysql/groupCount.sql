
--[用户数,user_id不为空按user_id统计,为空按session_id统计]
--用户数，按天去重user_id;后统计多天用户数量时不去重
SELECT COUNT(DISTINCT user_id) num,DATE_FORMAT(create_time,'%m-%d-%Y') from table_name
where create_time <'2018-04-08' and user_id <> '' GROUP BY DATE_FORMAT(create_time,'%m-%d-%Y')

--用户数，按天去重user_id;后统计多天用户数量时不去重
SELECT COUNT(DISTINCT session_id) num,DATE_FORMAT(create_time,'%Y-%m-%d') days from table_name
where create_time <'2018-04-08' and user_id = '' AND session_id <> '' GROUP BY DATE_FORMAT(create_time,'%m-%d-%Y'),session_id

--会话数(一次会话中,6次问答,算作一次会话)
SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS item_day,COUNT(DISTINCT session_id) AS session_num from table_name
WHERE session_id <> ''
and create_time >= '2018-03-19' and create_time < '2018-04-19'
GROUP BY DATE_FORMAT(create_time,'%m-%d-%Y')

--用户问题数
SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS item_day,COUNT(question_id) AS session_num from table_name
GROUP BY DATE_FORMAT(create_time,'%m-%d-%Y')

--会话时长(每一天,每一次会话[多次问答,多次记录],取每次会话时间差)
SELECT  DATE_FORMAT(create_time,'%m-%d-%Y') as item_day,session_id,TIMESTAMPDIFF(SECOND,MIN(create_time),MAX(create_time)) AS session_time
from table_name
WHERE	session_id <> ''
GROUP BY DATE_FORMAT(create_time,'%m-%d-%Y'),session_id