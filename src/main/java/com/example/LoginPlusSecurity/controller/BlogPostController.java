package com.example.LoginPlusSecurity.controller;

import java.security.Principal;
import java.util.List;

import com.example.LoginPlusSecurity.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.LoginPlusSecurity.model.BlogPost;
import com.example.LoginPlusSecurity.repository.BlogPostRepository;
import com.example.LoginPlusSecurity.repository.CategoryRepository;
import com.example.LoginPlusSecurity.repository.CommentRepository;
import com.example.LoginPlusSecurity.service.BlogPostService;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final CategoryRepository categoryRepository;
    private final BlogPostRepository blogPostRepository;
    private final CommentRepository commentRepository;


    public BlogPostController(BlogPostService blogPostService, CategoryRepository categoryRepository, BlogPostRepository blogPostRepository, CommentRepository commentRepository) {
        this.blogPostService = blogPostService;
        this.categoryRepository = categoryRepository;
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }


    @GetMapping("/admin/manage")
    public String home() {
        return "manage";
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<BlogPost>> showAllBlogPosts(Pageable pageable) {
        Page<BlogPost> blogPosts = this.blogPostService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(blogPosts);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BlogPost>> searchBlogPosts(@RequestParam("title") String title,Pageable pageable) {
        Page<BlogPost> blogPosts = blogPostService.findByTitleContainingIgnoreCase(title,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(blogPosts);
    }


//    @GetMapping("/category/{id}")
//    public String getBlogPostsByCategory(@PathVariable("id") Long categoryId,
//                                        @RequestParam(defaultValue = "latest") String filter,
//                                        @RequestParam(defaultValue = "0") int page,
//                                        Model model) {
//        int size = 5; // Fixed size
//        Page<BlogPost> blogPosts = blogPostService.categorySorting(categoryId, page, size, filter);
//        String categoryName = categoryRepository.getCategoryNameById(categoryId); // Fetch category name
//        model.addAttribute("blogPosts", blogPosts.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", blogPosts.getTotalPages());
//        model.addAttribute("filterCategoryId", categoryId);
//        model.addAttribute("categoryName", categoryName); // Add category name to the model
//        model.addAttribute("filter", filter);
//        return "categoryResults";
//    }
//
//
//
//    @GetMapping("/page/{pageNumber}")
//    public String showPaginatedHomePage(@PathVariable int pageNumber,
//                                        @ModelAttribute("sortBy") String sortBy,
//                                        Model model) {
//        int size = 5;
//        Page<BlogPost> blogPosts = blogPostService.homepageSorting(pageNumber, size, sortBy);
//        model.addAttribute("blogPosts", blogPosts.getContent());
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", blogPosts.getTotalPages());
//        model.addAttribute("sortBy", sortBy);
//        return "home";
//    }
//
//
//    @GetMapping("/blog/{slug}")
//    public String viewBlogPost(@PathVariable String slug, Model model) {
//        try {
//            BlogPost blogPost = blogPostService.findBySlug(slug);
//            blogPost.setViews(blogPost.getViews() + 1);
//            blogPostRepository.save(blogPost); // Save updated view count
//            model.addAttribute("blogPost", blogPost);
//            model.addAttribute("comments", commentRepository.findByBlogPostId(blogPost.getId()));
//            return "viewBlog";
//        } catch (RuntimeException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }
//
//
//    @GetMapping("/admin/blog/create")
//    public String showCreateBlogForm(Model model) {
//        model.addAttribute("blogPost", new BlogPost());
//        model.addAttribute("categories", categoryRepository.findAll());
//        return "createBlog";
//    }
//
//
//    @PostMapping("/admin/blog/create")
//    public String createBlogPost(@ModelAttribute BlogPost blogPost, Principal principal) {
//        blogPost.setAuthorName(principal.getName()); // Fetch the logged-in user's name
//        blogPostService.saveBlogPost(blogPost);
//        return "redirect:/"; //
//    }
//
//    @DeleteMapping("/admin/blog/delete/{id}")
//    public String deleteBlogPost(@PathVariable("id") Long id, Principal principal) {
//        this.blogPostService.deleteById(id);
//        return "Deleted Succesfully "+id; //
//    }
}