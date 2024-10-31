package com.TS.TwiterSimplicado.controller.dto;

import java.util.List;

public record FeedDto(List<FeedItemDto> feedItens,
        int page,
        int pageSize,
        int TotalPages,
        Long TotalElements) {

}
