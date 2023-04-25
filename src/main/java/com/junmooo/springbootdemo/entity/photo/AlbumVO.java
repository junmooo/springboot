package com.junmooo.springbootdemo.entity.photo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AlbumVO {
    private Album album;
    private List<Photo> photos;
}
