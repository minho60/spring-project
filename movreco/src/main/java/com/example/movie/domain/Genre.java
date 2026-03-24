package com.example.movie.domain;

import lombok.Getter;

@Getter
public enum Genre {
    ACTION("Action", "🧨 액션 / 스릴러"),
    SCI_FI("Sci-Fi", "🚀 SF / 우주"),
    ROMANCE("Romance", "💖 로맨스 / 멜로"),
    FANTASY("Fantasy", "🧙‍♂️ 판타지 / 마법"),
    COMEDY("Comedy", "😂 코미디 / 예능"),
    DRAMA("Drama", "🎭 드라마"),
    ANIMATION("Animation", "🎨 애니메이션"),
    HORROR("Horror", "👻 공포 / 호러"),
    DOCUMENTARY("Documentary", "🎥 다큐멘터리");

    private final String value;
    private final String label;

    Genre(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabelByValue(String value) {
        for (Genre genre : values()) {
            if (genre.value.equals(value)) {
                return genre.label;
            }
        }
        return value;
    }
}
