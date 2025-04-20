package com.example.LoginPlusSecurity.service.Impl;

import com.example.LoginPlusSecurity.model.BlogPost;
import com.example.LoginPlusSecurity.repository.BlogPostRepository;
import com.example.LoginPlusSecurity.service.BlogPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BlogServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public List<BlogPost> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        return List.of();
    }

    @Override
    public Page<BlogPost> findAll(Pageable pageable) {
        Page<BlogPost> blogPosts = this.blogPostRepository.findAll(pageable);
        return blogPosts;
    }

    @Override
    public Page<BlogPost> findByTitleContainingIgnoreCase(String title, Pageable pageable) {
        Page<BlogPost> blogPosts = this.blogPostRepository.findByTitleContainingIgnoreCase(title, pageable);
        return blogPosts;
    }
}
