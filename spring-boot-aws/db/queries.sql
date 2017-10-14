INSERT INTO web_requests (request_timestamp, request_data, instance_id)
VALUES (now(), 'this is data', 'instance-id');


SELECT *
FROM web_requests
LIMIT 5;

SELECT *
FROM web_requests wr
WHERE wr.request_timestamp BETWEEN '2017-07-12 14:40:00' AND '2017-07-12 23:59:59';

SELECT max(request_timestamp)
FROM web_requests;

SELECT DISTINCT instance_id
FROM web_requests;

SELECT
  count(*) AS count,
  instance_id
FROM web_requests
GROUP BY instance_id
ORDER BY count DESC;

SELECT count(*)
FROM web_requests;

DELETE FROM web_requests
