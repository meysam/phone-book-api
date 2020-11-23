package digital.paisley.phonebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUserDto {

    public int id;

    @JsonProperty("login")
    public String user;

    @JsonProperty("public_repos")
    public int totalPublicRepos;

    public List<GitHubRepoDto> repos;


}
