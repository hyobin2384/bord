package com.example.pinterest.Repository;

import com.example.pinterest.Domain.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m where m.username = :username1")
    Member findByUsername(@Param("username1") String username);

}
