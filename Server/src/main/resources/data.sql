-- 미션 카테고리 생성
INSERT INTO mission_categories (category_id, name) VALUES (1, '환경보호');
INSERT INTO mission_categories (category_id, name) VALUES (2, '건강');
INSERT INTO mission_categories (category_id, name) VALUES (3, '교육');
INSERT INTO mission_categories (category_id, name) VALUES (4, '사회공헌');
INSERT INTO mission_categories (category_id, name) VALUES (5, '자기계발');

-- 25개의 미션 생성
INSERT INTO mission (title, content, image_url, category_id) VALUES
                                                                             ('일회용품 줄이기', '일주일 동안 일회용품 사용을 줄이고 경험을 공유해주세요.', 'https://example.com/mission-images/reduce-disposables.jpg', 1),
                                                                             ( '매일 만보 걷기', '일주일 동안 매일 만보씩 걷고 기록을 남겨주세요.', 'https://example.com/mission-images/daily-walk.jpg', 2),
                                                                             ( '새로운 언어 배우기', '일주일 동안 새로운 언어의 기초 5문장을 배워보세요.', 'https://example.com/mission-images/learn-language.jpg', 3),
                                                                             ( '지역 봉사활동 참여', '이번 주 지역 봉사활동에 참여하고 경험을 나눠주세요.', 'https://example.com/mission-images/community-service.jpg', 4),
                                                                             ( '독서 습관 기르기', '일주일 동안 매일 30분씩 책을 읽고 느낀 점을 기록해보세요.', 'https://example.com/mission-images/reading-habit.jpg', 5),
                                                                             ( '재활용 분리수거 실천', '일주일 동안 재활용 분리수거를 철저히 하고 결과를 공유해주세요.', 'https://example.com/mission-images/recycling.jpg', 1),
                                                                             ( '명상 습관 들이기', '매일 10분씩 명상을 하고 마음의 변화를 기록해보세요.', 'https://example.com/mission-images/meditation.jpg', 2),
                                                                             ( '온라인 강의 수강하기', '관심 있는 분야의 온라인 강의를 수강하고 배운 점을 나눠주세요.', 'https://example.com/mission-images/online-course.jpg', 3),
                                                                             ( '헌혈 참여하기', '헌혈에 참여하고 그 경험을 공유해주세요.', 'https://example.com/mission-images/blood-donation.jpg', 4),
                                                                             ( '새로운 취미 도전', '새로운 취미를 시작하고 일주일간의 진행 상황을 기록해보세요.', 'https://example.com/mission-images/new-hobby.jpg', 5),
                                                                             ( '친환경 제품 사용하기', '일상에서 친환경 제품을 사용하고 그 경험을 나눠주세요.', 'https://example.com/mission-images/eco-friendly-products.jpg', 1),
                                                                             ( '건강한 식단 유지하기', '일주일 동안 균형 잡힌 식단을 유지하고 메뉴를 공유해주세요.', 'https://example.com/mission-images/healthy-diet.jpg', 2),
                                                                             ( '무료 강연 참석하기', '관심 분야의 무료 강연에 참석하고 배운 점을 정리해보세요.', 'https://example.com/mission-images/free-lecture.jpg', 3),
                                                                             ( '노인복지관 방문하기', '지역 노인복지관을 방문하여 봉사활동을 하고 느낀 점을 나눠주세요.', 'https://example.com/mission-images/elderly-care.jpg', 4),
                                                                             ( '스트레칭 습관 들이기', '매일 아침 10분씩 스트레칭을 하고 건강 변화를 기록해보세요.', 'https://example.com/mission-images/stretching.jpg', 5),
                                                                             ( '에너지 절약하기', '일주일 동안 가정에서 에너지 절약을 실천하고 방법을 공유해주세요.', 'https://example.com/mission-images/energy-saving.jpg', 1),
                                                                             ( '요가 배우기', '일주일 동안 매일 15분씩 요가를 배우고 느낀 점을 기록해보세요.', 'https://example.com/mission-images/yoga.jpg', 2),
                                                                             ( '외국어로 일기쓰기', '매일 외국어로 짧은 일기를 작성하고 발전 과정을 공유해주세요.', 'https://example.com/mission-images/foreign-language-diary.jpg', 3),
                                                                             ( '기부활동 참여하기', '원하는 단체에 기부를 하고 그 경험을 나눠주세요.', 'https://example.com/mission-images/donation.jpg', 4),
                                                                             ( '새로운 레시피 도전', '매일 새로운 요리 레시피에 도전하고 결과를 공유해주세요.', 'https://example.com/mission-images/new-recipe.jpg', 5),
                                                                             ( '플로깅 하기', '조깅하면서 쓰레기를 줍는 플로깅을 실천하고 경험을 나눠주세요.', 'https://example.com/mission-images/plogging.jpg', 1),
                                                                             ( '금연 도전', '일주일 동안 금연에 도전하고 그 과정을 기록해보세요.', 'https://example.com/mission-images/quit-smoking.jpg', 2),
                                                                             ( '1일 1알고리즘 풀기', '매일 한 개의 알고리즘 문제를 풀고 해결 과정을 공유해주세요.', 'https://example.com/mission-images/daily-algorithm.jpg', 3),
                                                                             ( '환경 캠페인 참여', '지역 환경 캠페인에 참여하고 그 경험을 나눠주세요.', 'https://example.com/mission-images/environmental-campaign.jpg', 4),
                                                                             ( '감사일기 쓰기', '매일 감사한 일 3가지를 기록하고 변화된 점을 공유해주세요.', 'https://example.com/mission-images/gratitude-journal.jpg', 5);

-- 멤버 생성
INSERT INTO member (member_id, email, password, name, point, role) VALUES
                                                                       (1, 'user1@example.com', 'password1', 'User One', 100, 'USER'),
                                                                       (2, 'user2@example.com', 'password2', 'User Two', 100, 'USER');

-- 보드 생성 (멤버당 하나씩)
INSERT INTO board (board_id, member_position, member_id) VALUES
                                                             (1, 3, 1),  -- User One은 3칸 이동
                                                             (2, 5, 2);  -- User Two는 5칸 이동

-- 타일 생성 (보드당 25개)
INSERT INTO tile (tile_id, board_id, mission_id, orders, state) VALUES
-- User One의 보드 타일
(1, 1, 1, 0, 'OPEN'),
(2, 1, 2, 1, 'OPEN'),
(3, 1, 3, 2, 'OPEN'),
(4, 1, 4, 3, 'OPEN'),  -- 현재 위치
(5, 1, 5, 4, 'OPEN'),
(6, 1, 6, 5, 'OPEN'),
(7, 1, 7, 6, 'OPEN'),
(8, 1, 8, 7, 'OPEN'),
(9, 1, 9, 8, 'OPEN'),
(10, 1, 10, 9, 'OPEN'),
(11, 1, 11, 10, 'OPEN'),
(12, 1, 12, 11, 'OPEN'),
(13, 1, 13, 12, 'OPEN'),
(14, 1, 14, 13, 'OPEN'),
(15, 1, 15, 14, 'OPEN'),
(16, 1, 16, 15, 'OPEN'),
(17, 1, 17, 16, 'OPEN'),
(18, 1, 18, 17, 'OPEN'),
(19, 1, 19, 18, 'OPEN'),
(20, 1, 20, 19, 'OPEN'),
(21, 1, 21, 20, 'OPEN'),
(22, 1, 22, 21, 'OPEN'),
(23, 1, 23, 22, 'OPEN'),
(24, 1, 24, 23, 'OPEN'),
(25, 1, 25, 24, 'OPEN'),
-- User Two의 보드 타일
(26, 2, 1, 0, 'OPEN'),
(27, 2, 2, 1, 'OPEN'),
(28, 2, 3, 2, 'OPEN'),
(29, 2, 4, 3, 'OPEN'),
(30, 2, 5, 4, 'OPEN'),
(31, 2, 6, 5, 'OPEN'),  -- 현재 위치
(32, 2, 7, 6, 'OPEN'),
(33, 2, 8, 7, 'OPEN'),
(34, 2, 9, 8, 'OPEN'),
(35, 2, 10, 9, 'OPEN'),
(36, 2, 11, 10, 'OPEN'),
(37, 2, 12, 11, 'OPEN'),
(38, 2, 13, 12, 'OPEN'),
(39, 2, 14, 13, 'OPEN'),
(40, 2, 15, 14, 'OPEN'),
(41, 2, 16, 15, 'OPEN'),
(42, 2, 17, 16, 'OPEN'),
(43, 2, 18, 17, 'OPEN'),
(44, 2, 19, 18, 'OPEN'),
(45, 2, 20, 19, 'OPEN'),
(46, 2, 21, 20, 'OPEN'),
(47, 2, 22, 21, 'OPEN'),
(48, 2, 23, 22, 'OPEN'),
(49, 2, 24, 23, 'OPEN'),
(50, 2, 25, 24, 'OPEN');

-- 미션 제출 (각 멤버의 현재 위치에 대해)
INSERT INTO mission_summit (id, image_url, content, state, rejection, mission_id, member_id) VALUES
                                                                                                 (1, 'https://example.com/submissions/user1-mission4.jpg', '지역 봉사활동에 참여했습니다. 매우 보람찼어요!', 'PENDING', NULL, 4, 1),
                                                                                                 (2, 'https://example.com/submissions/user2-mission6.jpg', '일주일 동안 재활용 분리수거를 철저히 했습니다.', 'PENDING', NULL, 6, 2);