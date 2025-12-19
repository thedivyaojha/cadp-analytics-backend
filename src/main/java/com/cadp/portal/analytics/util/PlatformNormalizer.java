package com.cadp.portal.analytics.util;

public class PlatformNormalizer {
    public static String normalize(String store) {
        if (store == null) return null;

        return store
                .trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "_")
                .replaceAll("^_|_$", "");
    }
}
