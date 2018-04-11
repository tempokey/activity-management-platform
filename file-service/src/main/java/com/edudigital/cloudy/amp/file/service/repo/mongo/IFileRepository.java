package com.edudigital.cloudy.amp.file.service.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edudigital.cloudy.amp.file.service.entity.po.Picture;


@Repository
public interface IFileRepository extends MongoRepository<Picture, String> {

}
