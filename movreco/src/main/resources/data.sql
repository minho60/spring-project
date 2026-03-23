DELETE FROM item;

INSERT INTO item (title, description, type, image_url, rating, genre) VALUES 
('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology.', 'MOVIE', 'https://example.com/inception.jpg', 8.8, 'Sci-Fi'),
('The Matrix', 'A computer hacker learns from mysterious rebels about the true nature of his reality.', 'MOVIE', 'https://example.com/matrix.jpg', 8.7, 'Action'),
('Interstellar', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival.', 'MOVIE', 'https://example.com/interstellar.jpg', 8.6, 'Sci-Fi'),
('1984', 'A dystopian social science fiction novel and cautionary tale.', 'BOOK', 'https://example.com/1984.jpg', 9.0, 'Fiction'),
('To Kill a Mockingbird', 'The story of racial injustice and the destruction of childhood innocence.', 'BOOK', 'https://example.com/mockingbird.jpg', 9.2, 'Classic'),
('Dune', 'Set in the distant future amidst a feudal interstellar society in which various noble houses control planetary fiefs.', 'BOOK', 'https://example.com/dune.jpg', 8.9, 'Sci-Fi');
