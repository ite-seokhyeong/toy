package com.itesh.oauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itesh.oauth2.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

}
