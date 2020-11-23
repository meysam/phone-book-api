package digital.paisley.phonebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GitHubRepoDto {

    public int id;

    public String name;

    @JsonProperty("created_at")
    public Date createdAt;

}
