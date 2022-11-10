package com.example.simpleapi.repositories;

import com.example.simpleapi.models.CambioModel;
import org.springframework.data.repository.CrudRepository;

public interface TipoCambioRepositories extends CrudRepository<CambioModel,Long> {
}
