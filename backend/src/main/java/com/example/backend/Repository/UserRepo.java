package com.example.backend.Repository;
import com.example.backend.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long>
{
    Optional<UserModel> findByUserEmailId(String emailId);

    @Query(value="SELECT `user_id`, `user_role` FROM `user_info` WHERE `user_email` = :emailId", nativeQuery=true)
    String findMyUserIdandRole(String emailId);

    @Query(value="SELECT COUNT(*) FROM `user_info` WHERE `user_email` = :emailId", nativeQuery=true)
    int ifUserPresentorNot(String emailId);
}
