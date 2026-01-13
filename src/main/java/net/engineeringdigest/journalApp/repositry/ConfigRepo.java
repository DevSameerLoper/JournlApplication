package net.engineeringdigest.journalApp.repositry;

import net.engineeringdigest.journalApp.entity.ConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepo extends MongoRepository<ConfigEntity,String> {
}
