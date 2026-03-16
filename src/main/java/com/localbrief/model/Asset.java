package com.localbrief.model;

public record Asset(
        AssetType type,
        String symbol,
        String name,
        String pair,
        String venue
) {
}
