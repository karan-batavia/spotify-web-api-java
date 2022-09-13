package se.michaelthelin.spotify.requests.data.follow.legacy;

import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import se.michaelthelin.spotify.ITest;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.AbstractDataTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static se.michaelthelin.spotify.Assertions.assertHasBodyParameter;
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class FollowPlaylistRequestTest extends AbstractDataTest<String> {
  private final FollowPlaylistRequest defaultRequest = ITest.SPOTIFY_API
    .followPlaylist(ITest.ID_USER, ITest.ID_PLAYLIST, ITest.PUBLIC)
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .build();

  public FollowPlaylistRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertHasBodyParameter(
      defaultRequest,
      "public",
      ITest.PUBLIC);
    assertEquals(
      "https://api.spotify.com:443/v1/users/abbaspotify/playlists/3AGOiaoRXMSjswCLtuNqv5/followers",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
      string);
  }
}
