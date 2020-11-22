package digital.paisley.phonebook.clients;

import digital.paisley.phonebook.TestSupport;
import digital.paisley.phonebook.config.ConfigMaps;
import digital.paisley.phonebook.dto.GitHubUserDto;
import digital.paisley.phonebook.exceptions.RestErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.ClientErrorException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class GithubClientTest extends TestSupport {

    @Mock
    private RestTemplate mockRestTemplate;

    @Spy
    private ConfigMaps mockConfigMaps=new ConfigMaps();

    @InjectMocks
    private GithubClient githubClientUnderTest;

    @BeforeEach
    void setUp() {
        mockConfigMaps.setGithubUrl("https://api.github.com/users/");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetGitHubUser() {
        // Setup

        // Configure RestTemplate.getForEntity(...).
        final ResponseEntity<GitHubUserDto> gitHubUserDtoResponseEntity = new ResponseEntity<>(gitHubUserDto(), HttpStatus.CONTINUE);
        when(mockRestTemplate.getForEntity(any(), eq(GitHubUserDto.class),any(Map.class))).thenReturn(gitHubUserDtoResponseEntity);

        // Run the test
        final GitHubUserDto result = githubClientUnderTest.getGitHubUser("userName");

        // Verify the results
        assertThat(result.user).isEqualTo("user");
    }

    @Test
    void testGetGitHubUser_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.getForEntity(any(), eq(GitHubUserDto.class),any(Map.class))).thenThrow(ClientErrorException.class);

        // Run the test and Verify the results
        assertThatExceptionOfType(RestErrorException.class)
                .isThrownBy(() -> {
                    githubClientUnderTest.getGitHubUser("userName");
                });
    }
}
