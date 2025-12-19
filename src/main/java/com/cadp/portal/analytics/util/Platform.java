package com.cadp.portal.analytics.util;

import java.util.Locale;

public enum Platform {

    SPOTIFY,
    APPLE_MUSIC,
    YOUTUBE,
    AMAZON_MUSIC,
    JIO_SAAVN,
    WYNK,
    UNKNOWN;

    /**
     * Normalize DSP STORE column values into Platform enum
     */
    public static Platform fromStore(String store) {
        if (store == null || store.isBlank()) {
            return UNKNOWN;
        }

        String normalized = store
                .trim()
                .toLowerCase(Locale.ROOT);

        if (normalized.contains("spotify")) return SPOTIFY;
        if (normalized.contains("apple")) return APPLE_MUSIC;
        if (normalized.contains("youtube")) return YOUTUBE;
        if (normalized.contains("amazon")) return AMAZON_MUSIC;
        if (normalized.contains("jio")) return JIO_SAAVN;
        if (normalized.contains("wynk")) return WYNK;

        return UNKNOWN;
    }
}
