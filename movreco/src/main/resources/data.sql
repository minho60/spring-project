DELETE FROM item;

INSERT INTO item (title, description, type, image_url, rating, genre) VALUES 
('인셉션', '타인의 꿈에 들어가 생각을 훔치는 특수 보안요원의 이야기를 다룬 SF 블록버스터.', 'MOVIE', '/assets/img/inception.jpg', 8.8, 'SF'),
('매트릭스', '평범한 시스템 해커가 인류를 지배하는 기계들과 맞서 싸우는 가상세계의 비밀을 파헤친다.', 'MOVIE', '/assets/img/matrix.jpg', 8.7, '액션'),
('인터스텔라', '점점 황폐해져가는 지구를 대체할 인류의 새로운 터전을 찾기 위해 우주로 떠나는 탐험가들의 이야기.', 'MOVIE', '/assets/img/interstellar.jpg', 8.6, 'SF'),
('1984', '전체주의 국가의 억압과 감시 속에서 진실을 찾으려는 한 남자의 투쟁을 그린 디스토피아 소설.', 'BOOK', '/assets/img/1984.jpg', 9.0, '소설'),
('앵무새 죽이기', '1930년대 미국 남부를 배경으로 인종 차별과 편견에 맞서는 정의로운 변호사의 이야기.', 'BOOK', '/assets/img/mockingbird.jpg', 9.2, '고전'),
('듄', '우주에서 가장 비싼 물질인 스파이스를 두고 벌어지는 가문들의 권력 투쟁과 거대한 모래 행성의 비밀.', 'BOOK', '/assets/img/dune.jpg', 8.9, 'SF'),
('다크 나이트', '고담시를 혼란에 빠뜨린 최악의 악당 조커에 맞서 모든 것을 걸고 싸우는 배트맨의 고독한 사투.', 'MOVIE', '/assets/img/darkknight.jpg', 9.0, '액션'),
('오펜하이머', '제2차 세계대전 당시 핵무기 개발 프로젝트를 이끈 천재 과학자 J. 로버트 오펜하이머의 숨겨진 생애.', 'MOVIE', '/assets/img/oppenheimer.jpg', 8.6, '전기'),
('프레스티지', '19세기 말 런던, 최고의 마술사가 되기 위해 모든 것을 바친 두 남자의 치열하고 잔혹한 대결.', 'MOVIE', '/assets/img/prestige.jpg', 8.5, '미스터리'),
('메멘토', '10분밖에 기억하지 못하는 단기 기억상실증 환자가 몸에 새긴 문신을 단서로 아내의 살인범을 쫓는다.', 'MOVIE', '/assets/img/memento.jpg', 8.4, '미스터리');
