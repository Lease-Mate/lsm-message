package com.lsm.ws.message.context.dto;

public class IdWrapperDto {

    public String id;

    public static IdWrapperDto from(String id) {
        var dto = new IdWrapperDto();
        dto.id = id;
        return dto;
    }
}
