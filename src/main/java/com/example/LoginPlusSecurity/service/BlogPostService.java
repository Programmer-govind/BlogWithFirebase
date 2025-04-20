package com.example.LoginPlusSecurity.service;

import com.example.LoginPlusSecurity.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.LoginPlusSecurity.model.BlogPost;
import com.example.LoginPlusSecurity.repository.BlogPostRepository;

import java.util.List;

public interface BlogPostService {

    public List<BlogPost> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Page<BlogPost> findAll(Pageable pageable);

    Page<BlogPost> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
