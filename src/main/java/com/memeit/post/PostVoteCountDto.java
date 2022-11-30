package com.memeit.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostVoteCountDto {


    private Long votesCount;


    PostVoteCountDto() {
    }

    public Long getVotesCount() {
      return votesCount;
    }

    public PostVoteCountDto setVotesCount(Long votesCount) {
        this.votesCount = votesCount;
        return this;
    }
}
