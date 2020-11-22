package digital.paisley.phonebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitHubRepoDto {

    public int id;

    public String name;

    public String owner;

    public Date createdAt;

}
