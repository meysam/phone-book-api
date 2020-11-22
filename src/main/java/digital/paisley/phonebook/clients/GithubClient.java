package digital.paisley.phonebook.clients;

import digital.paisley.phonebook.config.ConfigMaps;
import digital.paisley.phonebook.dto.GitHubUserDto;
import digital.paisley.phonebook.exceptions.ClientRestErrorException;
import digital.paisley.phonebook.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
@Slf4j
public class GithubClient {

    final RestTemplate restTemplate;
    final String githubUrl;
    private final ClientRestErrorException restErrorException = new ClientRestErrorException();

    public GithubClient(RestTemplate restTemplate, ConfigMaps configMaps) {
        this.restTemplate = restTemplate;
        githubUrl = configMaps.getGithubUrl();
    }

    public GitHubUserDto getGitHubUser(String userName) {
        final String uri = githubUrl.concat(userName);
        try {
            ResponseEntity<GitHubUserDto> response = restTemplate.getForEntity(uri.concat(userName), GitHubUserDto.class,
                    new HashMap<String, String>());
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException();
            } else {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            log.error("HttpClientErrorException", e);
            restErrorException.throwRestErrorException(e);
        } catch (Exception e) {
            log.error("Exception", e);
            restErrorException.throwRestErrorException(e);
        }
        return null;
    }
}
