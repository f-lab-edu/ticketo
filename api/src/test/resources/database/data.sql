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
, (2,'MUSICAL', '킹키부츠 - 서울', 150, 20,15,  '킹키부츠입니다.','2024-11-12 11:33:12', '2024-11-12 11:33:12', '2024-11-14 11:33:12', '2024-11-14 11:33:12', false, '2024-11-14 11:33:12', '2024-11-14 11:33:12',  2, 2, '//ticketimage.ticketo.com/Play/image/thumbnail/24/24013443_p.gif', '//ticketimage.ticketo.comrz/image/play/events/poster/24/240343437_p_s.jpg', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;

-- INSERT INTO actor (id, name, image, created_at, updated_at)
-- VALUES (1, '강홍석', '//ticketimage.ticketo.com/Play/image/actor/24/24013443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
-- , (2, '서경석', '//ticketimage.ticketo.com/Play/image/actor/25/223433443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
-- , (3, '최재림', '//ticketimage.ticketo.com/Play/image/actor/25/223433443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
-- , (4, '김수하', '//ticketimage.ticketo.com/Play/image/actor/25/2234333443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
-- , (5, '김환희', '//ticketimage.ticketo.com/Play/image/actor/25/2234333443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
-- ;

INSERT INTO character(id, name, event_id, created_at, updated_at)
VALUES (1, '롤라', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (2, '찰리', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (3, '로렌', 2, '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;

INSERT INTO event_cast (id, character_id, name, image, created_at, updated_at)
VALUES ( 1,  1, '강홍석', '//ticketimage.ticketo.com/Play/image/actor/24/24013443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (2, 1,  '서경석', '//ticketimage.ticketo.com/Play/image/actor/24/24013443_p.gif','2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (3, 1,  '최재림', '//ticketimage.ticketo.com/Play/image/actor/24/24013443_p.gif','2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (4, 3, '김수하', '//ticketimage.ticketo.com/Play/image/actor/25/2234333443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
, (5, 3, '김환희', '//ticketimage.ticketo.com/Play/image/actor/25/2234333443_p.gif', '2024-11-12 11:33:12', '2024-11-14 11:33:12')
;

INSERT INTO show (id, event_id, show_datetime, reservation_start_datetime, reservation_end_datetime, created_at, updated_at)
VALUES (1, 1, '2024-12-12 18:00:00', '2024-11-12 18:00:00', '2024-11-15 11:33:12', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
     , (2, 1, '2024-12-13 18:00:00', '2024-11-12 18:00:00', '2024-11-15 11:33:12', '2024-11-12 11:33:12', '2024-11-12 11:33:12')
;
