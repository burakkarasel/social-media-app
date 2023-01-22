package com.kullop.socialMediaAppBackend.repositories.concretes;

import com.kullop.socialMediaAppBackend.repositories.abstracts.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeDao {
    private LikeRepository likeRepository;

    public LikeDao(LikeRepository _likeRepository){
        this.likeRepository = _likeRepository;
    }
}
