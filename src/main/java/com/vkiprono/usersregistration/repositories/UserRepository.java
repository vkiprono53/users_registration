package com.vkiprono.usersregistration.repositories;

import com.vkiprono.usersregistration.models.UserReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vkiprono on 5/27/20 ---2:08 PM
 * @project usersregistration
 */
@Repository
public interface UserRepository extends JpaRepository<UserReg, Long> {
}
