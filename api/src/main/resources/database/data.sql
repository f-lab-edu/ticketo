INSERT INTO CATEGORY (id, name, parent_id, created_at, updated_at)
VALUES
 (1, '콘서트', null, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(2, '뮤지컬', null, '2024-11-12 11:33:12', '2024-11-13 08:33:12')
,(3, '발라드', 1, '2024-12-12 11:33:12', '2024-11-14 11:33:12')
,(4, '락/메탈', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(5, '랩/힙합', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(6, '재즈/소울', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(7, '디너쇼', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(8, '포크/트로트', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(9, '내한 공연', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(10,'페스티벌', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(11, '팬클럽/팬미팅', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(12, '인디', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(13, '토크/강연', 1, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(14, '오리지널/내한', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(15, '라이선스', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(16, '창작 뮤지컬', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
,(17, '넌버벌 퍼포먼스', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;
INSERT INTO place (id, name, created_at, updated_at)
VALUES
(1,'고척 스카이 돔', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
     , (2,'올림픽공원 올림픽 홀', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;

INSERT INTO region (id, name, created_at, updated_at)
VALUES
(1,'서울', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
     , (2,'인천', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;


INSERT INTO event (id, type, name, running_time, intermission_time, category_id, description, event_start_datetime, event_end_datetime, reservation_start_datetime, reservation_end_datetime, has_pre_reservation, pre_reservation_start_datetime, pre_reservation_end_datetime,  place_id, region_id, poster_img, thumbnail_img, created_at, updated_at)
VALUES
(1, 'CONCERT','콜드플레이 내한공연', 180, 0, 9, '콜드플레이 내한공연입니다.','2024-11-12 11:33:12', '2024-11-12 11:33:12', '2024-11-14 11:33:12', '2024-11-14 11:33:12',true,  '2024-11-14 11:33:12', '2024-11-14 11:33:12', 1, 1, '//ticketimage.ticketo.com/Play/image/thumbnail/24/24013437_p.gif', '//ticketimage.ticketo.comrz/image/play/events/poster/24/24013437_p_s.jpg', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (2, 'MUSICAL', '킹키부츠 - 서울', 150, 20,15,  '킹키부츠입니다.','2024-11-12 11:33:12', '2024-11-12 11:33:12', '2024-11-14 11:33:12', '2024-11-14 11:33:12', false, '2024-11-14 11:33:12', '2024-11-14 11:33:12',  2, 2, '//ticketimage.ticketo.com/Play/image/thumbnail/24/24013443_p.gif', '//ticketimage.ticketo.comrz/image/play/events/poster/24/240343437_p_s.jpg', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;


INSERT INTO character(id, name, event_id, created_at, updated_at)
VALUES (1, '롤라', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (2, '찰리', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (3, '로렌', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;

INSERT INTO show (id, event_id, show_datetime, reservation_start_datetime, reservation_end_datetime, created_at, updated_at)
VALUES (1, 1, '2024-12-12 18:00:00', '2024-11-12 18:00:00', '2024-12-12 18:00:00', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
, (2, 1, '2024-12-13 18:00:00', '2024-11-12 18:00:00', '2024-12-13 18:00:00', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
, (3, 2, '2024-12-12 18:00:00', '2024-11-12 18:00:00', '2024-12-12 18:00:00', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
, (4, 2, '2024-12-13 18:00:00', '2024-11-12 18:00:00', '2024-12-13 18:00:00', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
, (5, 2, '2024-12-14 18:00:00', '2024-11-12 18:00:00', '2024-12-14 18:00:00', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
, (6, 2, '2024-12-15 18:00:00', '2024-11-12 18:00:00', '2024-12-15 18:00:00', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
;

INSERT INTO show_cast (id, show_id, character_id, name, image, created_at, updated_at)
VALUES ( 1, 3,  1, '강홍석', '//ticketimage.ticketo.com/Play/image/actor/24/24013443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
     , (2, 4, 1,  '서경석', '//ticketimage.ticketo.com/Play/image/actor/24/24013443_p.gif','2024-11-12 11:33:12', '2024-11-14 11:33:12')
     , (3, 5, 1,  '최재림', '//ticketimage.ticketo.com/Play/image/actor/24/24013443_p.gif','2024-11-12 11:33:12', '2024-11-14 11:33:12')
     , (4, 3, 3, '김수하', '//ticketimage.ticketo.com/Play/image/actor/25/2234333443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
     , (5, 4, 3, '김환희', '//ticketimage.ticketo.com/Play/image/actor/25/2234333443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;

INSERT INTO grade (id, name, price, event_id, created_at, updated_at)
VALUES (1, 'VIP석', 170000,  1, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
     , (2, 'R석', 150000, 1, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
     , (3, 'S석', 130000, 1, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
     , (4, 'A석', 110000, 1, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
     , (5, 'VIP석', 140000, 2, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
     , (6, 'R석', 120000, 2, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
     , (7, 'A석', 90000, 2, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
;

INSERT INTO discount (id, name, percent, event_id, created_at, updated_at)
VALUES (1, '재관람 할인(1인 1매)', 30, 2, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
     , (2, '청소년 할인(본인만)', 20, 2, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
;

INSERT INTO zone(id, event_id, name, row_count, created_at, updated_at)
VALUES ( 1, 1, 'A1', 3, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
, ( 2, 1, 'A2', 5, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
, ( 3, 1, 'A3', 3, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
, ( 4, 2, '101', 3, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
, ( 5, 2, '102', 2, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
, ( 6, 2, '103', 3, '2024-11-12 18:00:00', '2024-11-15 11:33:12')
;

INSERT INTO seat(id, show_id, grade_id, zone_id, row_number, seat_number, status, created_at, updated_at)
VALUES
-- A1 (show_id == 1, zone_id == 1)
 (1, 1, 1, 1, 1, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(2, 1, 1, 1, 1, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(3, 1, 1, 1, 1, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(4, 1, 1, 1, 1, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(5, 1, 1, 1, 1, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(6, 1, 1,  1, 2, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(7, 1, 2,  1, 2, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(8, 1, 2,  1, 2, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(9, 1, 2, 1, 2, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(10, 1, 2,  1, 2, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(11, 1, 3,  1, 3, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(12, 1, 3,  1, 3, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(13, 1, 4, 1, 3, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(14, 1, 4, 1, 3, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(15, 1, 4, 1, 3, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')


-- A2 (show_id == 1, zone_id == 2)
,(16, 1, 1,  2, 1, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(17, 1, 1,  2, 1, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(18, 1, 1, 2, 1, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(19, 1, 1, 2, 1, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(20, 1, 1, 2, 1, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(21, 1, 2, 2, 2, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(22, 1, 2, 2, 2, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(23, 1, 2, 2, 2, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(24, 1, 2, 2, 2, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(25, 1, 2, 2, 2, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(26, 1, 2, 2, 3 , 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(27, 1, 2, 2, 3 , 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(28, 1, 2, 2, 3 , 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(29, 1, 2, 2, 3 , 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(30, 1, 2, 2, 3 , 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(31, 1, 3, 2, 4, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(32, 1, 3, 2, 4, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(33, 1, 3, 2, 4, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(34, 1, 3, 2, 4, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(35, 1, 4, 2, 4, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(36, 1, 4, 2, 5, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(37, 1, 4, 2, 5, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(38, 1, 4, 2, 5, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(39, 1, 4, 2, 5, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(40, 1, 4, 2, 5, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')

-- A3 (show_id == 1, zone_id == 3)
,(41,1,  4,  3, 1, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(42,1,  4,  3, 1, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(43,1,  4,  3, 1, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(44,1,  1,  3, 1, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(45,1,  1,  3, 1, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(46,1,  1,  3, 2, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(47,1,  1,  3, 2, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(48,1,  1,  3, 2, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(49,1,  1,  3, 2, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(50,1,  1,  3, 2, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(51,1,  2, 3, 3, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(52,1,  2, 3, 3, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(53,1,  2, 3, 3, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(54,1,  2, 3, 3, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(55,1,  2, 3, 3, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')

-- 101 (show_id == 3, zone_id == 4)
,(56, 3, 5, 4, 1, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(57, 3, 5, 4, 1, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(58, 3, 5, 4, 1, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(59, 3, 5, 4, 1, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(60, 3, 5, 4, 1, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(61, 3, 5, 4, 2, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(62, 3, 6, 4, 2, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(63, 3, 6, 4, 2, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(64, 3, 6, 4, 2, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(65, 3, 6, 4, 2, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(66, 3, 6, 4, 3, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(67, 3, 6, 4, 3, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(68, 3, 6, 4, 3, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(69, 3, 7, 4, 3, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(70, 3, 7, 4, 3, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')

-- 102 (show_id == 3, zone_id == 5)
,(71, 3, 5,  5, 1, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(72, 3, 5,  5, 1, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(73, 3, 5,  5, 1, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(74, 3, 5,  5, 1, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(75, 3, 5,  5, 1, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(76, 3, 5,  5, 2, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(77, 3, 5,  5, 2, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(78, 3, 5,  5, 2, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(79, 3, 6, 5, 2, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(80, 3, 6, 5, 2, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')

-- 103 (show_id == 3, zone_id == 6)
,(81, 3, 5, 6, 1, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(82, 3, 5, 6, 1, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(83, 3, 5, 6, 1, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(84, 3, 6, 6, 1, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(85, 3, 6, 6, 1, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(86, 3, 6, 6, 2, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(87, 3, 6, 6, 2, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(88, 3, 7, 6, 2, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(89, 3, 7, 6, 2, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(90, 3, 7, 6, 2, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(91, 3, 7, 6, 3, 1, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(92, 3, 7, 6, 3, 2, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(93, 3, 7, 6, 3, 3, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(94, 3, 7, 6, 3, 4, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
,(95, 3, 7, 6, 3, 5, 'AVAILABLE', '2024-11-12 18:00:00', '2024-11-15 11:33:12')
