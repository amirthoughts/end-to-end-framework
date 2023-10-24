package api.app.applicationApi;


import api.app.RestResource;
import api.pojo.Playlist;
import api.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.app.TokenManager.getToken;
import static api.app.applicationApi.Routes.PLAYLISTS;
import static api.app.applicationApi.Routes.USERS;


public class PlaylistApi {

    //static String access_token = "BQA4QxbLJ8JkSztwbEonPZhHBWw8AS2WO8yG20Gnzs6Csd174YNHtbtDWLoLlbdO_yqFGgAf-ppj3CLeIc_KnpMhGpIlWujnkABI1OKVP8Kv5QGl-lvbx8U_dh6z4OVimw0dK4jIZ-lawUfCuWRrtJOS38R9PaVim82I-UTBzaJVBbvxdmO8sXXk3p3ImdZFWHJDLGCQKP3l9_BPm17Q96nH5hwj5bW4NiwjUTq6bI5F3WrRz-_9gDQQ3sHBGvR1Y4OgIHDO8y8JfEj2";

    @Step
    public static Response post(Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), requestPlaylist);
    }

    @Step
    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, requestPlaylist);
    }

    @Step
    public static Response get(String playlistId){
        return RestResource.get(PLAYLISTS + "/" +  playlistId, getToken());
    }

    @Step
    public static Response update(Playlist requestPlaylist, String playlistId){
        return RestResource.update(PLAYLISTS + "/" +  playlistId, getToken(), requestPlaylist);
    }
}
