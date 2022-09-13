package se.michaelthelin.spotify.requests.data.player;

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
import static se.michaelthelin.spotify.Assertions.assertHasHeader;

public class SkipUsersPlaybackToPreviousTrackRequestTest extends AbstractDataTest<String> {
  private final SkipUsersPlaybackToPreviousTrackRequest defaultRequest = ITest.SPOTIFY_API
    .skipUsersPlaybackToPreviousTrack()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(null))
    .device_id(ITest.DEVICE_ID)
    .build();

  public SkipUsersPlaybackToPreviousTrackRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertHasHeader(defaultRequest, "Content-Type", "application/json");
    assertEquals(
      "https://api.spotify.com:443/v1/me/player/previous?device_id=5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e",
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
