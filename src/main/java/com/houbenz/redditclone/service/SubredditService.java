package com.houbenz.redditclone.service;

import com.houbenz.redditclone.Repository.SubredditRepository;
import com.houbenz.redditclone.dto.SubredditDto;
import com.houbenz.redditclone.model.Subreddit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {


    private final SubredditRepository subredditRepository ;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){

        Subreddit subreddit = mapSubredditDto(subredditDto);

        subreddit.setCreateDate(Instant.now());

        Subreddit save =subredditRepository.save(subreddit);
        subredditDto.setId(save.getId());

        return subredditDto;

    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {

        return Subreddit.builder()
                .id(subredditDto.getId())
                .description(subredditDto.getDescription())
                .name(subredditDto.getName())
                .build();
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream().map(this::mapToDto)
                .collect(toList());
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder()
                .name(subreddit.getName())
                .description(subreddit.getDescription())
                .numberOfPosts(subreddit.getPosts().size())
                .id(subreddit.getId())
                .build();
    }




}
