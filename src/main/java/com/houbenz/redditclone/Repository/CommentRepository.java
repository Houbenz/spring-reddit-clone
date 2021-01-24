package com.houbenz.redditclone.Repository;

import com.houbenz.redditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Post,Long> {
}
