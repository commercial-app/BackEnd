-- 미션 카테고리 생성
INSERT INTO mission_categories (category_id, name) VALUES (1, '환경보호');
INSERT INTO mission_categories (category_id, name) VALUES (2, '건강');
INSERT INTO mission_categories (category_id, name) VALUES (3, '교육');
INSERT INTO mission_categories (category_id, name) VALUES (4, '사회공헌');
INSERT INTO mission_categories (category_id, name) VALUES (5, '자기계발');

-- 30개의 임시 미션 생성
INSERT INTO mission (mission_id, title, content, image_url, category_id) VALUES
                                                                               (1, '일회용품 줄이기', '일주일 동안 일회용품 사용을 줄이고 경험을 공유해주세요.', 'images/mission1.jpg', 1),
                                                                               (2, '매일 만보 걷기', '일주일 동안 매일 만보씩 걷고 기록을 남겨주세요.', 'images/mission2.jpg', 2),
                                                                               (3, '새로운 언어 배우기', '일주일 동안 새로운 언어의 기초 5문장을 배워보세요.', 'images/mission3.jpg', 3),
                                                                               (4, '지역 봉사활동 참여', '이번 주 지역 봉사활동에 참여하고 경험을 나눠주세요.', 'images/mission4.jpg', 4),
                                                                               (5, '독서 습관 기르기', '일주일 동안 매일 30분씩 책을 읽고 느낀 점을 기록해보세요.', 'images/mission5.jpg', 5),
                                                                               (6, '재활용 분리수거 실천', '일주일 동안 재활용 분리수거를 철저히 하고 결과를 공유해주세요.', 'images/mission6.jpg', 1),
                                                                               (7, '명상 습관 들이기', '매일 10분씩 명상을 하고 마음의 변화를 기록해보세요.', 'images/mission7.jpg', 2),
                                                                               (8, '온라인 강의 수강하기', '관심 있는 분야의 온라인 강의를 수강하고 배운 점을 나눠주세요.', 'images/mission8.jpg', 3),
                                                                               (9, '헌혈 참여하기', '헌혈에 참여하고 그 경험을 공유해주세요.', 'images/mission9.jpg', 4),
                                                                               (10, '새로운 취미 도전', '새로운 취미를 시작하고 일주일간의 진행 상황을 기록해보세요.', 'images/mission10.jpg', 5),
                                                                               (11, '친환경 제품 사용하기', '일상에서 친환경 제품을 사용하고 그 경험을 나눠주세요.', 'images/mission11.jpg', 1),
                                                                               (12, '건강한 식단 유지하기', '일주일 동안 균형 잡힌 식단을 유지하고 메뉴를 공유해주세요.', 'images/mission12.jpg', 2),
                                                                               (13, '무료 강연 참석하기', '관심 분야의 무료 강연에 참석하고 배운 점을 정리해보세요.', 'images/mission13.jpg', 3),
                                                                               (14, '노인복지관 방문하기', '지역 노인복지관을 방문하여 봉사활동을 하고 느낀 점을 나눠주세요.', 'images/mission14.jpg', 4),
                                                                               (15, '스트레칭 습관 들이기', '매일 아침 10분씩 스트레칭을 하고 건강 변화를 기록해보세요.', 'images/mission15.jpg', 5),
                                                                               (16, '에너지 절약 실천', '일주일 동안 에너지 절약을 실천하고 그 방법을 공유해주세요.', 'images/mission16.jpg', 1),
                                                                               (17, '물 마시기 습관 들이기', '하루 2리터의 물을 마시는 습관을 들이고 변화를 기록해보세요.', 'images/mission17.jpg', 2),
                                                                               (18, '전문 서적 읽기', '관심 분야의 전문 서적을 읽고 주요 내용을 요약해보세요.', 'images/mission18.jpg', 3),
                                                                               (19, '유기동물 보호소 방문', '지역 유기동물 보호소를 방문하여 봉사하고 경험을 나눠주세요.', 'images/mission19.jpg', 4),
                                                                               (20, '매일 일기 쓰기', '일주일 동안 매일 일기를 작성하고 그 경험을 공유해주세요.', 'images/mission20.jpg', 5),
                                                                               (21, '플로깅 참여하기', '달리기하며 쓰레기를 줍는 플로깅에 참여하고 경험을 나눠주세요.', 'images/mission21.jpg', 1),
                                                                               (22, '요가 배우기', '일주일 동안 매일 15분씩 요가를 배우고 느낀 점을 기록해보세요.', 'images/mission22.jpg', 2),
                                                                               (23, '오픈소스 프로젝트 기여', '오픈소스 프로젝트에 기여하고 그 과정을 공유해주세요.', 'images/mission23.jpg', 3),
                                                                               (24, '지역 청소년 멘토링', '지역 청소년을 대상으로 멘토링을 진행하고 경험을 나눠주세요.', 'images/mission24.jpg', 4),
                                                                               (25, '악기 배우기', '새로운 악기를 배워보고 일주일간의 연습 과정을 기록해보세요.', 'images/mission25.jpg', 5),
                                                                               (26, '제로웨이스트 도전', '일주일 동안 제로웨이스트 생활을 실천하고 팁을 공유해주세요.', 'images/mission26.jpg', 1),
                                                                               (27, '금연 도전', '금연에 도전하고 일주일간의 경험을 기록해보세요.', 'images/mission27.jpg', 2),
                                                                               (28, '코딩 배우기', '새로운 프로그래밍 언어를 배워보고 간단한 프로그램을 만들어보세요.', 'images/mission28.jpg', 3),
                                                                               (29, '시각장애인 도서 녹음', '시각장애인을 위한 도서 녹음 봉사에 참여하고 경험을 나눠주세요.', 'images/mission29.jpg', 4),
                                                                               (30, '명화 따라 그리기', '유명한 명화를 따라 그려보고 그 과정을 공유해주세요.', 'images/mission30.jpg', 5);