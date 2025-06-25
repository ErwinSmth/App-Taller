package com.Innovacion.Taller.domain.repositoryInterfaces.taller;

import com.Innovacion.Taller.domain.dto.taller.TallerImagenDto;

import java.util.List;

public interface ITallerImagenRepository {

    List<TallerImagenDto> findByTallerId(Long TallerId);

}
