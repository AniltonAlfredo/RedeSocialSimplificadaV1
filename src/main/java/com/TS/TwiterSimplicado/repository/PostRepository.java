package com.TS.TwiterSimplicado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TS.TwiterSimplicado.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    

}
