package digital.paisley.phonebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUserDto {

    public int id;

    @JsonProperty("login")
    public String user;

    @JsonProperty("device_uuid")
    public int totalPublicRepos;

}
