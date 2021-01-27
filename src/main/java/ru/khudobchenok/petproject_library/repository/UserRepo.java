package ru.khudobchenok.petproject_library.repository;

import ru.khudobchenok.petproject_library.database.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
