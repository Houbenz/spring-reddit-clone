package com.houbenz.redditclone.controller;

import com.houbenz.redditclone.dto.SubredditDto;
import com.houbenz.redditclone.model.Subreddit;
import com.houbenz.redditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/subreddit")
@Slf4j
public class SubredditController {


    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubrredit(@RequestBody SubredditDto subredditDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditDto));
    }


    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());

    }

}
