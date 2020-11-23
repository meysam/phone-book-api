package digital.paisley.phonebook.clients;

import digital.paisley.phonebook.config.ConfigMaps;
import digital.paisley.phonebook.dto.GitHubRepoDto;
import digital.paisley.phonebook.dto.GitHubUserDto;
import digital.paisley.phonebook.exceptions.ClientRestErrorException;
import digital.paisley.phonebook.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
            ResponseEntity<GitHubUserDto> response = restTemplate.getForEntity(uri, GitHubUserDto.class,
                    new HashMap<String, String>());
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException();
            } else {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            log.error("HttpClientErrorException", e);
            if (e.getRawStatusCode() == 404)
                throw new EntityNotFoundException();
            restErrorException.throwRestErrorException(e);
        } catch (Exception e) {
            log.error("Exception", e);
            restErrorException.throwRestErrorException(e);
        }
        return null;
    }

    public List<String> getRepos(String userName) {
        final String uri = githubUrl.concat(userName).concat("/repos?sort=created&direction=desc&per_page=100&page=");
        List<GitHubRepoDto> repos = new ArrayList<>();
        GitHubUserDto gitHubUser = getGitHubUser(userName);
        try {
            long totalPage = getTotalPage(gitHubUser.totalPublicRepos, 100);
            for (int i = 1; i <= totalPage; i++) {
                ResponseEntity<GitHubRepoDto[]> response = restTemplate.getForEntity(uri.concat(String.valueOf(i)), GitHubRepoDto[].class,
                        new HashMap<String, String>());
                if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new EntityNotFoundException();
                } else {
                    repos.addAll(Arrays.asList(response.getBody()));
                }
            }
            return repos.stream().map(gitHubRepoDto -> gitHubRepoDto.name).collect(Collectors.toList());

        } catch (
                HttpClientErrorException e) {
            log.error("HttpClientErrorException", e);
            restErrorException.throwRestErrorException(e);
        } catch (
                Exception e) {
            log.error("Exception", e);
            restErrorException.throwRestErrorException(e);
        }
        return null;
    }

    private long getTotalPage(long num, long divisor) {
        return (num + divisor - 1) / divisor;
    }
}
